package com.it.zqm.impl;

import com.it.zqm.dao.entity.NsProvince;
import com.it.zqm.dao.mapper.NsProvinceMapper;
import com.it.zqm.service.NsProvinceService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/8/10
 */
public class NsProvinceServiceImlp implements NsProvinceService {
    @Resource
    private NsProvinceMapper nsProvinceMapper;
    @Override
    public List<NsProvince> queryProvinceList(NsProvince nsProvince) {
        return nsProvinceMapper.queryNsProvinceList(nsProvince);
    }
}
