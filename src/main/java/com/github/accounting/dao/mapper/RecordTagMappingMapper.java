package com.github.accounting.dao.mapper;

import com.github.accounting.model.persistence.RecordTagMapping;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordTagMappingMapper {

    @Insert({"<script>",
            "INSERT INTO as_record_tag(user_id, record_id, tag_id) VALUES ",
            "<foreach item='item' index='index' collection='recordTagMappings'",
            "open='(' separator = '),(' close=')'>",
            "#{item.userId}, #{item.recordId}, #{item.tagId}",
            "</foreach>",
            "</script>"})
    int batchRecordTagMapping(@Param("recordTagMappings") List<RecordTagMapping> recordTagMappings);

    @Delete("delete from as_record_tag where record_id = #{recordId} and user_id = #{userId}")
    void deleteRecordTagMapping(@Param("recordId") Long recordId, @Param("userId") Long userId);

    List<RecordTagMapping> getRecordTagMapping(List<Long> recordIds);
}
