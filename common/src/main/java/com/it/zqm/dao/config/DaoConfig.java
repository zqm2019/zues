package com.it.zqm.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Describe: dao配置
 * @Author：zhaqianming
 * @Date: 2021/8/6
 */
@Configuration
@MapperScan(basePackages = {"com.it.zqm.dao.mapper"})
public class DaoConfig {
}
