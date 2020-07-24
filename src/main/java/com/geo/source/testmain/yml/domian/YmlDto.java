package com.geo.source.testmain.yml.domian;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author YanZhen
 * @date 2020/07/24 11:53
 */
@Component
public class YmlDto {
    @Value("${test-list}")
    private List<String> list;

    public List<String> getList() {
        return list;
    }
}
