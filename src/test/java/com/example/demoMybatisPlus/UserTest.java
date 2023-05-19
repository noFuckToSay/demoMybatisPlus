package com.example.demoMybatisPlus;

import com.alibaba.fastjson.JSONObject;
import com.example.demoMybatisPlus.user.dto.UserVo;
import com.example.demoMybatisPlus.user.entity.User;
import com.example.demoMybatisPlus.user.enums.UserStatusEnum;
import com.example.demoMybatisPlus.user.mapstruct.UserMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author:22024002
 * @date:2023/5/19 19:59
 * @description:
 */
@SpringBootTest
@Slf4j
public class UserTest {

    @Test
    void test(){
        User user=new User();
        user.setStatus(UserStatusEnum.NEW.getCode());
        log.info(user.getStatus()+"");

        UserVo vo= UserMap.INSTANCE.toVo(user);
        log.info(vo.getStatus()+"");
    }
}
