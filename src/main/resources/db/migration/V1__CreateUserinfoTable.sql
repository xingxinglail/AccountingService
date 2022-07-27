CREATE TABLE as_userinfo
(
    id         bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    username   varchar(64) NOT NULL COMMENT '用户名',
    password   varchar(64) NOT NULL COMMENT '密码',
    salt       varchar(50) NOT NULL COMMENT '盐',
    createTime datetime    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    updateTime datetime    NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '更新时间',
    PRIMARY KEY `pk_id` (`id`),
    UNIQUE KEY `uk_username` (`username`)
) COMMENT '用户表';
