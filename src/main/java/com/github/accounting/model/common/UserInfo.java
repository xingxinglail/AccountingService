package com.github.accounting.model.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {

    private Long id;

    private String username;

    private String password;

    private String salt;
}
