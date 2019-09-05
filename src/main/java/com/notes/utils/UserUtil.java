package com.notes.utils;

import com.notes.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 文件描述 获取测试数据
 **/
public class UserUtil {

    /**
     * @return List<User>
     */
    public static List<User> getUsers(int num) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < num; i++) {
            users.add(new User(i, "name" + i, Arrays.asList(new String[]{"interest" + 1})));
        }

        return users;
    }
}
