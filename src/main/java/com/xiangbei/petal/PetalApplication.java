package com.xiangbei.petal;

import com.xiangbei.petal.listener.ApplicationStartup;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetalApplication {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(PetalApplication.class);
		springApplication.addListeners(new ApplicationStartup());
		springApplication.run(args);
	}
}
