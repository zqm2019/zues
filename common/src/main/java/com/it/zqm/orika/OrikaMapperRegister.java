package com.it.zqm.orika;

import ma.glasnost.orika.MapperFactory;

/**
 * @Describe: orika 对象属性映射接口
 * @Author：zhaqianming
 * @Date: 2021/8/24
 */
public interface OrikaMapperRegister {
    /**
     *  注册对象属性映射工厂
     * @param mapperFactory
     */
    void register(MapperFactory mapperFactory);
}
