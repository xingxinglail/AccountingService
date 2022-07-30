package com.github.accounting.dao;

import com.github.accounting.dao.mapper.RecordMapper;
import com.github.accounting.model.persistence.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecordDaoImpl {

    private final RecordMapper recordMapper;

    @Autowired
    public RecordDaoImpl(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    public void createRecord(Record record) {
        recordMapper.createRecord(record);
    }

    public void updateRecord(Record record) {
        recordMapper.updateRecord(record);
    }

    public List<Record> getRecordList(Long userId, Integer start, Integer end) {
        return recordMapper.getRecordList(userId, start, end);
    }

    public Integer countRecordList(Long userId) {
        return recordMapper.countRecordList(userId);
    }
}
