package com.it.zqm;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Describe: 测试基类 所有单元测试都可继承就无须添加注解了
 * 建议一个服务一个测试类，单元测试随着需求变更而维护
 * 改动之前的代码或者提交代码前需走相应改动地方的单元测试
 * @Author：zhaqianming
 * @Date: 2021/8/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {
    @Test
    public void baseTest() {
        Assert.assertNotNull("not null", "baseTest");
    }


}
