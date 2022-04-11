package com.it.zqm.utils;

import com.it.zqm.config.YamlFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Describe: 获取 指定yml文件属性
 * @Author：zhaqianming
 * @Date: 2021/12/6
 */
@Component
@PropertySource(value = "application.yml", factory = YamlFactory.class)
@ConfigurationProperties(prefix = "package")
@Data
public class VersionYamlUtils {
    private String branch;
    private String version;
}
