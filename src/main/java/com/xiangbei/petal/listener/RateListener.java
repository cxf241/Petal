package com.xiangbei.petal.listener;

import com.xiangbei.petal.event.RateEvent;
import com.xiangbei.petal.spark.als;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RateListener implements ApplicationListener<RateEvent> {
    public void onApplicationEvent(RateEvent event)
    {
        als.ChangeModel(event.getRating());
    }

}
