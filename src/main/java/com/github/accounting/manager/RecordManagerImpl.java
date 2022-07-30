package com.github.accounting.manager;

import com.github.accounting.UserContext;
import com.github.accounting.dao.RecordDaoImpl;
import com.github.accounting.dao.RecordTagMappingDaoImpl;
import com.github.accounting.dao.TagDaoImpl;
import com.github.accounting.exception.InvalidParameterException;
import com.github.accounting.model.persistence.Record;
import com.github.accounting.model.persistence.RecordTagMapping;
import com.github.accounting.model.persistence.Tag;
import com.github.accounting.model.service.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RecordManagerImpl implements RecordManager {

    private final TagDaoImpl tagDao;

    private final RecordDaoImpl recordDao;

    private final RecordTagMappingDaoImpl recordTagMappingDao;

    @Autowired
    public RecordManagerImpl(TagDaoImpl tagDao, RecordDaoImpl recordDao, RecordTagMappingDaoImpl recordTagMappingDao) {
        this.tagDao = tagDao;
        this.recordDao = recordDao;
        this.recordTagMappingDao = recordTagMappingDao;
    }

    @Override
    @Transactional
    public Long createRecord(Record record) {
        Long userId = UserContext.getCurrentUser().getId();
        List<Tag> tagList = tagValidate(userId, record);
        record.setUserId(userId);
        recordDao.createRecord(record);
        recordTagMappingDao.batchRecordTagMapping(record.getId(), tagList);
        return record.getId();
    }

    @Override
    @Transactional
    public void updateRecord(Record record) {
        Long userId = UserContext.getCurrentUser().getId();
        List<Tag> tagList = tagValidate(userId, record);
        record.setUserId(userId);
        recordDao.updateRecord(record);
        recordTagMappingDao.deleteRecordTagMapping(record.getId(), userId);
        recordTagMappingDao.batchRecordTagMapping(record.getId(), tagList);
    }

    @Override
    public PageResponse<List<Record>> getRecordList(Integer pageSize, Integer pageNumber) {
        Long userId = UserContext.getCurrentUser().getId();
        int start = (1 - pageSize) * pageNumber;
        Integer total = recordDao.countRecordList(userId);
        List<Record> recordList = new ArrayList<>();
        if (total > 0) {
            recordList = recordDao.getRecordList(userId, start, pageNumber);
            List<RecordTagMapping> recordTagMappingList = recordTagMappingDao.getRecordTagMapping(recordList.stream().map(Record::getId).collect(Collectors.toList()));
            for (Record record : recordList) {
                List<Tag> tagList = new ArrayList<>();
                for (RecordTagMapping recordTagMapping : recordTagMappingList) {
                    if (record.getId().equals(recordTagMapping.getRecordId())) {
                        tagList.add(Tag.builder()
                                .id(recordTagMapping.getTagId())
                                .description(recordTagMapping.getTagDescription())
                                .build());
                    }
                }
                record.setTagList(tagList);
            }
        }
        return PageResponse.<List<Record>>builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .total(total)
                .data(recordList)
                .build();
    }

    private List<Tag> tagValidate(Long userId, Record record) {
        List<Long> tagIds = record.getTagList().stream().map(Tag::getId).collect(Collectors.toList());
        List<Tag> tagList = tagDao.getTagListByIds(tagIds);
        if (tagList.isEmpty()) {
            throw new InvalidParameterException(String.format("The tag list %s are not existed.", tagIds));
        }
        tagList.forEach(tag -> {
            if (!userId.equals(tag.getUserId())) {
                throw new InvalidParameterException("The tag is not matched for user");
            }
        });
        return tagList;
    }
}
