package com.example.demoMybatisPlus.inventory.utils;

import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对象帮助类
 *
 * @author keno
 */
public class ObjectUtils extends ObjectUtil {
    protected static final Logger log = LoggerFactory.getLogger(ObjectUtils.class);

    /**
     * 设置对象字段值（字段类型为 Long）
     *
     * @param object    对象
     * @param fieldName 字段名称
     * @param value     值
     */
    public static void setLong(Object object, String fieldName, Long value) {
        setValue(object, fieldName, value);
    }

    /**
     * 设置对象字段值（字段类型为 String）
     *
     * @param object    对象
     * @param fieldName 字段名称
     * @param value     值
     */
    public static void setString(Object object, String fieldName, String value) {
        setValue(object, fieldName, value);
    }

    /**
     * 设置对象字段值（字段类型为 Integer）
     *
     * @param object    对象
     * @param fieldName 字段名称
     * @param value     值
     */
    public static void setInt(Object object, String fieldName, Integer value) {
        setValue(object, fieldName, value);
    }

    /**
     * 设置对象字段值
     *
     * @param object    对象
     * @param fieldName 字段名称
     * @param value     值
     */
    public static void setValue(Object object, String fieldName, Object value) {
        try {
            Field field = getField(object.getClass(), fieldName, 0);
            field.setAccessible(true);
            field.set(object, value);
            field.setAccessible(false);
        } catch (Exception e) {
            log.error("对象设置：对象中未找到" + fieldName + "属性", e);
            throw new RuntimeException("对象设置：对象中未找到" + fieldName + "属性");
        }
    }

    private static Field getField(Class<?> classes, String fieldName, int levels) throws NoSuchFieldException {
        try {
            return classes.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            if (levels >= 3) {
                throw e;
            }
            levels = levels + 1;
            return getField(classes.getSuperclass(), fieldName, levels);
        }
    }


    /**
     * 哈希值
     *
     * @param raw           对象
     * @param containFields 包含字段
     * @return 结果
     */
    public static Long hash(Object raw, List<String> containFields) {
        return toHash(ObjectUtils.toMap(raw, containFields));
    }

    /**
     * 字段按照TreeMap排序
     *
     * @param map Map
     * @return 结果
     */
    public static Long toHash(Map<String, Object> map) {
        if (CollUtils.isEmpty(map)) {
            return null;
        }
        String sort = JSON.toJSONString(map, SerializerFeature.MapSortField);
        return HashUtil.mixHash(sort);
    }

    /**
     * 对象转Map
     *
     * @param raw           对象
     * @param containFields 包含字段
     * @return 结果
     */
    public static Map<String, Object> toMap(Object raw, List<String> containFields) {
        if (ObjectUtils.isNull(raw)) {
            return null;
        }
        if (CollUtils.isEmpty(containFields)) {
            containFields = new ArrayList<>();
        }
        Map<String, Object> params = new HashMap<>();
        try {
            BeanInfo rawInfo = Introspector.getBeanInfo(raw.getClass());
            for (PropertyDescriptor rawPd : rawInfo.getPropertyDescriptors()) {
                Method rawReader = rawPd.getReadMethod();
                if (ObjectUtils.isNull(rawReader) || ObjectUtils.isNull(rawReader.invoke(raw)) || !containFields.contains(rawPd.getName())) {
                    continue;
                }
                String rawStr = rawReader.invoke(raw).toString();
                if (StrUtil.isBlank(rawStr)) {
                    continue;
                }
                params.put(rawPd.getName(), rawStr);
            }
        } catch (Exception e) {
            log.error("对象转Map异常", e);
            throw new RuntimeException("对象转Map异常");
        }
        return params;
    }

    /**
     * 获取对象字段值
     *
     * @param raw       对象
     * @param fieldName 字段名称
     * @return 值
     */
    public static Object getValue(Object raw, String fieldName) {
        try {
            Field field = raw.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(raw);
        } catch (Exception e) {
            log.error("对象获取属性值异常", e);
            throw new RuntimeException("对象获取属性值异常");
        }
    }
}
