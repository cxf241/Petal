package com.xiangbei.petal.spark;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.mllib.recommendation.Rating;

/**
 * @ClassName: Union
 * @Description: TODO
 * @author: tyrion
 * @date: 2018年7月18日 上午10:39:05
 */
public class Union extends SparkConfig  implements Runnable ,Serializable {

    private Rating rating = null;

    public Union() {
        super();
    }
    public void setRating(Rating rating) {
        this.rating=rating;
    }
    public void run() {

    }

    public JavaRDD<Rating> getRatings(){
        return ratings;
    }

    public  JavaRDD<Rating> join(Rating rating){
        List<Rating> list = new ArrayList<>();
        list.add(rating);
        JavaRDD<Rating> newRating = sc.parallelize(list);
        SparkConfig.ratings  = SparkConfig.ratings.union(newRating);
        return  SparkConfig.ratings ;
    }

}
