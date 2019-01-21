package com.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticDemoApplication {

    /**
     * springboot 默认支持两种技术支持ES
     * jest(默认不生效)  spring_data_ES
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ElasticDemoApplication.class, args);
    }

}

