package com.xiangbei.petal.service;

import com.xiangbei.petal.pojo.Rate;
import org.apache.spark.mllib.recommendation.Rating;

import java.util.List;

public interface RateService {
    int addRate(Rate rate);
    List<Rate> getAllRate(int uId);
    double getRate(int uId, int movieId);
    List<Rating> getRates();

}
