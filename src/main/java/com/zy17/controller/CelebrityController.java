package com.zy17.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zy17.dao.ImageItemDao;
import com.zy17.dto.AnswerResultDto;
import com.zy17.dto.CelebrityImageDto;
import com.zy17.dto.ImagecardVo;
import com.zy17.entity.ImageItem;
import com.zy17.service.impl.ServiceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 用户操作
 */
@Controller
@RequestMapping("/celebrity")
@Slf4j
public class CelebrityController {
    @Autowired
    ServiceUtil util;
    @Autowired
    ImageItemDao dao;

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    CelebrityImageDto getImage(@RequestParam(value = "username") String username) {
        ImageItem imageItem = util.genRandomImage(username);
        if (imageItem == null) {
            log.info("no image item for username:{}", username);
            return null;
        }
        CelebrityImageDto dto = new CelebrityImageDto();
        dto.setPicUrl(imageItem.getPicUrl());
        dto.setTags(imageItem.getTags());
        dto.setType(imageItem.getType());
        return dto;
    }

    @RequestMapping(value = "/card", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    ImagecardVo card(@RequestParam(value = "username", required = false) String username) {
        ImagecardVo imagecardVo = new ImagecardVo();
        ImageItem imageItem = util.genRandomImage(username);
        if (imageItem == null) {
            log.info("no image item for username:{}", username);
            imagecardVo.setStatus(ImagecardVo.NoMoreCard);
            return imagecardVo;
        }
        String tags = imageItem.getTags();
        // 找到真实答案
        // 找到其他同类型答案
        // 混合设置返回结果
        imagecardVo.setCardId(String.valueOf(imageItem.getID()));
        imagecardVo.setAnswerA(tags);
        imagecardVo.setAnswerB("库里");
        imagecardVo.setAnswerC("詹姆斯");
        imagecardVo.setAnswerD("周立波");
        imagecardVo.setImage(imageItem.getPicUrl());
        log.info("return card result" + JSON.toJSONString(imagecardVo));
        return imagecardVo;
    }

    @RequestMapping(value = "/check", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    AnswerResultDto guess(@RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "cardid", required = false) long cardId,
                          @RequestParam(value = "answer_name", required = false) String answerName) {
        log.info("check answer cardId:{} ,user:{} ,guess:{}", new Object[] {cardId, username, answerName});
        ImageItem oneById = dao.findOneById(cardId);

        AnswerResultDto result = new AnswerResultDto();
        if (oneById.getTags().equals(answerName)) {
            // 答案正确
            // 已经答过的题目, 放入缓存
            util.statistics(username, true, oneById.getID());
        } else {
            result.setStatus(AnswerResultDto.WRONG);
        }
        return result;
    }
}

