package com.geo.source.testmain.yml.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author YanZhen
 * @date 2020/07/24 11:39
 */
@Configuration
@PropertySource("classpath:yml/spring.yml")
@ComponentScan("com.geo.source.testmain.yml")
public class YmlConfig {
}
