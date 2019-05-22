package com.geo.source.spring_simple.example2_3_chapter.component_scan;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * <p> @ComponentScanning 注解</p>
 * ComponentScan 或 ImportResource 注解都可以导入bean定义
 * 用AnnotationConfigApplicationContext启动spring环境
 *
 * @author YanZhen
 * @since 2019-05-16 14:37
 */
// @ComponentScan(basePackages = "com.geo.source.spring_simple.example2.stereotype")
@ImportResource(locations = "classpath:spring/app-context-xml.xml")
@Configuration
public class HelloWorldConfiguration2 {

}
