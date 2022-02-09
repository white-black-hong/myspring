package com.whz.helper;

import com.whz.annotation.Autowired;
import com.whz.util.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

/**
 * @auther whz
 * @create 2022-01-27 16:14
 */
public final class IocHelper {
    /**
     * 遍历bean容器所有bean的属性，为所有带注解的属性注入实例
     * 关键字 1.bean容器(@Service @Controller) 2.bean属性 3.带注解的bean 4.注入实例
     */
    static {
        // 遍历bean容器里的所有bean
        // beanMap {Class@849}com.whz.Controller.UserController -> {UserController@978}
        // {Class@853} -> com.whz.Service.UserService {com.whz.Service.UserService$$EnhancerByCGLIB$$5dba8b14@727803de}
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (MapUtils.isNotEmpty(beanMap)) {
            // beanEntry key class com.whz.Controller.UserController value
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                // bean的class类
                // class com.whz.Controller.UserController
                Class<?> beanClass = beanEntry.getKey();
                // bean的实例
                // = {UserController} >> userService = null
                Object beanInstance = beanEntry.getValue();
                // 暴利反射获取属性
                // TODO ？？？？？？？？？？？？？？？
                // getDeclaredFields 获取某个类的所有声明字段，即包括public、private、protected，但是不包括父类的申明字段
                // [0] private com.whz.Service.IUserService com.whz.Controller.UserController.userService
                Field[] beanFields = beanClass.getDeclaredFields();
                // 遍历bean的属性
                if (ArrayUtils.isNotEmpty(beanFields)) {
                    for (Field beanField : beanFields) {
                        // 判断属性是否带Autowired注解
                        if (beanField.isAnnotationPresent(Autowired.class)) {
                            // 属性类型
                            // class com.whz.Service.UserService
                            Class<?> beanFieldClass = beanField.getType();
                            // 如果beanFieldClass是接口，就获取接口对应的实现类
                            beanFieldClass = findImplementClass(beanFieldClass);
                            // 获取Class类对应的实例
                            Object beanFieldInstance = beanMap.get(beanFieldClass);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取接口对应的实现类
     */
    public static Class<?> findImplementClass(Class<?> interfaceClass) {
        Class<?> implementClass = interfaceClass;
        // 接口对应的所有实现类
        Set<Class<?>> classSetBySuper = ClassHelper.getClassSetBySuper(interfaceClass);
        if (CollectionUtils.isNotEmpty(classSetBySuper)) {
            // 获取第一个实现类
            implementClass = classSetBySuper.iterator().next();
        }
        return implementClass;
    }
}
