package com.example.demoMybatisPlus.base;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author:22024002
 * @date:2023/4/5 17:38
 * @description:
 */
public class BaseEntity<T> {


    public QueryWrapper<T> outWrapper(T dto) {
        try {
            QueryWrapper<T> queryWrapper = new QueryWrapper<>();
            Class dtoClass = dto.getClass();
            List<Field> fieldList = Arrays.asList(dtoClass.getDeclaredFields());
            fieldList = fieldList
                    .stream()
                    .filter(this::filterField)
                    .collect(Collectors.toList());
            for (Field field : fieldList) {
                field.setAccessible(true);
                switch (field.getType().getTypeName()) {
                    case "java.lang.Long":
                    case "java.lang.Integer":
                        queryWrapper.eq(ObjectUtil.isNotNull(field.get(dto)), field.getName(), field.get(dto));
                        break;
                    case "java.lang.String":
                        queryWrapper.like(ObjectUtil.isNotEmpty(field.get(dto)), field.getName(), field.get(dto));
                        break;
                    default:
                        throw new RuntimeException("未实现字段["+field.getType().getTypeName()+"]-["+field.getName()+"]查询条件");
                }
            }
            return queryWrapper;
        } catch (IllegalAccessException ex) {
           throw new RuntimeException(ex);
        }
    }

    /**
     *根据字段名称排除查询字段
     * */
    private boolean filterField(Field field) {
        return
                !StrUtil.equals(field.getName(), "log")
                &&!StrUtil.equals(field.getName(), "createTime")
                &&!StrUtil.equals(field.getName(), "updateTime")
                && !StrUtil.equals(field.getName(), "serialVersionUID");
    }
}
