package com.github.accounting.converter.p2c;

import com.github.accounting.model.persistence.UserInfo;
import com.google.common.base.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserInfoP2CConverter extends Converter<UserInfo, com.github.accounting.model.common.UserInfo> {

    @Override
    protected com.github.accounting.model.common.UserInfo doForward(UserInfo userInfo) {
        return com.github.accounting.model.common.UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .salt(userInfo.getSalt())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.github.accounting.model.common.UserInfo userInfo) {
        return UserInfo.builder()
                .id(userInfo.getId())
                .username(userInfo.getUsername())
                .password(userInfo.getPassword())
                .salt(userInfo.getSalt())
                .build();
    }
}
