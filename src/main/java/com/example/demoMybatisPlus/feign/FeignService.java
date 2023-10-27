package com.example.demoMybatisPlus.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FeignService {

    @Autowired
    private MyFeign myFeign;

//    @Async
    public void feignInvoke(){
        for(int i=0;i<200;i++){
            String result=myFeign.testFeignInvoke();
            log.info("{}-{}",i,result);
        }
    }
}
