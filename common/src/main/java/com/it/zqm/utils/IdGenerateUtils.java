package com.it.zqm.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.UUID;

/**
 * @Describe:
 * @Author：zhaqianming
 * @Date: 2021/8/6
 */
@Slf4j
public final class IdGenerateUtils {

    public static final String RANDOM_GROUP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private IdGenerateUtils() {
    }

    /**
     * 获取 uuid
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 生成一次指定长度的随机数
     * @param length 制定长度
     * @return
     */
    public static String getRandomNumByLength(int length) {

        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < length; i++) {
            char ch = RANDOM_GROUP.charAt(new Random().nextInt(RANDOM_GROUP.length()));
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        log.info(getUUID());
        log.info(getRandomNumByLength(4));
        log.info(getRandomNumByLength(7));
    }
}
