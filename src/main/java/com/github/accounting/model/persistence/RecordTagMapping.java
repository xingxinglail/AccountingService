package com.github.accounting.model.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordTagMapping {

    private Long id;

    private Long userId;

    private Long recordId;

    private Long tagId;

    private LocalDateTime create_time;
}
