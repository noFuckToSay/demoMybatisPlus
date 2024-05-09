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

CREATE TABLE IF NOT EXISTS `menu`
(
    `id`          BIGINT      NOT NULL COMMENT '主键',
    `parent_id`   BIGINT      NULL COMMENT '父级菜单主键',
    `type`        INT         NULL COMMENT '1.subMenu 2.menuItem',
    `title`       VARCHAR(32) NOT NULL COMMENT '标题',
    `key`         VARCHAR(32) NOT NULL COMMENT 'key',
    `parent_key`  VARCHAR(32) NULL COMMENT 'parentKey',
    `icon`        VARCHAR(32) NULL COMMENT '图标	',
    `route_to`    VARCHAR(32) NULL COMMENT 'vue-router路由地址',
    `keep_alive` tinyint DEFAULT '0' COMMENT '菜单是否开启keep-alive缓存',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `create_by`   VARCHAR(32)          DEFAULT NULL COMMENT '创建人',
    `update_by`   VARCHAR(32)          DEFAULT NULL COMMENT '修改人',
    `is_del`      INT         NOT NULL DEFAULT '0' COMMENT '逻辑删除',

    PRIMARY KEY (`id`),
    UNIQUE KEY `key_unique` (`key`)
) ENGINE = INNODB COMMENT ='菜单表';
