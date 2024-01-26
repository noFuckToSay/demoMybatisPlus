package com.example.demoMybatisPlus;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author:22024002
 * @date:2023/4/7 17:00
 * @description:
 */
@SpringBootTest
@Slf4j
public class DurationTest {

    @Test
    public void testDuration(){
        LocalDateTime now =LocalDateTime.now();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LocalDateTime next=LocalDateTime.now();
        Duration duration=Duration.between(now,next);
        String interval= LocalTime.ofNanoOfDay(duration.toNanos()).format(DateTimeFormatter.ofPattern("HH:mm:ss:SSS"));

        System.out.println(interval);
    }

    @Test
    public void test(){
        BigDecimal a=BigDecimal.TEN;
        System.out.println("a is "+a);
        testSub(a);
        System.out.println("a is "+a);
    }

    private void testSub(BigDecimal num){
        num=num.subtract(BigDecimal.ONE);
        System.out.println("num is "+num);
    }
}
