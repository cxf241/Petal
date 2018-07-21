package com.xiangbei.petal.spark;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;

/**
 * @ClassName: Union
 * @Description: TODO
 * @author: tyrion
 * @date: 2018年7月18日 上午10:39:05
 */
public class Union extends SparkConfig  implements Runnable ,Serializable {

    public static Rating rating = null;
    public static MatrixFactorizationModel model;

    public Union() {
        super();
    }

    public void run() {

        int rank = 10;
        int numIterations = 10;
        double alpha = 0.01;
        model = ALS.train(JavaRDD.toRDD(ratings), rank, numIterations, alpha);

    }

    public JavaRDD<Rating> getRatings(){
        return ratings;
    }

    public  void join(Rating rating){
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

}
