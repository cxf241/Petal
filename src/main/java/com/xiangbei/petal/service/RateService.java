package com.xiangbei.petal.service;

import com.xiangbei.petal.pojo.Rate;

import java.util.List;

public interface RateService {
    int addRate(Rate rate);
    List<Rate> getAllRate(int uId);
    double getRate(int uId, int movieId);
}
