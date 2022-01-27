package com.whz.entity;

import lombok.Data;

@Data
public class Person extends God{

    // 1. 对象中的属性默认初始化 属性是指对象相关的值
    // age: 0
    // name : null
    // 4. 显示初始化子类的信息
    // age: 100000
    // name: "Monkey King"
    private int age = 100000;
    private String name = "Monkey King";

    public Person() {
        super();
        // 5. 初始化结束后将堆内存中的地址赋值给引用变量 然后子类的构造函数方法出栈
        System.out.println("Persion Class Construtor");
    }

    @Override
    public void m(String position) {
        super.m("2222");
        System.out.println("子类的 m() 方法");
        this.setPosition(position);
    }

    public void n() {
        System.out.println("子类的 n() 方法");
    }
}


