package com.xiangbei.petal.service;

import com.xiangbei.petal.dao.MovieDao;
import com.xiangbei.petal.pojo.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        List<Movie> temp = new ArrayList<>();
        int i = 0;
        for (Movie movie : movieDao.getAllMovie()) {
            if (movie.getId() == id)
                break;
            String type = movie.getType();
            for(String t:types){
                if(type.contains(t)){
                    temp.add(movieDao.getMovieById(movie.getId()));
                    i++;
                    break;
                }
            }
            if (i > 9)
                break;
        }

        List<Movie> records = new ArrayList<>();

        //生成随机10个数
        Random rd = new Random();
        int NUM = 10;
        int[] rds = new int[NUM];
        List<Integer> lst = new ArrayList<>();
        int index;
        for(i = 0;i < NUM; i++){
            lst.add(i);
        }
        for(i = 0;i < NUM; i++){
            index=rd.nextInt(NUM-i);
            rds[i]=lst.get(index);
            lst.remove(index);
        }

        for(int j = 0; j < 4; j++) {
            index = rds[j];
            records.add(temp.get(index));
        }
        return records;
    }
}
