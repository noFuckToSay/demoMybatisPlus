package com.example.demoMybatisPlus.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demoMybatisPlus.user.dto.UserCdto;
import com.example.demoMybatisPlus.user.dto.UserRdto;
import com.example.demoMybatisPlus.user.dto.UserUdto;
import com.example.demoMybatisPlus.user.dto.UserVo;
import com.example.demoMybatisPlus.user.entity.User;
import com.example.demoMybatisPlus.user.enums.UserStatusEnum;
import com.example.demoMybatisPlus.user.mapper.UserMapper;
import com.example.demoMybatisPlus.user.mapstruct.UserMap;
import com.example.demoMybatisPlus.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dingzk
 * @since 2023/04/17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void create(List<UserCdto> userCdtoList) {
        List<User> users=userCdtoList
                .stream()
                .map(dto->{
                    User user =UserMap.INSTANCE.fromCdto(dto);
                    user.setStatus(UserStatusEnum.NEW.getCode());
                    return user;
                })
                .collect(Collectors.toList());

        this.saveOrUpdateBatch(users);
    }

    @Override
    public void update(List<UserUdto> userUdtoList) {
        List<User> users=userUdtoList
                .stream()
                .map(UserMap.INSTANCE::fromUdto)
                .collect(Collectors.toList());

        this.saveOrUpdateBatch(users);
    }

    @Override
    public List<UserVo> list(UserRdto userRdto) {
        List<User> users= this.list(Wrappers.<User>lambdaQuery()
                .eq(StrUtil.isNotBlank(userRdto.getName()),User::getName,userRdto.getName())
                .eq(StrUtil.isNotBlank(userRdto.getEmail()),User::getEmail,userRdto.getEmail())
                .eq(ObjectUtil.isNotNull(userRdto.getAge()),User::getAge,userRdto.getAge())
        );

        List<UserVo> userVos=users.stream().map(UserMap.INSTANCE::toVo).collect(Collectors.toList());

        return userVos;
    }

    @Override
    public IPage<UserVo> page(UserRdto userRdto) {
        Page<User> page=Page.of(userRdto.getPageNum(),userRdto.getPageSize());
        IPage<User> originPage=this.page(page,Wrappers.<User>lambdaQuery()
                .eq(StrUtil.isNotBlank(userRdto.getName()),User::getName,userRdto.getName())
                .eq(StrUtil.isNotBlank(userRdto.getEmail()),User::getEmail,userRdto.getEmail())
                .eq(ObjectUtil.isNotNull(userRdto.getAge()),User::getAge,userRdto.getAge())
        );
        IPage<UserVo> voPage=originPage.convert(UserMap.INSTANCE::toVo);
        return voPage;
    }

    @Override
    public void delete(List<Long> ids) {
        this.removeByIds(ids);
    }
}
