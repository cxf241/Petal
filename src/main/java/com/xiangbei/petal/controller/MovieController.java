package com.xiangbei.petal.controller;

import com.xiangbei.petal.pojo.Movie;
import com.xiangbei.petal.pojo.User;
import com.xiangbei.petal.service.MovieService;
import com.xiangbei.petal.service.UserService;
import com.xiangbei.petal.spark.Pre;
import com.xiangbei.petal.spark.als;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MovieController {
    @Autowired
    MovieService movieService;
    @Autowired
    UserService userService;
    @RequestMapping("filmInfo")
    public String getMovieById(@RequestParam(name="id")String id, Map<String,Object> map) {
        map.put("movie", movieService.getMovieById(id));
        map.put("recommends", movieService.getSimilarMovie(id));
        return "filmInfo";
    }

    @RequestMapping("/films")
    @ResponseBody
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @RequestMapping("/recommend")
    @ResponseBody
    public List<String> getRecommend(HttpServletRequest request) {
        return Pre.getRecommend(als.model,1,5);
    }

    @RequestMapping("searchResult")
    public String getMovieByKeyWordPage(@RequestParam(name="keyWord")String keyWord, @RequestParam(name="page", defaultValue="1")int page, Map<String,Object> map) {
        int total = movieService.getTotalCount(keyWord);
        map.put("keyWord", keyWord);
        map.put("currentPage", page);
        map.put("totalPage", total % 10 == 0 ? total / 10 : total / 10 + 1);
        map.put("results", movieService.getMovieByKeyWordPage(keyWord, page));
        return "searchResult";
    }

}
