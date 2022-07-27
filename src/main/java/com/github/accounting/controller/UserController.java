package com.github.accounting.controller;

import com.github.accounting.converter.c2s.UserInfoC2SConverter;
import com.github.accounting.manager.UserInfoManager;
import com.github.accounting.model.service.UserInfo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0/users")
public class UserController {

    private final UserInfoManager userInfoManager;

    private final UserInfoC2SConverter userInfoC2SConverter;

    @Autowired
    public UserController(UserInfoManager userInfoManager, UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    @PostMapping("")
    public ResponseEntity<UserInfo> register(@RequestBody UserInfo userInfo) {
        val register = userInfoManager.register(userInfo.getUsername(), userInfo.getPassword());
        return ResponseEntity.ok(userInfoC2SConverter.convert(register));
    }
}
