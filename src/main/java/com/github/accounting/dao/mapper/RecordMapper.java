package com.github.accounting.dao.mapper;

import com.github.accounting.model.persistence.Record;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO as_record (user_id, amount, category, note) values (#{userId}, #{amount}, #{category},#{note})")
    void createRecord(Record record);

    void updateRecord(Record record);

    Integer countRecordList(@Param("userId") Long userId);

    List<Record> getRecordList(@Param("userId") Long userId, @Param("start") Integer start, @Param("end") Integer end);
}
