package com.example.demoMybatisPlus.inventory.utils;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 校验帮助类
 *
 * @author keno
 */
@Slf4j
public class ValidatorUtils {

    /**
     * 验证器工厂️
     */
    private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
    /**
     * 验证器
     */
    private static final Validator VALIDATOR = FACTORY.getValidator();


    public static <T> void validateList(List<T> datas) {
        validateList(datas, null);
    }

    /**
     * 验证集合
     *
     * @param datas 集合
     * @param <T>   对象类型
     */
    public static <T> void validateList(List<T> datas, Class<?> groups) {
        Map<Integer, Map<String, String>> errors = new HashMap<>();
        for (int i = 0; i < datas.size(); i++) {
            Map<String, String> error = validate(datas.get(i), groups);
            if (CollUtils.isEmpty(error)) {
                continue;
            }
            errors.put(i, error);
        }
        if (!CollUtils.isEmpty(errors)) {
            log.error(errors.toString());
            throw new RuntimeException(errors.toString());
        }
    }

    /**
     * 校验单个对象
     *
     * @param data 对象
     * @param <T>  对象类型
     */
    public static <T> void validateOne(T data) {
        validateOne(data, null);
    }

    /**
     * 验证对象
     *
     * @param data 对象
     * @param <T>  对象类型
     */
    public static <T> void validateOne(T data, Class<?> groups) {
        Map<String, String> error = validate(data, groups);
        if (!CollUtils.isEmpty(error)) {
            log.error(error.toString());
            throw new RuntimeException(error.toString());
        }
    }

    /**
     * 验证对象
     *
     * @param data 对象
     * @param <T>  对象类型
     * @return 返回错误信息
     */
    public static <T> Map<String, String> validate(T data, Class<?> groups) {
        Map<String, String> errorMap = new HashMap<>();
        if (ObjectUtils.isNull(groups)) {
            groups = Default.class;
        }
        try {
            Set<ConstraintViolation<T>> violations = VALIDATOR.validate(data, groups);
            for (ConstraintViolation<T> violation : violations) {
                Field declaredField = data.getClass().getDeclaredField(violation.getPropertyPath().toString());
                errorMap.put(declaredField.getName(), violation.getMessage());
            }
        } catch (Exception e) {
            log.error("校验数据异常", e);
            throw new RuntimeException("校验数据异常" + e.getMessage());
        }
        return errorMap;
    }
}
