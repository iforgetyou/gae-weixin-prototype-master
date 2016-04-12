package com.zy17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zy17.dto.CelebrityImageDto;
import com.zy17.dto.ResultVo;
import com.zy17.entity.ImageItem;
import com.zy17.service.impl.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户操作
 */
@Controller
@RequestMapping("/img")
@Slf4j
public class CelebrityController {
    @Autowired
    ServiceUtil util;

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    CelebrityImageDto getImage(@RequestParam(value = "username") String username) {
        ImageItem imageItem = util.genRandomImage(username);
        if (imageItem == null) {
            log.info("no image item {}", username);
            return null;
        }
        CelebrityImageDto dto = new CelebrityImageDto();
        dto.setPicUrl(imageItem.getPicUrl());
        dto.setTags(imageItem.getTags());
        dto.setType(imageItem.getType());
        return dto;
    }

    @RequestMapping(value = "/guess", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    ResultVo guess() {
        ImageItem imageItem = util.genRandomImage("test");
        if (imageItem == null) {
            log.info("no image item {}", "test");
            return null;
        }
        ResultVo resultVo = new ResultVo();
        resultVo.setAnswerA("A 江疏影");
        resultVo.setAnswerB("B 库里");
        resultVo.setAnswerC("C 傻逼詹姆斯");
        resultVo.setAnswerD("D 傻逼周立波");
        resultVo.setImage(imageItem.getPicUrl());
        log.info("json----" + JSON.toJSONString(resultVo));
        return resultVo;
    }
}

