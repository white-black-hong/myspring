package com.whz.initialize;

import com.whz.entity.God;
import com.whz.entity.Person;

public class PersonTest {

    public static void main(String[] args) {

        /*
         * 对象的实例化过程是分成两部分：类的加载初始化，对象的初始化
         * 要创建类的对象实例需要先加载并初始化该类，main方法所在的类需要先加载和初始化
         * 类初始化就是执行<clinit>方法，对象实例化是执行<init>方法
         * 一个子类要初始化需要先初始化父类
         */

        // ① 先加载God.class 再加载Person.class
        // ② 在栈中申请空间，声明引用变量 person: null
        // ③ 在堆内存中开辟一个空间并分配地址; 堆内存地址: Person@512
        // ④ 对象中的属性默认初始化 包括父类
        //  age: 0
        //  name: null
        //  skill: 0
        //  position: null
        // ⑤ 子类构造函数方法进栈
        // ⑥ 显示初始化子类的信息
        //  skill: 2
        //  position: null
        // ⑦ 父类构造函数方法进栈，执行完出栈
        // ⑧ 显示初始化子类的信息
        //  age: 100000
        //  name: "Monkey King"
        // ⑨ 初始化结束后将堆内存中的地址赋值给引用变量 person:Person@512 然后子类的构造函数方法出栈

        Person person = new Person();
        System.out.println("&person=" + System.identityHashCode(person));
        System.out.println("&person=" + person.hashCode());
        System.out.println("person=" + person + ",position=" +person.getPosition());


        God g = new Person();
        g.m("3333");
    }
}
