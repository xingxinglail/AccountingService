package com.github.accounting.model.persistence;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Record {

    private Long id;

    private Long userId;

    private BigDecimal amount;

    private Integer category;

    private Integer status;

    private String note;

    private List<Tag> tagList;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
