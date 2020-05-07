package org.springboot.chapter.chapter01.controller;

import org.springboot.chapter.chapter01.service.GoodInfoService;
import org.springboot.chapter.chapter01.entity.GoodInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/good")
public class GoodController {

    private final GoodInfoService goodInfoService;
    @Autowired
    public GoodController(GoodInfoService goodInfoService) {
        this.goodInfoService = goodInfoService;
    }

    @RequestMapping(value = "/save")
    public Long save(GoodInfoEntity good) throws Exception{
        return goodInfoService.save(good);
    }
}
