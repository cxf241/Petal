package com.xiangbei.petal;

import com.xiangbei.petal.service.RateService;
import com.xiangbei.petal.spark.als;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetalApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetalApplication.class, args);
	}
}
