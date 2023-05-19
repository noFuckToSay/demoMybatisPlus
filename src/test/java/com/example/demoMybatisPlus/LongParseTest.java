package com.example.demoMybatisPlus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author:22024002
 * @date:2023/5/8 17:25
 * @description:
 */
@SpringBootTest
public class LongParseTest {
    @Test
    void test(){
        String s="1.0000";
        System.out.println(Double.parseDouble(s));
    }
}
