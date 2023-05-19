package com.example.demoMybatisPlus;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author:22024002
 * @date:2023/4/8 14:04
 * @description:
 */
@SpringBootTest
public class JDBCTest {

    @SneakyThrows
    @Test
    public void test(){
        String url="jdbc:mysql://localhost:3306/world?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
        String userName="root";
        String passWord="admin";
//        Class.forName("com.mysql.jdbc.Driver");
        Connection connection= DriverManager.getConnection(url,userName,passWord);

        Class connectoinClass=connection.getClass();
        Method method=connectoinClass.getDeclaredMethod("prepareStatement",String.class,String[].class);

        String sql="INSERT INTO test_table  ( warehouse_code )  VALUES  ( ? )";
        String[] parameters={"test_id"};
        Object[] invokeParameters={sql,parameters};
//        PreparedStatement preparedStatement= connection.prepareStatement(sql,parameters);
        PreparedStatement preparedStatement= (PreparedStatement) method.invoke(connection,invokeParameters);
        preparedStatement.setString(1,"0198");
        System.out.println(preparedStatement);
        preparedStatement.execute();

        preparedStatement.close();
        connection.close();
    }
}
