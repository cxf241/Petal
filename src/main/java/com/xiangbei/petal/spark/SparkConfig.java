package com.xiangbei.petal.spark;


import java.io.Serializable;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.Rating;

/**
 * @ClassName: SparkConfig
 * @Description: 初始化Java spark
 * @author: tyrion
 * @date: 2018年7月17日 下午6:46:04
 */

public class SparkConfig implements Serializable{
    public static SparkConf sparkConf;
    public static JavaSparkContext sc;
    public static JavaRDD<Rating> ratings;

    public SparkConfig(){
        //初始化
        sparkConf = new SparkConf();
        sparkConf.setAppName("sql");
        sparkConf.setMaster("local[*]");
        sc = new JavaSparkContext(sparkConf);

    }

}