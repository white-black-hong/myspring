package com.whz.initialize;

import javafx.scene.Parent;

public class StaticOrderTest {

    private int id = 1;

    private static StaticOrderTest staticOrder = new StaticOrderTest();

    private static String path = "class.path";

    public static StaticOrderTest getStaticOrderTest() {
        return staticOrder;
    }

    private Parent parent;

    public StaticOrderTest() {
        System.out.println("加载StaticOrderTest构造函数");
    }

    public static synchronized void init() {
        System.out.println("加载init()");
    }

}

class testSO {
    public static void main(String[] args) {
        // 默认初始化属性
        // 显示初始化属性
        // 构造函数方法进栈，构造函数方法出栈
        // 静态属性默认初始化
        StaticOrderTest staticOrderTest = new StaticOrderTest();
    }
}