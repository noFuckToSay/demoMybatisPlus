package com.example.demoMybatisPlus;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
}
