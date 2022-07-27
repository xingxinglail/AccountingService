package com.github.accounting.model.persistence;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserInfo {

    private Long id;

    private String username;

    private String password;

    private String salt;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
