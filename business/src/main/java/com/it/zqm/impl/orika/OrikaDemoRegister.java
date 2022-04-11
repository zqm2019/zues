package com.it.zqm.impl.orika;

import com.it.zqm.orika.OrikaMapperRegister;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

/**
 * @Describe: 对象属性映射工具类
 * @Author：zhaqianming
 * @Date: 2021/8/25
 */
@Component
public class OrikaDemoRegister implements OrikaMapperRegister {
    @Override
    public void register(MapperFactory mapperFactory) {
        mapperFactory.classMap(Demo1.class, Demo2.class)
                // 不一样的字段指定映射关系
                .field("name1", "name")
                .field("demoDesc", "desc")
                // 属性名一样的使用，如果字段类型不一样，能自动转的没问题，不能转的会报错
                .byDefault()
                .register();
    }
}
