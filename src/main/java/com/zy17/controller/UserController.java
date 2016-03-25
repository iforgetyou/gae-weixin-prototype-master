package com.zy17.controller;

import com.zy17.entity.UserEntity;
import com.zy17.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户操作
 */
@Controller
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    // 获取用户信息
    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    UserEntity getUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("岩仔3");
        log.debug(userEntity.toString());
        return userEntity;
    }


    // 用户注册
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    void createUser(@RequestBody UserEntity userEntity) {
        userService.createUser(userEntity);
        log.debug("create" + userEntity);
    }

}