package com.yangbin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/10/010
 * @Time 18:07
 */
@SpringBootApplication
@EnableDiscoveryClient
public class LeyouloadApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouloadApplication.class,args);
    }
}
