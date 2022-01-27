package com.whz.helper;

import com.whz.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @auther whz
 * @create 2022-01-27 16:02
 */
public final class BeanHelper {

    /**
     * BEAN_MAP 相当于一个Spring容器，拥有应用所有bean的实例
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object >();

    static {
        // 获取应用中所有bean
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        // 将bean实例化，并放入bean容器zhong
        for (Class<?> beanClass : beanClassSet) {
            Object obj = ReflectionUtil.newInstance(beanClass);
            BEAN_MAP.put(beanClass, obj);
        }
    }

    /**
     * 获取Bean容器
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     */
    public static <T> T getBean(Class<T> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T)BEAN_MAP.get(cls);
    }

    /**
     * 设置Bean实例
     */
    public static void setBean(Class<?> cls, Object obj) {
        BEAN_MAP.put(cls, obj);
    }
}
