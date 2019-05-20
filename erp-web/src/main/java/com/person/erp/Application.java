package com.person.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
        System.out.println("项目启动...");
        SpringApplication.run(Application.class, args);
        System.out.println("启动成功...");
    }
}
