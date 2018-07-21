/*
 * @author：李放
 *
 */

package com.xiangbei.petal.spark;


import java.io.Serializable;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.Rating;
import org.apache.spark.sql.SQLContext;


public class SparkConfig implements Serializable{
    protected static SparkConf sparkConf;
    protected static JavaSparkContext sc;
    protected static JavaRDD<Rating> ratings;
    protected static SQLContext sqlContext;

    //设置数据库登录信息
    protected static String url = "jdbc:mysql://localhost:3306/petal";
    protected static String table = "rate";
    protected static Properties connectionProperties = new Properties();


    public static void initialization(){
        connectionProperties.setProperty("dbtable", table);
        connectionProperties.setProperty("user", "root");
        connectionProperties.setProperty("password", "123456");
        connectionProperties.put("driver", "com.mysql.jdbc.Driver");
        sparkConf = new SparkConf();
        sparkConf.setAppName("sql");
        sparkConf.setMaster("local[*]");
        sc = new JavaSparkContext(sparkConf);
        sqlContext = new SQLContext(sc);
    }
}