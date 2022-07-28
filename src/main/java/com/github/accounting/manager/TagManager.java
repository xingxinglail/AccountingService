package com.github.accounting.manager;

import com.github.accounting.model.persistence.Tag;

public interface TagManager {

    Tag createTag(String description);

    void updateTag(Tag tag);

    int deleteTagById(Long id);

    Tag getTagById(Long id);

    Tag getTagByDescription(String description, Long userId);
}
