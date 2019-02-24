package com.tensquare.spit;

import entity.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @program: tensquare_parent
 * @description:
 * @author: Mr.Jiang
 * @create: 2019-02-24 15:36
 **/
@SpringBootApplication
@EnableAutoConfiguration
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class, args);
    }
    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
}

