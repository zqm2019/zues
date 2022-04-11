package com.it.zqm.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/8/23
 */
@Data
public class CreateSliceRo {

    @NotNull
    @NotBlank
    private String snName;
    @NotNull
    private Integer mcc;
    @NotNull
    @NotNull
    private Integer mnc;
    /**
     * 切片类型
     */
    @NotNull
    @Range(min = 0, max = 20)
    private Byte sst;

    /**
     * 切片分区编号
     */
    private String sd;

    /**
     * 最大用户数
     */
    private Integer maxNumUser;

    /**
     * 最大上行速率
     */
    private Integer maxUlThpt;

    /**
     * 最大下行速率
     */
    private Integer maxDlThpt;

    /**
     * ue网段
     */
    private String ueSubnet;

}
