package com.it.zqm;

import com.it.zqm.controller.TestController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/12/24
 */
public class TestControllerTest extends BaseTest {

    @Autowired
    private TestController testController;
    @Test
    public void testHello(){
        String s = testController.testHello();
        Assert.assertNotNull(s);
    }
}
