package com.github.accounting.dao;

import com.github.accounting.dao.mapper.TagMapper;
import com.github.accounting.model.persistence.Record;
import com.github.accounting.model.persistence.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {

    private final TagMapper tagMapper;

    @Autowired
    public TagDaoImpl(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public void createTag(Tag tag) {
        tagMapper.createTag(tag);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }

    @Override
    public int deleteTagById(Long id, Long userId) {
        Tag tag = Tag.builder().id(id).userId(userId).status(0).build();
        return tagMapper.updateTag(tag);
    }

    @Override
    public Tag getTagById(Long id, Long userId) {
        return tagMapper.getTagById(id, userId);
    }

    @Override
    public Tag getTagByDescription(String description, Long userId) {
        return tagMapper.getTagByDescription(description, userId);
    }

    @Override
    public List<Tag> getTagListByIds(List<Long> ids) {
        return tagMapper.getTagListByIds(ids);
    }
}
