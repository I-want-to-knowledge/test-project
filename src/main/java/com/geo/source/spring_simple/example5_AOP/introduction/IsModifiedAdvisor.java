package com.geo.source.spring_simple.example5_AOP.introduction;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * 创建引用检查的顾问
 *
 * @author YanZhen
 * @since 2019-06-05 09:41
 */
public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {

  public IsModifiedAdvisor() {
    super(new IsModifiedMixin());
  }
}
