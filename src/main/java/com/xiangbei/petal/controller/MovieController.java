package com.xiangbei.petal.controller;

import com.xiangbei.petal.pojo.Movie;
import com.xiangbei.petal.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class MovieController {
    @Autowired
    MovieService movieService;
    @RequestMapping("/filmInfo")
    public String getMovieById(@RequestParam(name="id")String id, Map<String,Object> map) {
        map.put("movie", movieService.getMovieById(id));
        return "/filmInfo";
    }

    @RequestMapping("/films")
    @ResponseBody
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

}
