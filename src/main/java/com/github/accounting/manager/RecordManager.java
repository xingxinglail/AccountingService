package com.github.accounting.manager;

import com.github.accounting.model.persistence.Record;

public interface RecordManager {

    Record createRecord(Record record);
}
