package com.xiangbei.petal.spark;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
	 * Licensed to the Apache Software Foundation (ASF) under one or more
	 * contributor license agreements.  See the NOTICE file distributed with
	 * this work for additional information regarding copyright ownership.
	 * The ASF licenses this file to You under the Apache License, Version 2.0
	 * (the "License"); you may not use this file except in compliance with
	 * the License.  You may obtain a copy of the License at
	 *
	 *    http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 */

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.Row;

public final class als {
	public static void main(String... args) throws IOException {
		
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
		 sparkConf.setMaster("local[2]");
		 JavaSparkContext sc =  new JavaSparkContext(sparkConf);
		 
		//从数据库读取数据,返回JavaRDD<Row>类型的数据
		JavaRDD<Row> row = sql.getRow(sc);
		
		//JavaRDD<Row>转化为JavaRDD<Rating>类型的数据，用于建立als的model
		JavaRDD<Rating> ratings = row.map(
					new Function<Row,Rating>(){
						public Rating call(Row row) throws Exception{
						double rating = (double) row.get(0);
						int user = (int) row.get(1);
						int movie = (int) row.get(2);
						
						return new Rating(user,movie,rating);
					}
				}
);
		
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

		//getPre返回推测出用户喜爱的电影的前几名
		Pre.getRecommend(model, 3, 5);

		//getRMSE返回均方根误差
		//Pre.getRMSE(model, ratings);
	
		//输出前几名的推荐
		//File file = new File("result.txt");  //存放数组数据的文件
		//FileWriter out = new FileWriter(file);  //文件写入流
			  
		//将数组中的数据写入到文件中
		/*int n =1;
		Rating[] temp =   model.recommendProducts( n, 16);
		while(temp != null) {
			for(int i =0 ;i < temp.length;i++){
				
				try {
					out.write(n+"\t");
					out.write(temp[i].product()+"\t\n");
					
					
					 //System.out.println("用户"+n+"商品:"+temp[i].product()+"，评分:"+temp[i].rating());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   }
			System.out.println(n);
			n++;
			temp =   model.recommendProducts( n, 16);
		}*/
		//关闭输出流
		//out.close();
		System.out.println("finished");
		
		//关闭JavaSparkContext，推荐结束
		sc.close();

	}
}