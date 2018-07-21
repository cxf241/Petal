/*
 * @author：陈旭峰
 *
 */
package com.xiangbei.petal.listener;

import com.xiangbei.petal.spark.InitializationThread;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        InitializationThread it = new InitializationThread("Initialization and Load");
        it.start();
    }
}
