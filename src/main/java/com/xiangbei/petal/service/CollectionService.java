package com.xiangbei.petal.service;

import com.xiangbei.petal.pojo.Collection;

import java.util.List;

public interface CollectionService {
    List<Integer> getAllCollections(int uId);
    boolean addCollection(int uId, int movieId);
    boolean deleteCollection(int uId, int movieId);
    boolean searchCollected(int uId, int movieId);
}
