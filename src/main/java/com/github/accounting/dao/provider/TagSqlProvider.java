package com.github.accounting.dao.provider;

import com.github.accounting.model.persistence.Tag;
import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.jdbc.SQL;

public class TagSqlProvider {

    public String updateTag(final Tag tag) {
        return new SQL() {
            {
                UPDATE("as_tag");
                if (!StringUtils.isNullOrEmpty(tag.getDescription())) {
                    SET("description = #{description}");
                }
                if (tag.getStatus() != null) {
                    SET("status = #{status}");
                }
                WHERE("id = #{id} AND user_id = #{userId}");
            }
        }.toString();
    }
}
