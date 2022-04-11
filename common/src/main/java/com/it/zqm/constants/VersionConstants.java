package com.it.zqm.constants;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/12/29
 */
public class VersionConstants {
    private VersionConstants(){
    }
    /**
     * 提测版本打包可升级 如V1.0.0
     * BUG修复打包可设置小版本 如 V1.0.0.1  完全没BUG 需要重新升级版本包
     */
    public static final String VERSION = "V1.0.0";
    /**
     * 记录打包时间
     */
    public static final String DATE = "2021-12-07";
    /**
     * 打包的分支，基于远端某一稳定分支
     */
    public static final String BRANCH = "test";
    /**
     * 当前分支最后一次提交的commit
     */
    public static final String COMMIT_ID = "30e542bff8afb05c30e33cd4d16cdf73d594dc14";

}
