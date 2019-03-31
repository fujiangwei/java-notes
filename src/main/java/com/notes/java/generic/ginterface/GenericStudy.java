package com.notes.java.generic.ginterface;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/20
 * @time: 18:22
 * @modifier:
 * @since:
 */

/**
 * 不传入具体的泛型参数类型，此时需要将泛型的声明加上
 */
public class GenericStudy<T> implements GenericInterface<T> {
    @Override
    public T study() {
        return (T)"Python";
    }
}
