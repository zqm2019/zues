package com.it.zqm.job;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.it.zqm.dao.entity.NsProvince;
import com.it.zqm.dao.mapper.NsProvinceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Describe: job 测试
 * @Author：zhaqianming
 * @Date: 2021/9/8
 */
@Configuration
@EnableScheduling
@Slf4j
public class TestJob {
    @Resource
    private NsProvinceMapper mapper;

    /**
     * 项目启动后没5秒输出一次 关闭注解不自动运行
     */
//    @Scheduled(cron = "0/5 * * * * ?")
    private void test() {
        List<NsProvince> nsProvinces = mapper.queryNsProvinceList(new NsProvince());
        log.info(JSON.toJSONString(nsProvinces));
        PageHelper.startPage(1, 20);
        NsProvince nsProvince = new NsProvince();

        List<NsProvince> nsProvince1s = mapper.queryNsProvinceList(nsProvince);
        PageInfo<NsProvince> pageInfo = new PageInfo<>(nsProvince1s);
        //总数
        pageInfo.getTotal();
        //总页数
        pageInfo.getPages();
        nsProvince.setPageNum(1);
        nsProvince.setPageSize(10);
        List<NsProvince> nsProvince1s1 = mapper.queryNsProvinceList(nsProvince);
        PageInfo<NsProvince> pageInfo1 = new PageInfo<>(nsProvince1s1);
        log.info(JSON.toJSONString(pageInfo1));

        log.info("testJob!");
    }
}
