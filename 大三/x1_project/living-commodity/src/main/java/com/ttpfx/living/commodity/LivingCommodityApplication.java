package com.ttpfx.living.commodity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ttpfx
 * @date 2023/2/3
 */
@EnableDiscoveryClient
@SpringBootApplication
public class LivingCommodityApplication {

    public static void main(String[] args) {
        SpringApplication.run(LivingCommodityApplication.class, args);
    }
}
