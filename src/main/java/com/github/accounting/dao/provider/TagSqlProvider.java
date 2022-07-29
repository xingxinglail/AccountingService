package com.github.accounting.dao.provider;

import com.github.accounting.model.persistence.Tag;
import com.google.common.base.Joiner;
import com.mysql.cj.util.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

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

    public String getTagListByIds(@Param("id") final List<Long> ids) {
        return new SQL() {
            {
                SELECT("id", "description", "user_id as userId");
                FROM("as_tag");
                WHERE("status = 1 AND " + String.format("id in ('%s')", Joiner.on("','").skipNulls().join(ids)));
            }
        }.toString();
    }
}
