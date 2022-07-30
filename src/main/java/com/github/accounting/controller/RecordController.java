package com.github.accounting.controller;

import com.github.accounting.exception.InvalidParameterException;
import com.github.accounting.manager.RecordManager;
import com.github.accounting.model.persistence.Record;
import com.github.accounting.model.service.PageResponse;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0/record")
public class RecordController {

    private final RecordManager recordManager;

    @Autowired
    public RecordController(final RecordManager recordManager, RecordManager recordManager1) {
        this.recordManager = recordManager1;
    }

    @GetMapping
    public ResponseEntity<PageResponse<List<Record>>> getRecordList(@RequestParam("pageSize") Integer pageSize, @RequestParam("pageNumber") Integer pageNumber) {
        return ResponseEntity.ok(recordManager.getRecordList(pageSize, pageNumber));
    }

    @PostMapping
    public ResponseEntity<Long> addRecord(@RequestBody Record record) {
        if (checkRecord(record)) {
            throw new InvalidParameterException("参数错误");
        }
        return ResponseEntity.ok(recordManager.createRecord(record));
    }

    @PutMapping
    public ResponseEntity<Object> updateRecord(@RequestBody Record record) {
        if (record.getId() == null || checkRecord(record)) {
            throw new InvalidParameterException("参数错误");
        }
        recordManager.updateRecord(record);
        return ResponseEntity.ok(null);
    }

    private boolean checkRecord(Record record) {
        return CollectionUtils.isEmpty(record.getTagList())
                || record.getAmount() == null
                || record.getCategory() == null;
    }
}
