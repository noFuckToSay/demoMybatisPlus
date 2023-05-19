package com.example.demoMybatisPlus.user.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demoMybatisPlus.user.biz.UserBiz;
import com.example.demoMybatisPlus.user.dto.UserCdto;
import com.example.demoMybatisPlus.user.dto.UserRdto;
import com.example.demoMybatisPlus.user.dto.UserUdto;
import com.example.demoMybatisPlus.user.dto.UserVo;
import com.example.demoMybatisPlus.user.entity.User;
import com.example.demoMybatisPlus.user.enums.UserStatusEnum;
import com.example.demoMybatisPlus.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:22024002
 * @date:2023/5/19 11:16
 * @description:
 */
@Component
public class UserBizImpl implements UserBiz {

    private UserService userService;

    @Autowired
    public UserBizImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public void create(UserCdto userCdto) {
        userService.create(Arrays.asList(userCdto));
    }

    @Override
    @Transactional
    public void update(UserUdto userUdto) {
        userService.update(Arrays.asList(userUdto));
    }

    @Override
    public List<UserVo> list(UserRdto userRdto) {
        return userService.list(userRdto);
    }

    @Override
    public IPage<UserVo> page(UserRdto userRdto) {
        return userService.page(userRdto);
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        userService.delete(ids);
    }

    @Override
    @Transactional
    public void submit(List<Long> ids) {
        List<User> users = userService.list(Wrappers.<User>lambdaQuery()
                .in(User::getId, ids)
                .eq(User::getStatus, UserStatusEnum.NEW)
        ).stream().map(u -> {
            User user=new User();
            user.setId(u.getId());
            user.setStatus(UserStatusEnum.APPROVAL);
            return user;
        }).collect(Collectors.toList());

        userService.updateBatchById(users);
    }

    @Override
    @Transactional
    public void unSubmit(List<Long> ids) {
        List<User> users = userService.list(Wrappers.<User>lambdaQuery()
                .in(User::getId, ids)
                .eq(User::getStatus, UserStatusEnum.APPROVAL)
        ).stream().map(u -> {
            User user=new User();
            user.setId(u.getId());
            user.setStatus(UserStatusEnum.NEW);
            return user;
        }).collect(Collectors.toList());

        userService.updateBatchById(users);
    }
}
