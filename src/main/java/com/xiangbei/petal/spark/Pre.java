package com.xiangbei.petal.spark;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import scala.Tuple2;
/**
 * 
 * @ClassName: Pre 
 * @Description: 预测推荐类，实现推荐
 * @author: tyrion
 * @date: 2018年7月13日 下午8:06:53
 */

public class Pre {
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
	public static Rating[] getRecommend(MatrixFactorizationModel model, int x,int y)
	{
		  Rating[] ts = model.recommendProducts(x,y);
		    System.out.println("用户"+x+"最喜欢的"+y+"个商品为:");
		    for(int i =0 ;i < ts.length;i++){
		        System.out.println("商品:"+ts[i].product()+"，评分:"+ts[i].rating());
		    }
			return ts;
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
}
