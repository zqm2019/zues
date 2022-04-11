package com.it.zqm.dao.mapper;


import com.it.zqm.dao.entity.NsProvince;

import java.util.List;

/**
 * 省份设置 数据层
 *
 * @author sylincom
 * @date 2020-11-04
 */
public interface NsProvinceMapper {

    /**
     * 查询省份设置列表
     *
     * @param nsProvince 省份设置信息
     * @return 省份设置集合
     */
    List<NsProvince> queryNsProvinceList(NsProvince nsProvince);


}