package com.ttpfx.living.commodity.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ttpfx
 * @date 2023/2/16
 */
@Configuration
@EnableTransactionManagement //开启事务
@MapperScan("com.ttpfx.living.commodity.dao")
public class MyBatisConfig {
    //引入分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作，
        //true 调回到首页，false 继续请求 默认 false
        paginationInterceptor.setOverflow(true);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(100);
        return paginationInterceptor;
    }
}
