package com.github.accounting.dao.mapper;

import com.github.accounting.model.persistence.Record;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RecordMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO as_record (user_id, amount, category, note) values (#{userId}, #{amount}, #{category},#{note})")
    void createRecord(Record record);
}
