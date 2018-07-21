package com.xiangbei.petal.event;

import org.apache.spark.mllib.recommendation.Rating;
import org.springframework.context.ApplicationEvent;

public class RateEvent extends ApplicationEvent {

    private Rating rating;

    public RateEvent(Object source, Rating rating) {
        super(source);
        this.rating = rating;
    }

    public Rating getRating() {
        return rating;
    }
}
