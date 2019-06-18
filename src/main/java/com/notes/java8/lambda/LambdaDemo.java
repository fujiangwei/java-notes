package com.notes.java8.lambda;

import com.notes.domain.User;
import com.notes.java8.functionInterface.Hello;

import java.util.function.*;

/**
 * 文件描述 lambda测试代码
 **/
public class LambdaDemo {

    public static void main(String[] args) {
        /************lambda用法begin************/
        //匿名内部类写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("内部类写法");
            }
        }).start();

        //lambda 写法
        new Thread(() -> System.out.println("lambda写法")).start();

        //入参为空
        TestDemo no_param = () -> "hi, no param";
        TestDemo no_param2 = () -> { return "hi, no param"; };
        System.out.println(no_param.hi());

        //单个参数
        TestDemo2 param = name -> name;
        TestDemo2 param2 = name -> { return name;};
        System.out.println(param.hei("hei, grils"));

        //多个参数
        TestDemo3 multiple = (String hello, String name) -> hello + " " + name;
        //一条返回语句，可以省略大括号和return
        TestDemo3 multiple2 = (hello, name) -> hello + name;
        //多条处理语句，需要大括号和return
        TestDemo3 multiple3 = (hello, name) -> {
            System.out.println("进入内部");
            return hello + name;
        };
        System.out.println(multiple.greet("hello", "lambda"));
        /************lambda用法end************/

        /************构造函数begin************/
        //ClassName::instanceMethod模式
        Supplier<User> us = User::new;
        //等效
        Supplier<User> us2 = () -> new User();
        //获取对象
        User user = us.get();

        //一个参数,参数类型不同则会编译出错
        Function<Integer, User> uf = id -> new User(id);
        //或加括号
        Function<Integer, User> uf2 = (id) -> new User(id);
        //等效
        Function<Integer, User> uf3 = (Integer id) -> new User(id);
        User apply = uf.apply(61888);

        //两个参数
        BiFunction<Integer, String, User> ubf = (id, name) -> new User(id, name);
        User 狂欢happy = ubf.apply(618, "狂欢happy");
        /************构造函数end************/

        /************方法引用begin************/
        //objectName::instanceMethod，对象实例方法：把表达式的参数值作为instanceMethod方法的参数
        Consumer<String> sc = System.out::println;
        //等效
        Consumer<String> sc2 = (x) -> System.out.println(x);
        sc.accept("618, 狂欢happy");

        //ClassName::staticMethod  类的静态方法：把表达式的参数值作为staticMethod方法的参数
        Function<Integer, String> sf = String::valueOf;
        //等效
        Function<Integer, String> sf2 = (x) -> String.valueOf(x);
        String apply1 = sf.apply(61888);

        //ClassName::instanceMethod  类的实例方法：把表达式的第一个参数当成instanceMethod的调用者，其他参数作为该方法的参数
        BiPredicate<String, String> sbp = String::equals;
        //等效
        BiPredicate<String, String> sbp2 = (x, y) -> x.equals(y);
        boolean test = sbp.test("a", "A");
        /************方法引用end************/

        C c = new C();
        c.hello();

    }

    public interface TestDemo {
        String hi();
    }

    /**
     * 当被lambda使用时将视为函数式接口，只允许一个abstarct方法，
     * 否则在使用时会报multiple non-overriding abstract methods found in interface xxx
     */
    public interface TestDemo2 {
        String hei(String hello);
    }

    public interface TestDemo3 {
        String greet(String hello, String name);
    }

}
