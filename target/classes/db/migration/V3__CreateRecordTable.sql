CREATE TABLE as_record
(
    id          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    user_id     bigint(20) unsigned NOT NULL,
    amount      DECIMAL(10, 2) NOT NULL COMMENT '金额',
    category    tinyint(1) NOT NULL DEFAULT 1 COMMENT '账务类型 1收入 2支出',
    status      tinyint(1) NOT NULL DEFAULT 1 COMMENT '删除状态 1未删除 0已删除',
    note        varchar(100)            DEFAULT NULL COMMENT '备注',
    create_time datetime       NOT NULL DEFAULT NOW() COMMENT '创建时间',
    update_time datetime       NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '更新时间',
    PRIMARY KEY `pk_id` (`id`)
) COMMENT '用户账务表';

