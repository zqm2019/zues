package com.it.zqm.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/8/10
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory simpleClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();

        /**
         *  数据传输时间 单位毫秒
         */
        simpleClientHttpRequestFactory.setReadTimeout(5000);
        /**
         *  连接时间 单位毫秒
         */
        simpleClientHttpRequestFactory.setConnectTimeout(5000);
        return simpleClientHttpRequestFactory;
    }
}
