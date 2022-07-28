package com.github.accounting.model.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // 值为 null 不返回该字段
public class UserInfo {

    private Long id;

    private String username;

    private String password;
}
