package spring2.groovy

import com.geo.source.spring_simple.example4_chapter.groovy.GroovyEntity
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader
import org.springframework.context.support.GenericApplicationContext

def context = new GenericApplicationContext()
def reader = new GroovyBeanDefinitionReader(context)

reader.beans {
    groovyEntity(GroovyEntity, name: 'yz', age: 27)
}

context.refresh()

println context.getBean("groovyEntity")