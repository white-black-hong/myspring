package com.whz.entity;

import lombok.Data;

@Data
public class God {

    //  1. 对象中的属性默认初始化
    //  2. 显示初始化父类的属性
    //  skill: 2
    //  position: null
    private int skill  = 2;
    private String position;

    public God() {
        // 3. 父类构造函数方法进栈，执行完出栈
        m("1234");
        System.out.println("God Class Construtor");
    }

    public void m(String position) {
        System.out.println("父类的 m() 方法");

        this.position = position;
    }

}
