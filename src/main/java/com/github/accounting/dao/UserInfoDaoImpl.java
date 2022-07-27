package com.github.accounting.dao;

import com.github.accounting.dao.mapper.UserInfoMapper;
import com.github.accounting.model.persistence.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDaoImpl implements UserInfoDao {

    private final UserInfoMapper userInfoMapper;

    @Autowired
    public UserInfoDaoImpl (UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public void createUser(UserInfo userInfo) {
        userInfoMapper.createUser(userInfo);
    }

    @Override
    public UserInfo getUserInfoByUserName(String username) {
        return userInfoMapper.getUserInfoByUserName(username);
    }
}
