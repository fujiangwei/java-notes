package com.notes.spi.impl;

import com.notes.spi.SPIService;

/**
 * 文件描述
 **/
public class SPIServiceImpl implements SPIService {
    @Override
    public void execute() {
        System.out.println("SPIServiceImpl execute()");
    }
}