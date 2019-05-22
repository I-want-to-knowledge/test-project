package com.geo.source.spring_simple.example2_3_chapter.LookupMethodReplacer;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

/**
 * 方法替换，测试
 *
 * @author YanZhen
 * @since 2019-05-18 16:14
 */
public class MethodReplacementDemo {
  public static void main(String[] args) {
    final GenericXmlApplicationContext context = new GenericXmlApplicationContext();
    context.load("classpath:spring/app-context-xml.xml");
    context.refresh();
    final ReplacementTarget replacementTarget = context.getBean("replacementTarget", ReplacementTarget.class);
    final ReplacementTarget standardTarget = context.getBean("standardTarget", ReplacementTarget.class);
    displayInfo(replacementTarget);
    displayInfo(standardTarget);
    context.close();
  }

  private static void displayInfo(ReplacementTarget target) {
    System.out.println(target.formatMessage("Thanks for playing, try again！"));
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("perfTest");

    for (int i = 0; i < 100000; i++) {
      final String s = target.formatMessage("No filter in my head!");
      // System.out.println(s);
    }

    stopWatch.stop();
    System.out.println("100000 invocations took: " + stopWatch.getTotalTimeMillis() + "ms");
  }
}
