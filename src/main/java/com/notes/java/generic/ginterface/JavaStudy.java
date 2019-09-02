package com.notes.java.generic.ginterface;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/3/20
 * @time: 18:34
 * @modifier:
 * @since:
 */

/**
 * 传入具体的泛型参数类型，此时所有使用泛型的地方都需要改成传入的是参类型
 */
public class JavaStudy implements GenericInterface<String> {
    @Override
    public String study() {
        return "Java";
    }
}
