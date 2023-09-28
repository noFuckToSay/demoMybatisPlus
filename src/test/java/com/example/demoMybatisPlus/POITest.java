package com.example.demoMybatisPlus;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
@Slf4j
public class POITest {

    @Test
    void test() throws IOException {
        LocalDateTime now=LocalDateTime.now().plusMonths(2);
        Integer rowNum=0;
        Integer cellNum=0;

        SXSSFWorkbook book = new SXSSFWorkbook();
        CellStyle cellStyle=book.createCellStyle();
        DataFormat format = book.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("@"));
        Sheet sheet = book.createSheet("test");
        Row row=sheet.createRow(rowNum++);
        Cell cell=row.createCell(cellNum++);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(now));
        sheet.setDefaultColumnStyle(0,cellStyle);

        FileOutputStream fileOutputStream=new FileOutputStream("C:\\Users\\22024002\\Desktop\\test.xlsx");
        book.write(fileOutputStream);
        fileOutputStream.close();

    }

    @Test
    void test1(){
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()));
    }
}
