package com.github.accounting.manager;

import com.github.accounting.model.persistence.Record;
import com.github.accounting.model.service.PageResponse;

import java.util.List;

public interface RecordManager {

    Long createRecord(Record record);

    void updateRecord(Record record);

    PageResponse<List<Record>> getRecordList(Integer pageSize, Integer pageNumber);
}
