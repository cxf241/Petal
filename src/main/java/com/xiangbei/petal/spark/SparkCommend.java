/*
 * @author：李放
 *
 */
package com.xiangbei.petal.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.Row;

import java.util.ArrayList;
import java.util.List;

public class SparkCommend extends SparkConfig{

    private static JavaRDD<Rating> ratings;
    private static MatrixFactorizationModel model;

    public static void initialization(){
        System.out.println("initializing....");
        long startTime = System.currentTimeMillis();
        SparkConfig.initialization();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime+"ms");
    }

    public static void loadRdd(){
        System.out.println("loadingRDD....");
        long startTime = System.currentTimeMillis();
        Function f = new Function<Row,Rating>(){
            public Rating call(Row row) throws Exception{
                double rating = (double) row.get(2);
                int user = (int) row.get(0);
                int movie = (int) row.get(1);

                return new Rating(user,movie,rating);
            }
        };
        ratings = sqlContext.read().jdbc(url, table, connectionProperties).select("*").javaRDD().map(f);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime+"ms");
        int rank = 10;
        int numIterations = 10;
        double alpha = 0.01;
        long endTime2 = System.currentTimeMillis();
        model = ALS.train(JavaRDD.toRDD(ratings), rank, numIterations, alpha);
        System.out.println(endTime2-endTime+"ms training");
    }

    public static void commendMovies(Rating rating){
        if(sparkConf == null)
            initialization();
        if(ratings == null)
            loadRdd();

        System.out.println("startCommending");
        long startTime = System.currentTimeMillis();
        List<Rating> list = new ArrayList<>();
        list.add(rating);
        JavaRDD<Rating> newRating = sc.parallelize(list);
        ratings  = ratings.union(newRating);
        int rank = 10;
        int numIterations = 10;
        double alpha = 0.01;
        model = ALS.train(JavaRDD.toRDD(ratings), rank, numIterations, alpha);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
        //return ratings ;
    }

    public static List<String> getRecommend(int x, int y)
    {
        Rating[] ts = model.recommendProducts(x,y);
        List<String> list = new ArrayList<>();

        for(int i =0 ;i < ts.length;i++){
            list.add(String.valueOf(ts[i].product()));
        }
        return list;
    }
}
