package com.geo.source.csv.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 导出csv文件的注释
 * @author YanZhen
 * @date 2020/01/20 15:19
 **/
@Target(TYPE)
@Retention(RUNTIME)
public @interface CsvRow {

    /**
     * 给出csv的文件名
     * @return 文件名
     */
    String value() default "";
}
