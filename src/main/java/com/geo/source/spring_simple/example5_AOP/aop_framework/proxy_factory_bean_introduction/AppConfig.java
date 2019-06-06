package com.geo.source.spring_simple.example5_AOP.aop_framework.proxy_factory_bean_introduction;

import com.geo.source.spring_simple.example5_AOP.introduction.Contact;
import com.geo.source.spring_simple.example5_AOP.introduction.IsModified;
import com.geo.source.spring_simple.example5_AOP.introduction.IsModifiedAdvisor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 引入检查，测试
 *
 * @author YanZhen
 * @since 2019-06-05 17:17
 */
@Configuration
public class AppConfig {

  @Bean
  Contact contact() {
    final Contact contact = new Contact();
    contact.setName("yz");
    return contact;
  }

  @Bean
  Advisor advisor1() {
    return new IsModifiedAdvisor();
  }

  @Bean
  ProxyFactoryBean introductionBean1() {
    final ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
    proxyFactoryBean.setTarget(contact());
    proxyFactoryBean.addAdvisor(advisor1());
    proxyFactoryBean.setProxyTargetClass(true);
    return proxyFactoryBean;
  }
}
