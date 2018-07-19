package com.xiangbei.petal.service;

import com.xiangbei.petal.dao.RateDao;
import com.xiangbei.petal.pojo.Rate;
import org.apache.spark.mllib.recommendation.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RateServiceImpl implements RateService {
    @Autowired
    RateDao rateDao;
    @Override
    public int addRate(Rate rate) {
        return rateDao.addRate(rate);
    }

    @Override
    public List<Rate> getAllRate(int uId) {
        return rateDao.getAllRate(uId);
    }

    @Override
    public double getRate(int uId, int movieId) {
        return rateDao.getRate(uId, movieId) == null ? 0.0 : rateDao.getRate(uId, movieId);
    }

    @Override
    public List<Rating> getRates() {
        List<Rating> list = new ArrayList<>();
        for (Rate r : rateDao.getRates())
            list.add(new Rating(r.getuId(),r.getMovieId(), r.getScore()));
        return list;
    }
}
