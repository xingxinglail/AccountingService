package com.github.accounting.dao;

import com.github.accounting.dao.mapper.RecordMapper;
import com.github.accounting.model.persistence.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
