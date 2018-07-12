package com.xiangbei.petal.service;

import com.xiangbei.petal.dao.MovieDao;
import com.xiangbei.petal.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
