package com.it.zqm.utils;

import com.google.common.collect.Lists;
import com.it.zqm.orika.OrikaMapperRegister;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

/**
 * @Describe: 对象之间属性传值工具
 * @Author：zhaqianming
 * @Date: 2021/8/24
 */
@Component
public class ObjectMapperUtils {
    private MapperFacade mapperFacade = null;

    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    @Autowired(required = false)
    private List<OrikaMapperRegister> orikaMapperRegisterList = Lists.newArrayList();

    @PostConstruct
    private void init() {
        if (CollectionUtils.isNotEmpty(orikaMapperRegisterList)) {
            for (OrikaMapperRegister orikaMapperRegister : orikaMapperRegisterList) {
                orikaMapperRegister.register(mapperFactory);
            }
        }
        mapperFacade = mapperFactory.getMapperFacade();
    }

    /**
     * 单个对象属性值映射
     *
     * @param source 原来的对象
     * @param target 目标对象类型
     * @param <S>
     * @param <T>
     * @return
     */
    public <S, T> T convert(S source, Class<T> target) {
        return mapperFacade.map(source, target);
    }

    /**
     * 对象集合属性映射
     *
     * @param sourceList
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public <S, T> List<T> convertList(List<S> sourceList, Class<T> target) {
        return CollectionUtils.isEmpty(sourceList) ? Collections.emptyList() : mapperFacade.mapAsList(sourceList, target);
    }
}
