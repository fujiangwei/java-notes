package com.notes.java.generic;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/20
 * @time: 17:45
 * @modifier:
 * @since:
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 参数T可以为任意标识，最好使用常见的如T、E、K、V等形式的参数常用于表示泛型
 * 在实例化泛型类时，必须指定T的具体类型
 */
public class GenericClass<T> {
    /**
     * 泛型变量
     */
    private T var;

    List<String>[] ls = new ArrayList[10];

    public GenericClass(T var) {
        this.var = var;
    }

    /**
     * 泛型方法
     *
     * @return
     */
    public T getVar() {
        return var;
    }

    /**
     * 泛型方法
     *
     * @param var
     * @param <T>
     * @return
     */
    public <T> T show(T var) {
        return var;
    }

    /**
     * 静态泛型方法
     * @param genericClass
     * @param <T>
     * @return
     */
    public static <T> T print(GenericClass<T> genericClass) {
        return genericClass.getVar();
    }
}
