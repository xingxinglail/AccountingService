package com.github.accounting;


import com.github.accounting.model.common.UserInfo;

public class UserContext {

    private static ThreadLocal<UserInfo> currentUser = new ThreadLocal<>();

    public static UserInfo getCurrentUser() {
        return currentUser.get();
    }

    public static void setCurrentUser(UserInfo userInfo) {
        currentUser.set(userInfo);
    }
}
