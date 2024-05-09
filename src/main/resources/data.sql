INSERT INTO `user`(`id`, `user_name`, `password`, `create_by`, `update_by`)
    VALUES (FLOOR(RAND() * (1000000000000000 - + 1)), 'admin', 'admin', 'admin', 'admin') AS init_user
ON DUPLICATE KEY UPDATE `user_name`=init_user.`user_name`,
                        `password`=init_user.`password`,
                        `create_by`=init_user.`create_by`,
                        `update_by`=init_user.`update_by`;

insert into `menu` (`id`, `parent_id`, `type`, `title`, `key`, `parent_key`, `icon`, `route_to`, `keep_alive`, `create_time`, `update_time`, `create_by`, `update_by`, `is_del`)
values
    ('1',NULL,'1','基础信息','basic-info',NULL,'SettingOutlined',NULL,'0','2024-05-06 09:50:36','2024-05-09 13:06:25',NULL,NULL,'0'),
    ('2','1','2','用户管理','user','basic-info','UserOutlined','/home/user','1','2024-05-06 09:52:12','2024-05-09 13:06:26',NULL,NULL,'0'),
    ('3','1','2','菜单管理','menuManage','basic-info','MenuOutlined','/home/menuManage','0','2024-05-06 09:52:42','2024-05-09 13:06:26',NULL,NULL,'0'),
    ('4',NULL,'1','业务信息','business-info',NULL,'TeamOutlined',NULL,'0','2024-05-06 09:53:44','2024-05-09 13:06:27',NULL,NULL,'0'),
    ('5','4','2','车辆管理','car','business-info','CarOutlined','/home/car','0','2024-05-06 09:54:39','2024-05-09 13:06:28',NULL,NULL,'0'),
    ('6',NULL,'2','物料管理','part',NULL,'WalletOutlined','/home/part','0','2024-05-06 09:56:25','2024-05-09 13:06:28',NULL,NULL,'0') as init_menu
ON DUPLICATE KEY UPDATE `parent_id`=init_menu.`parent_id`,
                        `type`=init_menu.`type`,
                        `title`=init_menu.`title`,
                        `parent_key`=init_menu.`parent_key`,
                        `icon`=init_menu.`icon`,
                        `route_to`=init_menu.`route_to`,
                        `keep_alive`=init_menu.`keep_alive`,
                        `create_by`=init_menu.`create_by`,
                        `update_by`=init_menu.`update_by`;