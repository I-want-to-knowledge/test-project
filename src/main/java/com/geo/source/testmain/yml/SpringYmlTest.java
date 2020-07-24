package com.geo.source.testmain.yml;

import com.geo.source.testmain.yml.config.YmlConfig;
import com.geo.source.testmain.yml.domian.YmlDto;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @author YanZhen
 * @date 2020/07/24 11:35
 */
public class SpringYmlTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(YmlConfig.class);
        YmlDto ymlDto = context.getBean(YmlDto.class);
        List<String> list = ymlDto.getList();
        System.out.println(list);
        System.out.println(list == null ? null : list.get(0));
    }
}
