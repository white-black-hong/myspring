package com.whz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 * @auther whz
 * @create 2022-01-27 15:37
 */
public final class ReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 创建实例
     */
    public static Object newInstance(Class<?> cls) {
        Object instance;
        try {
            // newInstance()和new()区别：
            //　1、两者创建对象的方式不同，前者是实用类的加载机制，后者则是直接创建一个类：
            //　2、newInstance创建类是这个类必须已经加载过且已经连接，new创建类是则不需要这个类加载过/
            //　3、newInstance: 弱类型(GC是回收对象的限制条件很低，容易被回收)、低效率、只能调用无参构造，
            // new 强类型（GC不会自动回收，只有所有的指向对象的引用被移除是才会被回收，若对象生命周期已经结束，但引用没有被移除，经常会出现内存溢出）
            instance = cls.newInstance();
        } catch (Exception e) {
            LOGGER.error("new instance failure", e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 创建实例（根据类名）
     */
    public static Object newInstance(String className) {
        Class<?> cls = ClassUtil.loadClass(className);
        return newInstance(cls);
    }

    /**
     * 调用方法
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result;
        try {
            method.setAccessible(true);
            result = method.invoke(obj, args);
        } catch (Exception e) {
            LOGGER.error("invoke method failure", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     */
    public static void setField(Object obj, Field field, Object value) {
        try {
            field.setAccessible(true); // 去除私有权限
            field.set(obj, value);
        } catch (Exception e) {
            LOGGER.error("set field failure", e);
            throw new RuntimeException(e);
        }
    }

}
