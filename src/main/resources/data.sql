INSERT INTO `user`(`id`,`user_name`,`password`,`create_by`,`update_by`)
    VALUES(FLOOR(RAND() * (1000000000000000 -  + 1)),'admin','admin','admin','admin') AS init_user
ON DUPLICATE KEY UPDATE
                     `user_name`=init_user.`user_name`,
                     `password`=init_user.`password`,
                     `create_by`=init_user.`create_by`,
                     `update_by`=init_user.`update_by`;