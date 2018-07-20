package com.xiangbei.petal.listener;

import com.xiangbei.petal.service.RateService;
import com.xiangbei.petal.spark.als;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        RateService service = contextRefreshedEvent.getApplicationContext().getBean(RateService.class);
        als.Init(service.getRates());
    }
}
