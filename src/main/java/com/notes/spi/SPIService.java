package com.notes.spi;

/**
 * 文件描述 SPI ，全称为 Service Provider Interface，是一种服务发现机制。
 * 它通过在ClassPath路径下的META-INF/services文件夹查找文件，自动加载文件里所定义的类。
 * 在ClassPath路径下配置添加一个文件。文件名字是接口的全限定类名，内容是实现类的全限定类名，多个实现类用换行符分隔。
 **/
public interface SPIService {
    void execute();
}
