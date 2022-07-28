package com.github.accounting.model.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tag {

    private Long id;

    private String description;
}
