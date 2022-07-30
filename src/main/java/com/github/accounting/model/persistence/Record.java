package com.github.accounting.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
