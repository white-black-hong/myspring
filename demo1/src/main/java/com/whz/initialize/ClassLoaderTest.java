package com.whz.initialize;

public class ClassLoaderTest {

    public  static void main(String[] args) {

        // TODO Auto-generated method stub

//
//        // BootstrapClassLoader JRE目录下的jar包或者是class文件
//        System.out.println(System.getProperty("sun.boot.class.path"));
//        // ExtClassLoader 一个或多个扩展目录的路径
//        System.out.println(System.getProperty("java.ext.dirs"));
        // AppClassLoader 当前java工程目录路径，java类路径
//        System.out.println(System.getProperty("java.class.path"));

        ClassLoader cl = Test.class.getClassLoader();

        System.out.println("ClassLoader is:" + cl.toString());
        // 父加载器
        System.out.println("ClassLoader\'s parent is:" + cl.getParent().toString());
        // 无父父加载器
//        System.out.println("ClassLoader\' grand is:" + cl.getParent().getParent().toString());



//        cl = int.class.getClassLoader();
//
//        System.out.println("ClassLoader is:" + cl.toString());

    }
}
