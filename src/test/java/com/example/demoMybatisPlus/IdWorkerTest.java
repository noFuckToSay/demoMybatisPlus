package com.example.demoMybatisPlus;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:22024002
 * @date:2023/7/14 18:13
 * @description:
 */
@SpringBootTest
public class IdWorkerTest {

    @Test
    void test(){
        IdWorker.getId();
        List<Thread> threads=new ArrayList<>();
//        Thread thread=
    }
}
