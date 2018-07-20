package com.xiangbei.petal.spark;

import java.util.Properties;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

/**
 * 
 * @ClassName: sql 
 * @Description: 连接数据库，读取数据
 * @author: tyrion
 * @date: 2018年7月13日 下午8:08:41
 */
public class sql {
	 public static  JavaRDD<Row> getRow(JavaSparkContext sc){

		 SQLContext sqlContext = new SQLContext(sc);
		 
		 //设置数据库登录信息
		 String url = "jdbc:mysql://localhost:3306/petal";
		 String table = "rating";
		 Properties connectionProperties = new Properties();
		 connectionProperties.setProperty("dbtable", table);
		 connectionProperties.setProperty("user", "root");
		 connectionProperties.setProperty("password", "tracer");
		 connectionProperties.put("driver", "com.mysql.jdbc.Driver");

		 // 读取数据
		 Dataset<Row> jdbcDF = sqlContext.read().jdbc(url, table, connectionProperties).select("*");
		 
		 //打印表结构
		 //jdbcDF.printSchema();

		 return jdbcDF.javaRDD();
		 }
	
}

