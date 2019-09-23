package com.notes.spring.aop.controller;

import com.notes.spring.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/4/10
 * @time: 21:46
 * @modifier:
 * @since:
 */
@RestController
public class AOPController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser")
    public void addUser() {
//        userService.addUser();
        userService.updateUser();

        userService.findUser();
    }
}
