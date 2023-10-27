package com.example.demoMybatisPlus.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "myFeign", url = "http://127.0.0.1:8080")
public interface MyFeign {

    @GetMapping("/test/feignInvoke")
    String testFeignInvoke();
}
