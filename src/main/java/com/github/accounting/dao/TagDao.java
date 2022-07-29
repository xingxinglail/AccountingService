package com.github.accounting.dao;

import com.github.accounting.model.persistence.Tag;

import java.util.List;

public interface TagDao {

    void createTag(Tag tag);

    int updateTag(Tag tag);

    int deleteTagById(Long id, Long userId);

    Tag getTagById(Long id, Long userId);

    Tag getTagByDescription(String description, Long userId);

    List<Tag> getTagListByIds(List<Long> ids);
}
