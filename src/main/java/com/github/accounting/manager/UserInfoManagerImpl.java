package com.github.accounting.manager;

import com.github.accounting.converter.p2c.UserInfoP2CConverter;
import com.github.accounting.dao.UserInfoDao;
import com.github.accounting.exception.InvalidParameterException;
import com.github.accounting.exception.ResourceNotFoundException;
import com.github.accounting.model.common.UserInfo;
import lombok.val;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserInfoManagerImpl implements UserInfoManager {

    public static final int HASH_ITERATIONS = 1000;

    private final UserInfoDao userInfoDao;
    private final UserInfoP2CConverter userInfoP2CConverter;

    @Autowired
    public UserInfoManagerImpl(UserInfoDao userInfoDao, UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDao = userInfoDao;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public UserInfo register(String username, String password) {
        val userInfo = userInfoDao.getUserInfoByUserName(username);
        if (userInfo != null) {
            throw new InvalidParameterException(String.format("The user %s was registered.", username));
        }
        String salt = UUID.randomUUID().toString();
        String encryptedPassword = new Sha256Hash(password, salt, HASH_ITERATIONS).toBase64();
        val newUserInfo = com.github.accounting.model.persistence.UserInfo.builder()
                .username(username)
                .password(encryptedPassword)
                .salt(salt)
                .build();
        try {
            userInfoDao.createUser(newUserInfo);
            return userInfoP2CConverter.convert(newUserInfo);
        } catch (DuplicateKeyException exception) {
            throw new InvalidParameterException(String.format("The user %s was registered.", username), exception);
        }
    }

    @Override
    public UserInfo getUserInfoByUserName(String username) {
        val userinfo = Optional.ofNullable(userInfoDao.getUserInfoByUserName(username))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User name %s was not found", username)));
        return userInfoP2CConverter.convert(userinfo);
    }

    @Override
    public void login(String username, String password) {
        val subject = SecurityUtils.getSubject();
        val usernamePasswordToken = new UsernamePasswordToken(username, password);
        subject.login(usernamePasswordToken);
    }
}
