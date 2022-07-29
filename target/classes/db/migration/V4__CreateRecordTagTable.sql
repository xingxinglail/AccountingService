CREATE TABLE as_record_tag
(
    id          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    user_id     bigint(20) unsigned NOT NULL,
    record_id   bigint(20) unsigned NOT NULL,
    tag_id      bigint(20) unsigned NOT NULL,
    create_time datetime NOT NULL DEFAULT NOW() COMMENT '创建时间',
    PRIMARY KEY `pk_id` (`id`)
) COMMENT '用户账务标签关联表';

