package com.github.accounting.manager;

import com.github.accounting.model.common.UserInfo;

public interface UserInfoManager {

    UserInfo register(String username, String password);
}
