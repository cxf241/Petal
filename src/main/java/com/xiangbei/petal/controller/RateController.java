/*
 * 作者：陈旭峰
 *
 */

package com.xiangbei.petal.controller;

import com.xiangbei.petal.event.RateEvent;
import com.xiangbei.petal.pojo.Movie;
import com.xiangbei.petal.pojo.Rate;
import com.xiangbei.petal.pojo.Record;
import com.xiangbei.petal.service.CollectionService;
import com.xiangbei.petal.service.MovieService;
import com.xiangbei.petal.service.RateService;
import org.apache.spark.mllib.recommendation.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class RateController {
    @Autowired
    RateService rateService;
    @Autowired
    CollectionService collectionService;
    @Autowired
    MovieService movieService;

    @Autowired
    ApplicationContext context;
    @RequestMapping(value = "doRate",method = RequestMethod.POST)
    @ResponseBody
    public boolean addRate(@RequestParam("uId")int uId, @RequestParam("movieId")int movieId, @RequestParam("score")double score) {
        Rate rate = new Rate();
        rate.setuId(uId);
        rate.setMovieId(movieId);
        rate.setScore(score);
        context.publishEvent(new RateEvent(this, new Rating(uId, movieId, score)));
        return rateService.addRate(rate) > 0;
    }

    @RequestMapping(value = "rated",method = RequestMethod.POST)
    @ResponseBody
    public double searchRate(@RequestParam("uId")int uId, @RequestParam("movieId")int movieId) {
        return rateService.getRate(uId, movieId);
    }

    @RequestMapping("userInfo")
    public String getUserInfo(@RequestParam("id")int id, Map<String,Object> map) {

        List<Movie> collections = new ArrayList<>();
        for (Integer i :collectionService.getAllCollections(id)) {
            collections.add(movieService.getMovieById(i.toString()));
        }

        List<Record> rates = new ArrayList<>();

        for (Rate r : rateService.getAllRate(id)) {
            Record record = new Record();
            String mid = String.valueOf(r.getMovieId());
            Movie movie = movieService.getMovieById(mid);
            record.setdScore(movie.getRating());
            record.setId(mid);
            record.setName(movie.getName());
            record.setScore(r.getScore() * 2);
            rates.add(record);
        }

        map.put("collections", collections);
        map.put("rates", rates);
        return "userInfo";
    }
}
