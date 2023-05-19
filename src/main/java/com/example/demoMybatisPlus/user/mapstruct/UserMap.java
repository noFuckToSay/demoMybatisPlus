package com.example.demoMybatisPlus.user.mapstruct;

import com.example.demoMybatisPlus.user.dto.UserCdto;
import com.example.demoMybatisPlus.user.dto.UserRdto;
import com.example.demoMybatisPlus.user.dto.UserUdto;
import com.example.demoMybatisPlus.user.dto.UserVo;
import com.example.demoMybatisPlus.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author:22024002
 * @date:2023/5/19 10:50
 * @description:
 */
@Mapper
public interface UserMap {
    UserMap INSTANCE = Mappers.getMapper(UserMap.class);

    User fromCdto(UserCdto userCdto);

    User fromUdto(UserUdto userUdto);

    User fromRdto(UserRdto userRdto);

    UserVo toVo(User user);

}
