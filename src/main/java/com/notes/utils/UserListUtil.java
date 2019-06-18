package com.notes.utils;

import com.notes.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述 获取测试数据
 **/
public class UserListUtil {

    /**
     *
     * @return List<User>
     */
    public List<User> getUsers(int num) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < num; i++) {
            users.add(new User(i, "name" + i));
        }

        return users;
    }
}
