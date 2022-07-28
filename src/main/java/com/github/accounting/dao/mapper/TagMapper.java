package com.github.accounting.dao.mapper;

import com.github.accounting.dao.provider.TagSqlProvider;
import com.github.accounting.model.persistence.Tag;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TagMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO as_tag (user_id, description) values (#{userId}, #{description})")
    void createTag(Tag tag);

    @Select("SELECT id, user_id as userId, description, status, create_time as createTime, update_time as updateTime FROM as_tag WHERE id = #{id} AND user_id = #{userId} AND status = 1")
    Tag getTagById(@Param("id") Long id, @Param("userId") Long userId);

    @Options(resultSets = "description")
    @UpdateProvider(type = TagSqlProvider.class, method = "updateTag")
    int updateTag(Tag tag);

    @Options(resultSets = "status")
    @UpdateProvider(type = TagSqlProvider.class, method = "updateTag")
    int deleteTag(Tag tag);

    @Select("SELECT id, user_id as userId, description, status, create_time as createTime, update_time as updateTime FROM as_tag WHERE description = #{description} AND user_id = #{userId} AND status = 1")
    Tag getTagByDescription(String description, Long userId);
}
