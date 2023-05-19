package com.example.demoMybatisPlus.config.mybatisPlus;

/**
 * @author:22024002
 * @date:2023/4/7 12:25
 * @description:
 */
public class OperatorContextHolder {
    private static ThreadLocal<String> operatorContext=new ThreadLocal<>();

    public static String getOperator() {
        return operatorContext.get();
    }

    public static void setOperator(String operator) {
        operatorContext.set(operator);
    }
}
