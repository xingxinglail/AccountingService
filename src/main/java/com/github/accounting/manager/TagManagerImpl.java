package com.github.accounting.manager;

import com.github.accounting.UserContext;
import com.github.accounting.dao.RecordDaoImpl;
import com.github.accounting.dao.TagDaoImpl;
import com.github.accounting.exception.InvalidParameterException;
import com.github.accounting.exception.ResourceNotFoundException;
import com.github.accounting.model.persistence.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TagManagerImpl implements TagManager {

    private final TagDaoImpl tagDao;

    @Autowired
    public TagManagerImpl(TagDaoImpl tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag createTag(String description) {
        Long userId = UserContext.getCurrentUser().getId();
        Optional.ofNullable(getTagByDescription(description, userId)).ifPresent((tag) -> {
            throw new InvalidParameterException("tag 已存在");
        });
        Tag tag = Tag.builder().userId(userId).description(description).build();
        tagDao.createTag(tag);
        return tag;
    }

    @Override
    public void updateTag(Tag tag) {
        Optional.ofNullable(getTagById(tag.getId())).orElseThrow(() -> new ResourceNotFoundException("tag 不存在"));
        Long userId = UserContext.getCurrentUser().getId();
        tag.setUserId(userId);
        tagDao.updateTag(tag);
    }

    @Override
    public int deleteTagById(Long id) {
        Long userId = UserContext.getCurrentUser().getId();
        Optional.ofNullable(getTagById(id)).orElseThrow(() -> new ResourceNotFoundException("tag 不存在"));
        return tagDao.deleteTagById(id, userId);
    }

    @Override
    public Tag getTagById(Long id) {
        Long userId = UserContext.getCurrentUser().getId();
        Tag tag = tagDao.getTagById(id, userId);
        if (tag == null) {
            throw new ResourceNotFoundException("tag 不存在");
        }
        return tag;
    }

    @Override
    public Tag getTagByDescription(String description, Long userId) {
        return tagDao.getTagByDescription(description, userId);
    }

    @Override
    public List<Tag> getTagListByIds(List<Long> ids) {
        return tagDao.getTagListByIds(ids);
    }
}
