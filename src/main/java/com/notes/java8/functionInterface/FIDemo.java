package com.notes.java8.functionInterface;

import com.notes.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

/**
 * 文件描述 java8基础函数接口demo
 **/
public class FIDemo {

    public static void main(String[] args) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 6; i++) {
            users.add(new User(i, "name" + i));
        }

        users.stream()
                //过滤出
                .filter(up)
                //遍历处理
                .map(uf)
                //遍历
                .forEach(uc);

        //测试
        User user = new User(88, "bb");

        String name = uft.apply(user);
        System.out.println(name);

        uc.accept(user);

        User user1 = us.get();
        System.out.println(user1.getName());

        boolean test = up.test(user);
        System.out.println(test);

        String fjw = bf.apply(user, "fjw");
        System.out.println(fjw);

        bc.accept(user, "fjw");
    }

    /**
     * Function<T, R>
     */
    private static Function<User, User> uf = u -> u;

    /**
     * Function<T, R>
     */
    private static Function<User, String> uft = u -> u.getName();

    /**
     * Consumer<T>
     */
    private static Consumer<User> uc = u -> System.out.println(u.getName());

    /**
     * Supplier<T>
     */
    private static Supplier<User> us = () -> new User(1, "us");

    /**
     * Predicate<T>
     */
    private static Predicate<User> up = u -> !u.getName().isEmpty();

    /**
     * BiFunction<T, U, R>
     */
    private static BiFunction<User, String, String> bf  = (u, name) -> name + " " + u.getName();

    /**
     * BiConsumer<T, U>
     */
    private static BiConsumer<User, String> bc  = (u, name) -> System.out.println(name + " " + u.getName());
}
