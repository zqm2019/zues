package com.it.zqm.dao.entity;


import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;

/**
 * 省份设置表 t_ns_province
 *
 * @author sylincom
 * @date 2020-11-04
 */
@Data
public class NsProvince  implements Serializable {

    private static final long serialVersionUID = -6903243427609485167L;
    /**
     * 自增列
     */
    private Integer provinceId;
    /**
     * 省份代码
     */
    private String provinceCode;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 简称
     */
    private String shortName;
    /**
     * 经度
     */
    private String lng;
    /**
     * 纬度
     */
    private String lat;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 备注
     */
    private String memo;
    /**
     * 状态
     */
    private Integer dataState;
    /**
     * 租户ID
     */
    private String tenantCode;
    /**
     * 下一个代码
     */
    private String nextCode;

    private Integer pageNum;

    private Integer pageSize;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("provinceId", getProvinceId())
                .append("provinceCode", getProvinceCode())
                .append("provinceName", getProvinceName())
                .append("shortName", getShortName())
                .append("lng", getLng())
                .append("lat", getLat())
                .append("sort", getSort())
                .append("gmtCreate", getGmtCreate())
                .append("gmtModified", getGmtModified())
                .append("memo", getMemo())
                .append("dataState", getDataState())
                .append("tenantCode", getTenantCode())
                .append("nextCode", getNextCode())
                .toString();
    }
}
