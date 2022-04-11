package com.it.zqm;

import com.it.zqm.impl.orika.Demo1;
import com.it.zqm.impl.orika.Demo2;
import com.it.zqm.utils.ObjectMapperUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/8/25
 */
public class OrikaDemoTest extends BaseTest {

    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    @Test
    public void testOrika() {
        Demo1 demo1 = new Demo1();
        // age
        demo1.setAge("1");
        demo1.setDemoDesc("demo1desc");
        demo1.setName1("name1");
        demo1.setSex("男");
        Demo2 demo2 = objectMapperUtils.convert(demo1, Demo2.class);
        Assert.assertNotNull(demo2);
    }
}
