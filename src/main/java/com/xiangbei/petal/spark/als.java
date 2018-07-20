package com.xiangbei.petal.spark;


import java.io.IOException;
import java.util.List;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
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
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.sql.Row;

public class als {


	public static MatrixFactorizationModel model;
	public static Union union;


	public static void Init(List<Rating> list){

		// 创建一个Runnable实现类的对象
		Union union = new Union();

		union.ratings = union.sc.parallelize(list);

		Thread thread = new Thread(union);
		thread.start();


		model = Pre.getModel(union.getRatings());

		//关闭JavaSparkContext，推荐结束
		//union.sc.close();
	}

	public static void ChangeModel (Rating rating){

		union.join(rating);

		model = Pre.getModel(union.getRatings());

		//关闭JavaSparkContext，推荐结束
		//union.sc.close();
	}

}