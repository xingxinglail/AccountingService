package com.github.accounting.model.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageResponse<T> {

    private Integer pageNumber;

    private Integer pageSize;

    private Integer total;

    private T data;
}
