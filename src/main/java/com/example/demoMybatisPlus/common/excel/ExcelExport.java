package com.example.demoMybatisPlus.common.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * @Description
 * @author wtlip
 * @date 2022/7/6 14:24
 * @param
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelExport {

    /** 字段名称 */
    String value();

    /** 导出排序先后: 数字越小越靠前（默认按Java类字段顺序导出） */
    int sort() default 0;

    /** 导出映射，格式如：0-未知;1-男;2-女 */
    String kv() default "";

    /** 导出模板示例值（有值的话，直接取该值，不做映射） */
    String example() default "";

    /** 导出模板中该列单元格格式是否为文本 */
    boolean isTextStyle() default false;
}
