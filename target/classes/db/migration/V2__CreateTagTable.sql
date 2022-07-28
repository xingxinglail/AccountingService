CREATE TABLE as_tag
(
    id          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    user_id     bigint(20) unsigned NOT NULL,
    description varchar(64) NOT NULL COMMENT '标签名',
    status tinyint(1) NOT NULL DEFAULT 1 COMMENT '删除状态 1未删除 0已删除',
    create_time datetime    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    update_time datetime    NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '更新时间',
    PRIMARY KEY `pk_id` (`id`)
) COMMENT '用户表';
