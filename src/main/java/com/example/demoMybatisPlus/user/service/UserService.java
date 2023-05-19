package com.example.demoMybatisPlus.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demoMybatisPlus.user.dto.UserCdto;
import com.example.demoMybatisPlus.user.dto.UserRdto;
import com.example.demoMybatisPlus.user.dto.UserUdto;
import com.example.demoMybatisPlus.user.dto.UserVo;
import com.example.demoMybatisPlus.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dingzk
 * @since 2023/04/17
 */
public interface UserService extends IService<User> {

    void create(List<UserCdto> userCdtoList);
    void update(List<UserUdto> userUdtoList);
    List<UserVo> list(UserRdto userRdto);
    IPage<UserVo> page(UserRdto userRdto);
    void delete(List<Long> ids);
}
