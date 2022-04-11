package com.it.zqm;

import com.it.zqm.dao.entity.NsProvince;
import com.it.zqm.dao.mapper.NsProvinceMapper;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class ApplicationTest extends BaseTest {
    @Resource
    private NsProvinceMapper mapper;

    @Test
    public void test() {
        Assert.assertNotNull("not null", new NsProvince());
    }


    @Test
    public void testMapper() {
        List<NsProvince> nsProvinces = mapper.queryNsProvinceList(new NsProvince());
        Assert.assertNotNull("not null", nsProvinces);
    }


//    @Test
//    public void testError(){
//       Assert.assertNotNull("这是测试jenkins单元测试失败的场景" , null);
//    }


}
