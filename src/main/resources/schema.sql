CREATE TABLE IF NOT EXISTS `user`
(
    `id`          BIGINT      NOT NULL COMMENT '主键',
    `user_name`   VARCHAR(32) NOT NULL COMMENT '用户名',
    `password`    VARCHAR(32) NOT NULL COMMENT '密码',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_by`   VARCHAR(32)          DEFAULT NULL COMMENT '创建人',
    `update_by`   VARCHAR(32)          DEFAULT NULL COMMENT '修改人',
    `is_del`      INT         NOT NULL DEFAULT '0' COMMENT '逻辑删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_name_unique` (`user_name`)
) ENGINE = INNODB COMMENT ='用户表';
