package com.github.accounting.manager;

import com.github.accounting.UserContext;
import com.github.accounting.dao.RecordDaoImpl;
import com.github.accounting.dao.RecordTagMappingDaoImpl;
import com.github.accounting.dao.TagDaoImpl;
import com.github.accounting.exception.InvalidParameterException;
import com.github.accounting.model.persistence.Record;
import com.github.accounting.model.persistence.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public Record createRecord(Record record) {
        Long userId = UserContext.getCurrentUser().getId();
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
        record.setUserId(userId);
        recordDao.createRecord(record);
        recordTagMappingDao.batchRecordTagMapping(record.getId(), tagList);
        return null;
    }
}
