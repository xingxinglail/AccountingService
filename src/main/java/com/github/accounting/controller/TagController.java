package com.github.accounting.controller;

import com.github.accounting.exception.InvalidParameterException;
import com.github.accounting.manager.TagManager;
import com.github.accounting.model.service.Tag;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1.0/tag")
public class TagController {

    private final TagManager tagManager;

    @Autowired
    public TagController(TagManager tagManager) {
        this.tagManager = tagManager;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") Long id) {
        val tag = tagManager.getTagById(id);
        return ResponseEntity.ok(Tag.builder().id(tag.getId()).description(tag.getDescription()).build());
    }

    @PostMapping("")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        val newTag = tagManager.createTag(tag.getDescription());
        return ResponseEntity.ok(Tag.builder().id(newTag.getId()).description(newTag.getDescription()).build());
    }

    @PutMapping("")
    public void updateTag(@RequestBody Tag tag) {
        Long id = tag.getId();
        if (id == null || id < 0L) {
            throw new InvalidParameterException("参数错误");
        }
        tagManager.updateTag(com.github.accounting.model.persistence.Tag.builder().id(id).description(tag.getDescription()).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteTag(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tagManager.deleteTagById(id));
    }
}
