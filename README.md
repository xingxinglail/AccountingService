# AccountingService

# MySql 事务四种隔离级别

## MySql 默认事务隔离级别 READ COMMITTED 已提交读

## READ UNCOMMITTED 读未提交

- 事务中的修改即使没有提交，对其他事务也是可见的
- 事务可以读取到未提交的数据，此现象称之为**脏读**
- 一般不会使用这种级别，而且任何操作都不会加锁

```sql

show VARIABLES LIKE 'TRANSACTION_isolation'

CREATE TABLE test
(
    id          bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    username    varchar(64) NOT NULL COMMENT '用户名',
    create_time datetime    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    update_time datetime    NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '更新时间',
    PRIMARY KEY `pk_id` (`id`),
    UNIQUE KEY `uk_username` (`username`)
) COMMENT '事务隔离级别测试表表';

-- 连接 A 执行
set SESSION TRANSACTION ISOLATION LEVEL read UNCOMMITTED

BEGIN;
INSERT INTO test (username) VALUES ("老王");
SELECT * FROM test; -- 能查出数据导致脏读

-- 连接 B 执行
set SESSION TRANSACTION ISOLATION LEVEL read UNCOMMITTED

SELECT * FROM test; -- 能查出数据导致脏读
```