/*
 * @author：陈旭峰
 *
 */
package com.xiangbei.petal.service;

import com.xiangbei.petal.dao.CollectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    CollectionDao collectionDao;


    @Override
    public List<Integer> getAllCollections(int uId) {
        return collectionDao.getAllCollections(uId);
    }

    @Override
    public boolean addCollection(int uId, int movieId) {
        return collectionDao.addCollection(uId, movieId) > 0;
    }

    @Override
    public boolean deleteCollection(int uId, int movieId) {
        return collectionDao.deleteCollection(uId, movieId) > 0;
    }

    @Override
    public boolean searchCollected(int uId, int movieId) {
        return collectionDao.getCollection(uId, movieId) != null;
    }
}
