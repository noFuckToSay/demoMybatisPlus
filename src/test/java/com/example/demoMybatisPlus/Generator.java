package com.example.demoMybatisPlus;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * @author:22024002
 * @date:2023/3/31 10:53
 * @description:
 */
@SpringBootTest
@Slf4j
public class Generator {

    @Test
    void test(){
        String jdbcUrl="jdbc:mysql://127.0.0.1:3306/world";
        String userName="root";
        String password="admin";

//        String jdbcUrl="jdbc:mysql://rm-m5e4y1r17526z63ey.mysql.rds.aliyuncs.com:3306/uat-haier-vmi?autoReconnect=true&allowMultiQueries=true&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai";
//        String userName="hdshmls_admin";
//        String password="hdshmls@^01_f";

//        String jdbcUrl="jdbc:mysql://rm-m5er1vh0cj38ao08m.mysql.rds.aliyuncs.com:3306/haier-vmi?autoReconnect=true&allowMultiQueries=true&useUnicode=true&characterEncoding=UTF8&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
//        String userName= "vmi_rt";
//        String password="Hpwd@_luf09^fffw3";

        String javaPath=System.getProperty("user.dir")+"/src/main/java";
        String resourcePath=System.getProperty("user.dir")+"/src/main/resources";

        String packageName="oneDetail";
        String tableName="one_detail";

        FastAutoGenerator
                .create(jdbcUrl, userName, password)
                .globalConfig(builder -> {
                    builder
                            .outputDir(javaPath) // 指定输出目录
                            .author("dingzk") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy/MM/dd")
                            .disableOpenDir();  //禁止打开输出目录
                })
                .packageConfig(builder -> {
                    builder
                            .parent("com.example.demomybatisplus") // 设置父包名
                            .moduleName(packageName) // 设置父包模块名
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, resourcePath+"/mapper/"+packageName)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder
                            .addInclude(tableName) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_","wms_","vmi_zjt_service_plat_","vmi_") // 设置过滤表前缀
                            .entityBuilder()
                            .enableFileOverride()
                            .enableLombok()
                            .mapperBuilder()
                            .enableFileOverride()
                            .serviceBuilder()       //service生成策略
                            .enableFileOverride()         //覆盖
                            .formatServiceFileName("%sService")//格式化service文件名xxxService
                            .controllerBuilder()    //controller生成策略
                            .enableFileOverride()         //覆盖
                            .enableRestStyle();      //rest风格controller
                })
                .injectionConfig(builder->{
                    Map<String,Object> customFilesParams=new HashMap<>();   //自定义模板用到的变量值
                    customFilesParams.put("test","hello,world");

                    builder
                            .beforeOutputFile(((tableInfo, stringObjectMap) -> {
                                System.out.println(JSONObject.toJSONString(tableInfo));
                                System.out.println(stringObjectMap);
                            }))
                            .customMap(customFilesParams)
                            .customFile(file->{
                                file.fileName("Dto.java").templatePath("/templates/dto.java.vm").packageName("dto").build();
                            });
//                            .customFile(file->{
//                                file.fileName("Vo.java").templatePath("/templates/vo.java.vm").packageName("vo").build();
//                            });
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

    @Test
    public void test2() throws SQLException {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "admin");
        DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", properties);
    }
}
