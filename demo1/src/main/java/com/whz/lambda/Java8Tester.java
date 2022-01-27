package com.whz.lambda;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

interface MathOperation {
    int operation(int a, int b);
}

interface GreetingService {
    void sayMessage(String message);
}

// java lambda 表达式
// Lambda 表达式主要用来订阅行内执行的方法类型接口，例如，一个简单方法接口。在下面例子中，我们使用各种类型的Lambda表达式来
// 定义MathOperation接口的方法。然后我们定义了sayMessage的执行
// Lambda 表达式免去了使用匿名方法的麻烦，并且给予Java简单但是强大的函数化的编程能力
//
public class Java8Tester {

    // 语法
    // (parameters) -> expression
    // 或
    // (parameters) -> { statements; }

    // 重要特征
    // 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值
    // 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号
    // 可选的大括号：如果主体包含了一个语句，就不需要使用大括号
    // 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定表达式返回了一个数值


    // 1. 不需要参数，返回值为 5
    // () -> 5
    // 2. 接收一个参数（数字类型），返回其2倍的值
    // x -> 2*x
    // 3. 接受2个参数（数字），并返回他们的差值
    // (x, y) -> x - y
    // 4. 接受2个int型参数，返回他们的和
    // (int x, int y) -> x + y
    // 5. 接受一个 string 对象，并在控制台打印，不返回任何值（看起来像是返回void）
    // (String s) -> System.out.print(s);

    final static String salutation = "Hello! ";

    public static void main(String args[]) {
        Java8Tester tester = new Java8Tester();

        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;

        // 大括号的返回语句
        MathOperation multiplication = (int a, int b) -> {return a*b;};

        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a/b ;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetingService1 = message -> System.out.println("Hello " + message);

        // 用括号
        GreetingService greetingService2 = (message) -> System.out.println("Hello " + message);

        greetingService1.sayMessage("Runoob");
        greetingService2.sayMessage("Google");

        // lambda 表达式只能引用标记了final的外层局部变量，这就是说不能再lambda内部修改定义在域外的局部变量
        GreetingService greetingService3 = message -> System.out.println(salutation + message);
        greetingService3.sayMessage("Rose");

        // 可以在lambda表达式中访问外层的局部变量
        final int num= 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);

        // lambda表达式的局部变量可以不声明为final，但是必须不可被后面的代码修改（即隐性的final）
        int num2 = 1;
        Converter<Integer, String> s2 = (param) -> System.out.println(String.valueOf(param + num2));
        s2.convert(2);
//        num2 = 5;

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Before Java8, too nuch code for too little to do");
//            }
//        }).start();
//
//        new Thread(() -> System.out.println("Before Java8, too nuch code for too little to do")).start();



        tester.doSthWith(name -> name + "喜欢aa");


        Person p1 = new Person(1, "嘤");
        Person p2 = new Person(24, "小涨");
        Person p3 = new Person(24, "不惑");
        Person p4 = new Person(56, "耳顺");
        Person p5 = new Person(68, "知天命");
        Person p6 = new Person(56, "古来稀");

        List<Person> list = Arrays.asList(p1,p2,p3,p4,p5);

        list.stream().filter(p -> p.age < 40).collect(Collectors.toList()).forEach(f -> System.out.println(f.name));

//        list.stream().filter(p -> p.age < 40).collect(Collectors.toMap(m -> m.age, m -> m.name)).forEach((k, v) -> System.out.println(k+" "+v));
        // key重复 后者覆盖前者(k1, k2) -> k2
        list.stream().filter(p -> p.age < 40).collect(Collectors.toMap(m -> m.age, m -> m.name, (k1, k2) -> k2)).forEach((k, v) -> System.out.println(k+" "+v));
        list.stream().count();

        List<Integer> numList = new ArrayList<>();
        Collections.addAll(numList, 1, 2,3,4,5,6,7,8);
        numList.forEach(System.out::print);

        Map<String, Person> map1 = new HashMap<String, Person>(){{
            put(p1.name, p1);
            put(p2.name, p2);
        }};

        // 第一层括号定义了一个匿名类；第二层实例初始化块
        Map<String, Person> map2 = new HashMap<String, Person>(){{
            put(p3.name, p3);
            put(p4.name, p4);
            put(p5.name, p5);
            put(p6.name, p6);
        }};

        System.out.println("\r\n-------------------------------");
        Map<String, Person> map3 = new HashMap<>(map1);
//        map3.merge(k, v, (v1, v2) -> new Person(v1.age, v1.name));
        map2.forEach((k ,v) -> {
            map3.merge(k, v, (v1, v2) -> new Person(v2.age, v2.name));
            System.out.println(k+" "+v.age+ " " + v.name);
        });
        System.out.println("\r\n-------------------------------");
        map3.forEach((k ,v) -> {
            System.out.println(k+" "+v.age+ " " + v.name);
        });
        System.out.println("\r\n-------------------------------");


        Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> new Person(v2.age, v2.name)))
                .forEach((k ,v) ->System.out.println(k+" "+v.age+ " " + v.name));

        System.out.println("\r\n-------------------------------");
        Stream.of(map1, map2).flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> new Person(v1.age, v2.name)))
                .forEach((k ,v) ->System.out.println(k+" "+v.age+ " " + v.name));


        Map<String, Person> map4 = map2.entrySet()

                .stream()

                .collect(Collectors.toMap(

                        Map.Entry::getKey,

                        Map.Entry::getValue,

                        (v1, v2) -> new Person(v1.age, v2.name),

                        () -> new HashMap<>(map1)));

//        Map<String, Person> map7 = EntryStream.of(map1)
//
//                .append(EntryStream.of(map2))
//
//                .toMap((e1, e2) -> e1);


    }

    void doSthWith(Function<String,String> do_sth)
    {
        String name = "ss";
        String result = do_sth.apply(name);
        System.out.println(result);
    }

    static public class Person
    {
        public int age;
        public String name;

        public Person(int age, String name) {
            this.name = name;
            this.age = age;
        }
    }





    public interface Converter<T1, T2> {
        void convert(int i);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
