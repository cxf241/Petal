package com.xiangbei.petal.service;

import com.xiangbei.petal.dao.MovieDao;
import com.xiangbei.petal.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieDao movieDao;

    @Override
    public Movie getMovieById(String id) {
        return movieDao.getMovieById(id);
    }

    @Override
    public List<Movie> getMovies() {
        return movieDao.getMovies();
    }

    @Override
    public List<Integer> getRecommend(int userId) {
        return movieDao.getRecommend(userId);
    }

    @Override
    public Integer getTotalCount(String keyWord) {
        return movieDao.getTotalCount("%" + keyWord + "%");
    }

    @Override
    public List<Movie> getMovieByKeyWordPage(String keyWord, int page) {
        return movieDao.getMovieByKeyWordPage("%" + keyWord + "%", (page - 1) * 10);
    }

    @Override
    public List<Movie> getSimilarMovie(String id) {
        String movieType = movieDao.getTypeById(Integer.parseInt(id));
        movieType = movieType.trim();
        String[] types = movieType.split("\\s+");
        // 列表用于存储记录
        List<Movie> records = new ArrayList<>();
        int i = 0;
        for (Movie movie : movieDao.getAllMovie()) {
            String type = movie.getType();
            for(String t:types){
                if(type.contains(t)){
                    records.add(movieDao.getMovieById(movie.getId()));
                    i++;
                    break;
                }
            }
            if (i > 3)
                break;
        }
        return records;
    }
}
