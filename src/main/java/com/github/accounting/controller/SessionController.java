package com.github.accounting.controller;

import com.github.accounting.manager.UserInfoManager;
import com.github.accounting.model.service.UserInfo;
import lombok.val;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1.0/session")
public class SessionController {

    private static final String SUCCESS = "success";

    private static final String STATUS = "status";

    private static final String USERNAME = "username";

    private static final int OK = 200;

    private static final int NOT_FOUND = 400;

    private final UserInfoManager userInfoManager;

    @Autowired
    public SessionController(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @PostMapping
    public Map<String, String> login(@RequestBody UserInfo userInfo) {
        val response = new HashMap<String, String>();
        userInfoManager.login(userInfo.getUsername(), userInfo.getPassword());
        response.put(STATUS, SUCCESS);
        response.put(USERNAME, userInfo.getUsername());
        return response;
    }
}
