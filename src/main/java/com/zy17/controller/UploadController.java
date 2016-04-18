package com.zy17.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * 上传图片
 * Created by zhangyan53 on 2016/4/12.
 */
@Controller
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getPeopleListInfo(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("uploadImageGuessCard");
        //        ModelAndView mv = new ModelAndView("upload");
        return mv;
    }
}
