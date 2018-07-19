package com.xiangbei.petal.controller;

import com.xiangbei.petal.pojo.Rate;
import com.xiangbei.petal.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RateController {
    @Autowired
    RateService rateService;

    @RequestMapping(value = "doRate",method = RequestMethod.POST)
    @ResponseBody
    public boolean addRate(@RequestParam("uId")int uId, @RequestParam("movieId")int movieId, @RequestParam("score")double score) {
        Rate rate = new Rate();
        rate.setuId(uId);
        rate.setMovieId(movieId);
        rate.setScore(score);
        return rateService.addRate(rate) > 0;
    }

    @RequestMapping(value = "rated",method = RequestMethod.POST)
    @ResponseBody
    public double searchRate(@RequestParam("uId")int uId, @RequestParam("movieId")int movieId) {
        return rateService.getRate(uId, movieId);
    }
}
