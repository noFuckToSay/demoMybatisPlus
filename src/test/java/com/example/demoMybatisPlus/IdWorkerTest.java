package com.example.demoMybatisPlus;

import cn.hutool.core.date.DateTime;
import org.apache.poi.ss.usermodel.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author:22024002
 * @date:2023/7/14 18:13
 * @description:
 */
@SpringBootTest
public class IdWorkerTest {

    @Test

    /**
     * Long类型时间戳转化为LocalDateTime * @param dateTimeLong * @return
     */
    void test() {
        Long excelDate=45199l;
        Date date=DateUtil.getJavaDate(excelDate,TimeZone.getDefault());
        System.out.println(date);
        System.out.println(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    @Test
    void test1(){
        System.out.println(LocalDateTime.class.getSimpleName());
        System.out.println(LocalDate.class.getSimpleName());
        System.out.println(DateTime.class.getSimpleName());
        System.out.println(Date.class.getSimpleName());
    }
}
