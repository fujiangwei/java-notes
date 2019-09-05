package com.notes.java8.optional;

import com.notes.domain.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 文件描述 Optional使用
 **/
public class OptionalDemo {

    public static void main(String[] args) {

        //1、可用即返回
        User king = new User(1, "king");
        Optional<User> userOpt = Optional.of(king);
        User user = userOpt.orElse(null);
        System.out.println(user.getName());
        //不建议
        if (userOpt.isPresent()) {
            System.out.println(userOpt.get().getName());
        } else {
            //
        }
        User unknown = new User(0, "Unknown");
        User user1 = userOpt.orElse(unknown);
        System.out.println(user1.getName());
        //2、不可用则提供默认值
        User user2 = null;
        Optional<User> userOpt2 = Optional.ofNullable(user2);
        User user3 = userOpt2.orElse(unknown);
        System.out.println(user3.getName());
        //3、不可用则可以通过指定函数生成
        User user4 = userOpt2.orElseGet(() -> new User(0, "DEFAULT"));
        System.out.println(user4.getName());
        //4、 可用则使用,否则不进行任何操作
        userOpt.ifPresent(u -> System.out.println(u.getName()));
        userOpt2.ifPresent(u -> System.out.println(u.getName()));
        //5、 使用map方法获取关联数据
        System.out.println(userOpt.map(u -> u.getName()).orElse("Unknown"));
        System.out.println(userOpt2.map(u -> u.getName()).orElse("Default"));
        //6、 使用flatMap方法获取关联数据
        List<String> interests = new ArrayList<String>();
        interests.add("a");
        interests.add("b");
        interests.add("c");
        user.setInterests(interests);
        List<String> interests2 = Optional.of(user)
                .flatMap(u -> Optional.ofNullable(u.getInterests()))
                .orElse(Collections.emptyList());
        System.out.println(interests2.isEmpty());
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.orElse(unknown.getName()));
        Optional<Object> nullOpt = Optional.ofNullable(null);
        System.out.println(nullOpt.orElse(unknown.getName()));

    }

    /**
     * Supplier
     */
    private static Supplier<User> us = () -> new User(0, "DEFAULT");

    /**
     * Consumer
     */
//    private static Consumer<User> uc = (u) -> System.out.println(u.getName());
    private static Consumer<User> uc = u -> System.out.println(u.getName());

    private static Optional<User> getUser(User user) {
        return Optional.ofNullable(user);
    }
}
