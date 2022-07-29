CREATE TABLE as_userinfo
(
    id          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    username    varchar(64) NOT NULL COMMENT '用户名',
    password    varchar(64) NOT NULL COMMENT '密码',
    salt        varchar(50) NOT NULL COMMENT '盐',
    create_time datetime    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    update_time datetime    NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '更新时间',
    PRIMARY KEY `pk_id` (`id`),
    UNIQUE KEY `uk_username` (`username`)
) COMMENT '用户表';

-- 密码'asd'
insert into as_userinfo(id, username, password, salt)
values (1, "abc", "c1Un2B/PGLT9MSkc7k45x5hLMXAUMvOKSvRISKxUWFY=", "09826faf-b077-4883-b912-cd6a45f365ea");

-- 密码'asd'
insert into as_userinfo(id, username, password, salt)
values (2, "aaa", "fnfQe5Yg0UeGfxiV1Kwwo+82rF5tQ9jmc6RNzCzRcPI=", "7bdf6f5d-4326-415d-84e1-e398f3f9e4aa");