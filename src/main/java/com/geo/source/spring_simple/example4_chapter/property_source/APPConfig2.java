package com.geo.source.spring_simple.example4_chapter.property_source;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Import 注入目标配置类
 *
 * @author YanZhen
 * @since 2019-05-27 11:24
 */
@Configuration
@Import(APPConfig.class)
@ImportResource("classpath:spring2/app-context-event.xml")
public class APPConfig2 {

}
