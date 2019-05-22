package com.geo.source.spring_simple.example2_3_chapter.AutomaticAssembly;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 自动装配bean，测试
 *
 * @author YanZhen
 * @since 2019-05-21 14:23
 */
interface Foo2 {
}

@Component
class FooImpl1 implements Foo2 {

}

@Component
@Primary // bean出现冲突时首选
class FooImpl2 implements Foo2 {

}