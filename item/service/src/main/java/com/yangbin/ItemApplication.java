package com.yangbin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/6/006
 * @Time 13:50
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yangbin.mapper")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class,args);
    }
}
