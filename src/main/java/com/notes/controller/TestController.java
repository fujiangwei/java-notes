package com.notes.controller;

import com.notes.utils.LocalConfigInfoUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件描述
 **/
@RestController
@RequestMapping(value = "test")
public class TestController {

    @GetMapping(value = "hello")
    public String test() {

        return "ok";
    }

    @GetMapping(value = "cpuInfo")
    public String cpuInfo() {
        try {
            LocalConfigInfoUtils.cpuInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
