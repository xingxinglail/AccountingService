package com.github.accounting.controller;

import com.github.accounting.exception.InvalidParameterException;
import com.github.accounting.manager.RecordManager;
import com.github.accounting.model.persistence.Record;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0/record")
public class RecordController {

    private final RecordManager recordManager;

    @Autowired
    public RecordController(final RecordManager recordManager, RecordManager recordManager1) {
        this.recordManager = recordManager1;
    }

    @PostMapping
    public ResponseEntity<Record> addRecord(@RequestBody Record record) {
        if (checkRecord(record)) {
            throw new InvalidParameterException("参数错误");
        }
        Record record1 = recordManager.createRecord(record);
        System.out.println(record1);
        return ResponseEntity.ok(null);
    }

    private boolean checkRecord(Record record) {
        return CollectionUtils.isEmpty(record.getTagList())
                || record.getAmount() == null
                || record.getCategory() == null;
    }
}
