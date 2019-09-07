package com.geo.source.time.task.config;

import com.geo.source.time.task.task.MinuteTaskService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author YanZhen
 * @date 2019/09/07 09:42
 **/
@Component
public class ServiceLocator implements ApplicationContextAware {

    private Map<String, MinuteTaskService> minuteTaskServiceMap;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        minuteTaskServiceMap = applicationContext.getBeansOfType(MinuteTaskService.class);
    }

    public Map<String, MinuteTaskService> getMinuteTaskServiceMap() {
        return minuteTaskServiceMap;
    }

    public List<MinuteTaskService> getMinuteTaskServices() {
        if (minuteTaskServiceMap == null) {
            return new ArrayList<>();
        }
        return new ArrayList<>(minuteTaskServiceMap.values());
    }
}
