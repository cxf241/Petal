/*
 * @author：陈旭峰
 *
 */
package com.xiangbei.petal.listener;

import com.xiangbei.petal.event.RateEvent;
import com.xiangbei.petal.spark.SparkCommend;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class RateListener implements ApplicationListener<RateEvent> {
    @Async
    public void onApplicationEvent(RateEvent event)
    {
        System.out.println("Rate");
        SparkCommend.commendMovies(event.getRating());
    }

}
