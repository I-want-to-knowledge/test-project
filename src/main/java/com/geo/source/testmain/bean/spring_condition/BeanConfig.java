package com.geo.source.testmain.bean.spring_condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * bean configuration
 *
 * @author YanZhen
 * @since 2019-03-28 15:29
 */
@Configuration
public class BeanConfig {

  @Conditional(WindowsCondition.class)// values是个数组可以配置多个条件，条件之间是‘&&’关系
  @Bean(name = "bill")
  public Person person1() {
    return new Person("Bill Gates", 62);
  }

  @Conditional(LinuxCondition.class)
  @Bean("linus")
  public Person person2() {
    return new Person("Linus", 50);
  }
}
