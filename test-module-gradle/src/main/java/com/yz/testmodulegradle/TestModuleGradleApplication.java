package com.yz.testmodulegradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yz.testmodulegradle")
public class TestModuleGradleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestModuleGradleApplication.class, args);
    }

}
