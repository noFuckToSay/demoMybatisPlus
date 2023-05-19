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
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime next=LocalDateTime.parse("2023-04-10 17:00:00",formatter);
        Duration duration=Duration.between(now,next);
        String interval= LocalTime.ofNanoOfDay(duration.toNanos()).format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println(interval);
    }
}
