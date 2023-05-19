package com.example.demoMybatisPlus.user.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demoMybatisPlus.user.dto.UserCdto;
import com.example.demoMybatisPlus.user.dto.UserRdto;
import com.example.demoMybatisPlus.user.dto.UserUdto;
import com.example.demoMybatisPlus.user.dto.UserVo;

import java.util.List;

/**
 * @author:22024002
 * @date:2023/5/19 11:01
 * @description:
 */
public interface UserBiz {

    void create(UserCdto userCdto);

    void update(UserUdto userUdto);

    List<UserVo> list(UserRdto userRdto);

    IPage<UserVo> page(UserRdto userRdto);

    void delete(List<Long> ids);
    void submit(List<Long> ids);
    void unSubmit(List<Long> ids);

}