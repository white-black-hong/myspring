package com.whz.function;


import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Vector;
import java.util.function.Function;

public class FanXing {

    public static void main(String[] args) { (new FanXing()).act(); }


    public void act() {

        Vector<First> vs = new Vector<>();
//        vs.add(new Base()); // 错误，First容器只能装First及其子类，不能装First的超类
        vs.add(new Second());

        // 多态存在的三个必要条件
        // 继承
        // 重写
        // 父类引用指向子类对象: Parent p = new Child();
        // Parent p; 将a声明为父类对象，只是一个引用，未分配空间
        // Child temp = new Child(); 通过Child类的构造函数建立了一个Child类对象的实例，也就是初始化
        // p = (Parent)Child; 将子类对象temp转化为父类对象并赋值p，这就是上传（upcase），是安全的
        List<? super First> vSuper = new Vector<Base>(); //可以容纳本类（Second）及子类，但无法容纳超类
//        vSuper.add(new Base());
//        vSuper.add(new First());
        vSuper.add(new Second());
        vSuper.add(new Third());



        this.checkSuper(new Vector<First>()); // 可以传递超类、本类参数，但无法传递子类参数
        this.checkSuper(new Vector<Base>());
//        this.checkSuper(new Vector<>(Third));

        List<? extends Second> vExtends = new Vector<Third>(); // 不能容纳任何子类、本类、超类
//        vExtends.add(new First());
//        vExtends.add(new Second());
//        vExtends.add(new Third());
        vExtends.add(null); // 正确，但是没用

//        this.checkExtends(new First()); // 错误，无法传递超类参数
        this.checkExtends(new Vector<Second>());
        this.checkExtends(new Vector<Third>());

        //List<? super T> 当容器用时，能容纳T本身及T的子类，但无法容纳T的超类。用来向函数传递参数时，只能传递T及T的超类
        //List<? extends T> 当容器用时，只能容纳null，没什么用处。用来向函数传递参数时，只能传递T及T的子类，不能传递T的超类

        Second second = new Second();
        second.setName("qiao");
        second.setId("11");
        second.setKind("11");


        final Function<Second, First> firstBaseFunction = t -> (First) t;
        final Function<First, Base> firstBaseFunction1 = t -> (Base)t;
        final Base apply1 = firstBaseFunction1.compose(firstBaseFunction).apply(second);
        System.out.println(apply1);

        // second extends first
        final Function<Second, Second> firstBaseFunction2 = t -> t;
        final Base apply2 = firstBaseFunction1.compose(firstBaseFunction2).apply(second);
        System.out.println(apply2);

        // second extends first
        final Function<Base, First> firstBaseFunction3 = t -> (First)t;
        final Base apply3 = firstBaseFunction1.compose(firstBaseFunction3).apply(second);
        System.out.println(apply3);


        Function<Integer, Integer> times2 = i -> i*2;
        Function<Integer, Integer> squared = i -> i*i;

        System.out.println(times2.apply(4));

        System.out.println(squared.apply(4));

        //32                先4×4然后16×2,先执行apply(4)，在times2的apply(16),先执行参数，再执行调用者。
        System.out.println(times2.compose(squared).apply(4));

        //64               先4×2,然后8×8,先执行times2的函数，在执行squared的函数。
        System.out.println(times2.andThen(squared).apply(4));

        //16
        System.out.println(Function.identity().compose(squared).apply(4));

    }

    @Data
    @ToString
    public class Base {
        String id;
    }
    @Data
    public class First extends Base{
        String name;

        @Override
        public String toString() {
            return "First{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
    @Data
    public class Second extends First {
        String kind;

        @Override
        public String toString() {
            return "Second{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", kind='" + kind + '\'' +
                    '}';
        }
    }
    @Data
    public class Third extends Second {
        String color;

        @Override
        public String toString() {
            return "Third{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", kind='" + kind + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    public void checkSuper(List<? super Second> a) {

    }

    public void checkExtends(List<? extends Second> a) {

    }
}
