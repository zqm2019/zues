package com.it.zqm.service;

import com.it.zqm.dao.entity.NsProvince;

import java.util.List;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/8/10
 */
public interface NsProvinceService {

    List<NsProvince> queryProvinceList(NsProvince nsProvince);
}
