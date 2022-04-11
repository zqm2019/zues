package com.it.zqm;

import com.it.zqm.config.ApplicationContextConfig;
import com.it.zqm.utils.VersionYamlUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/12/6
 */
public class YamlTest extends BaseTest {
    @Autowired
    private VersionYamlUtils versionYamlUtils;

    @Test
    public void testReadYaml() {
        String branch = versionYamlUtils.getBranch();
        Assert.assertNotNull("res", branch);
    }

    @Test
    public void testReadYaml2() {
        Environment environment = ApplicationContextConfig.get(Environment.class);
        Assert.assertNotNull("res", environment.getProperty("package.version"));
    }
}
