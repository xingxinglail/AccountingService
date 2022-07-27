package com.github.accounting.dao;

import com.github.accounting.model.persistence.UserInfo;

public interface UserInfoDao {

    void createUser(UserInfo userInfo);

    UserInfo getUserInfoByUserName(String username);
}
