package com.xiangbei.petal.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.Row;
import scala.Tuple2;

import javax.swing.text.StringContent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: Pre
 * @Description: 读取数据，预测推荐模型，实现推荐
 * @author: tyrion
 * @date: 2018年7月13日 下午8:06:53
 */

public class Pre {
	/**
	 *

	 * @Title: load
	 * @Description: TODO(读取数据)
	 * @param @return
	 * @return JavaRDD<Rating>    返回类型
	 * @throws
	 */
	public static  JavaSparkContext load() {
		/*		读取本地TXT类型文件方法
 		SparkConf sparkConf = new SparkConf().setAppName("JavaALS");
		sparkConf.setMaster("local[*]");
		JavaSparkContext jsc = new JavaSparkContext(sparkConf);
		// 载入文件路径
		String path = "ml-100k/u.data";
		JavaRDD<String> data = jsc.textFile(path);*/

		//初始化spark
		SparkConf sparkConf = new SparkConf();
		sparkConf.setAppName("sql");
		sparkConf.setMaster("local[*]");
		JavaSparkContext sc =  new JavaSparkContext(sparkConf);

		//关闭JavaSparkContext
		//sc.close();
		return sc;

	}

	/**
	 *
	 * @Title: getRating
	 * @Description: TODO(将读取的数据存储到JavaRDD<Rating>结构中)
	 * @param @param sc
	 * @param @return  参数说明
	 * @return JavaRDD<Rating>    返回类型
	 * @throws
	 */
	public static JavaRDD<Rating> getRating( JavaSparkContext sc){
		//从数据库读取数据,返回JavaRDD<Row>类型的数据
		JavaRDD<Row> row = sql.getRow(sc);

		//JavaRDD<Row>转化为JavaRDD<Rating>类型的数据，用于建立als的model
		JavaRDD<Rating> ratings = row.map(
				new Function<Row,Rating>(){
					public Rating call(Row row) throws Exception{
						double rating = (double) row.get(2);
						int user = (int) row.get(0);
						int movie = (int) row.get(1);

						return new Rating(user,movie,rating);
					}
				}
		);
		return ratings;
	}



	/**
	 *
	 * @Title: getRecommend
	 * @Description: TODO(获取向用户推荐的商品)
	 * @param @param model
	 * @param @param x	用户ID
	 * @param @param y	推荐商品个数
	 * @return Rating[]
	 * @throws
	 */
	public static List<String> getRecommend(MatrixFactorizationModel model, int x, int y)
	{
		Rating[] ts = model.recommendProducts(x,y);
		List<String> list = new ArrayList<>();

		for(int i =0 ;i < ts.length;i++){
			list.add(String.valueOf(ts[i].product()));
		}
		return list;
	}

	/**
	 *

	 * @Title: getRMSE
	 * @Description: TODO(返回均方根误差RMSE)
	 * @param @param model	预测模型
	 * @param @param ratings  实际数据
	 * @return double    返回类型
	 * @throws
	 */
	public static double getRMSE( MatrixFactorizationModel model,JavaRDD<Rating>ratings ) {

		// Evaluate the model on rating data
		JavaRDD<Tuple2<Object, Object>> userProducts = ratings.map(
				new Function<Rating, Tuple2<Object, Object>>() {
					public Tuple2<Object, Object> call(Rating r) {
						return new Tuple2<Object, Object>(r.user(), r.product());
					}
				}
		);
		JavaPairRDD<Tuple2<Integer, Integer>, Double> predictions = JavaPairRDD.fromJavaRDD(
				model.predict(JavaRDD.toRDD(userProducts)).toJavaRDD().map(
						new Function<Rating, Tuple2<Tuple2<Integer, Integer>, Double>>() {
							public Tuple2<Tuple2<Integer, Integer>, Double> call(Rating r){
								return new Tuple2<Tuple2<Integer, Integer>, Double>(
										new Tuple2<Integer, Integer>(r.user(), r.product()), r.rating());
							}
						}
				));
		JavaRDD<Tuple2<Double, Double>> ratesAndPreds =
				JavaPairRDD.fromJavaRDD(ratings.map(
						new Function<Rating, Tuple2<Tuple2<Integer, Integer>, Double>>() {
							public Tuple2<Tuple2<Integer, Integer>, Double> call(Rating r){
								return new Tuple2<Tuple2<Integer, Integer>, Double>(
										new Tuple2<Integer, Integer>(r.user(), r.product()), r.rating());
							}
						}
				)).join(predictions).values();
		double MSE = JavaDoubleRDD.fromRDD(ratesAndPreds.map(
				new Function<Tuple2<Double, Double>, Object>() {
					public Object call(Tuple2<Double, Double> pair) {
						Double err = pair._1() - pair._2();
						return err * err;
					}
				}
		).rdd()).mean();

		System.out.println("Mean Squared Error = " + MSE);
		return MSE;
		// Save and load model
		// model.save(sc.sc(), "myModelPath");
		//MatrixFactorizationModel sameModel = MatrixFactorizationModel.load(sc.sc(), "myModelPath");
	}

	/**
	 *
	 * @param ratings
	 * @return
	 */
	public static MatrixFactorizationModel getModel(JavaRDD<Rating> ratings){

		/**
		 * 1) ratings : 评分矩阵对应的RDD。需要我们输入。如果是隐式反馈，则是评分矩阵对应的隐式反馈矩阵。
		 *
		 * 2) rank :
		 * 矩阵分解时对应的低维的维数。即PTm×kQk×nPm×kTQk×n中的维度k。这个值会影响矩阵分解的性能，越大则算法运行的时间和占用的内存可能会越多。通常需要进行调参，一般可以取10-200之间的数。
		 *
		 * 3) iterations
		 * :在矩阵分解用交替最小二乘法求解时，进行迭代的最大次数。这个值取决于评分矩阵的维度，以及评分矩阵的系数程度。一般来说，不需要太大，比如5-20次即可。默认值是5。
		 *
		 * 4) lambda: 在
		 * python接口中使用的是lambda_,原因是lambda是Python的保留字。这个值即为FunkSVD分解时对应的正则化系数。主要用于控制模型的拟合程度，增强模型泛化能力。取值越大，则正则化越强
		 * 大型推荐系统一般需要调参得到合适的值。
		 *
		 * 5) alpha :
		 * 这个参数仅仅在使用隐式反馈trainImplicit时有用。指定了隐式反馈信心阈值，这个值越大则越认为用户和他没有评分的物品之间没有关联。一般需要调参得到合适值。
		 */

		/*JavaRDD<Rating>[] splits = ratings.randomSplit(new double[] { 0.8, 0.2 });
		JavaRDD<Rating> training = splits[0]; // 训练集
		JavaRDD<Rating> test = splits[1]; // 测试集
		 */

		int rank = 10;
		int numIterations = 10;
		double alpha = 0.01;
		MatrixFactorizationModel model = ALS.train(JavaRDD.toRDD(ratings), rank, numIterations, alpha);
		return model;
	}
/*	public static void getRecommendForAll(MatrixFactorizationModel model, int n){
		return model.recommendProductsForUsers(n);
	}*/


}