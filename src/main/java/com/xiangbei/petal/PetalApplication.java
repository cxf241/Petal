package com.xiangbei.petal;

import com.xiangbei.petal.listener.ApplicationStartup;
import com.xiangbei.petal.listener.RateListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PetalApplication {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(PetalApplication.class);
		springApplication.addListeners(new ApplicationStartup());
		springApplication.addListeners(new RateListener());
		springApplication.run(args);
	}
}
