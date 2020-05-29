package com.notes.designpattern.singleton;

import com.notes.domain.MyBean;
import com.notes.domain.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件描述 对象管理
 **/
public class BeanManager {

    /**
     * 存储所有被管理的对象
     */
    private static Map<String, Object> beanMap = new HashMap<>();

    /**
     * 为了管理对象的产生
     * 对象的控制权给当前类负责
     * 生命周期托管实现对象的单例
     * IOC控制反转
     */
    public static <T> T getBean(Class<T> clazz) {
        T obj = null;
        try {
            String clazzName = clazz.getName();
            String classSimpleName = clazz.getSimpleName();
            // 存在获取返回，不存在则新建并保存返回
            if(beanMap.containsKey(classSimpleName)) {
                obj = (T)beanMap.get(classSimpleName);
                if (null == obj) {
                    // 获取类名
                    Class<?> aClass = Class.forName(clazzName);
                    // 反射实例化
                    obj = (T)aClass.newInstance();
                    // 放入缓存
                    beanMap.put(classSimpleName, obj);
                }
                return obj;
            }

            // 获取类名
            Class<?> aClass = Class.forName(clazzName);
            // 反射实例化
            obj = (T)aClass.newInstance();
            // 放入缓存
            beanMap.put(classSimpleName, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static void main(String[] args) {
        User myBean = BeanManager.getBean(User.class);
        User myBean2 = BeanManager.getBean(User.class);
        System.out.println("myBean:" + myBean);
        System.out.println("myBean2:" + myBean2);
        System.out.println(myBean == myBean2);
    }
}