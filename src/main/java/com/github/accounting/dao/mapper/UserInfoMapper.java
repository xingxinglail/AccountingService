package com.github.accounting.dao.mapper;

import com.github.accounting.model.persistence.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO as_userinfo(username, password, salt) VALUES (#{username},#{password},#{salt})")
    void createUser(UserInfo userInfo);

    @Select("SELECT id, username, password, salt, create_time, update_time FROM as_userinfo WHERE username = #{username}")
    UserInfo getUserInfoByUserName(String username);
}
