package com.geo.source.time.task;

import com.geo.source.time.task.config.ServiceLocator;
import com.geo.source.time.task.config.TimeTaskConfig;
import com.geo.source.time.task.task.MinuteTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * 模拟定时任务测试器，给予spring
 * @author YanZhen
 * @date 2019/09/07 08:54
 **/
@Slf4j
public class Main {

    private static ServiceLocator serviceLocator;

    public static void main(String[] args) {
        final GenericApplicationContext context = new AnnotationConfigApplicationContext(TimeTaskConfig.class);
        serviceLocator = context.getBean(ServiceLocator.class);
        new Main().test();
        context.close();

//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    // @Scheduled(cron = "0/5 * * * * ?")
    public void test() {
        final Main main = new Main();
        if (serviceLocator != null) {
            final List<MinuteTaskService> minuteTaskServices = serviceLocator.getMinuteTaskServices();
            for (MinuteTaskService taskService : minuteTaskServices) {
                new Main().run(taskService);
            }
        } else {
            log.error("服务未获取");
        }
    }

    @Async
    void run(MinuteTaskService taskService) {
        taskService.run();
    }
}
