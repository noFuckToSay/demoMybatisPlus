package com.example.demoMybatisPlus;

import com.example.demoMybatisPlus.feign.FeignService;
import com.example.demoMybatisPlus.feign.MyFeign;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@Slf4j
public class FeignInvokeTest {
    @Autowired
    FeignService feignService;


    @Test
    void test(){
        feignService.feignInvoke();
    }
}
