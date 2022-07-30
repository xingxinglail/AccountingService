package com.github.accounting.dao;

import com.github.accounting.dao.mapper.RecordTagMappingMapper;
import com.github.accounting.model.persistence.RecordTagMapping;
import com.github.accounting.model.persistence.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RecordTagMappingDaoImpl {

    private final RecordTagMappingMapper recordTagMappingMapper;

    @Autowired
    public RecordTagMappingDaoImpl(RecordTagMappingMapper recordTagMappingMapper) {
        this.recordTagMappingMapper = recordTagMappingMapper;
    }

    public void batchRecordTagMapping(Long recordId, List<Tag> tagList) {
        List<RecordTagMapping> recordTagMappings = tagList.stream()
                .map(tag -> RecordTagMapping.builder()
                        .tagId(tag.getId())
                        .userId(tag.getUserId())
                        .recordId(recordId)
                        .build())
                .collect(Collectors.toList());
        recordTagMappingMapper.batchRecordTagMapping(recordTagMappings);
    }

    public void deleteRecordTagMapping(Long recordId, Long userId) {
        recordTagMappingMapper.deleteRecordTagMapping(recordId, userId);
    }

    public List<RecordTagMapping> getRecordTagMapping(List<Long> recordIds) {
        return recordTagMappingMapper.getRecordTagMapping(recordIds);
    }
}
