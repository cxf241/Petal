package com.xiangbei.petal.controller;

import com.xiangbei.petal.service.CollectionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @RequestMapping(value = "doCollect", method = RequestMethod.POST)
    public boolean changeCollect(@Param("collected")boolean collected, @Param("uId")int uId, @Param("movieId")int movieId) {
        if (collected) {//已收藏，需要取消
            return collectionService.deleteCollection(uId, movieId);
        }
        else {//未收藏，加入收藏
            return collectionService.addCollection(uId, movieId);
        }
    }

    @RequestMapping(value = "collected", method = RequestMethod.POST)
    public boolean searchCollect(@Param("uId")int uId, @Param("movieId")int movieId) {
        return collectionService.searchCollected(uId, movieId);
    }

}
