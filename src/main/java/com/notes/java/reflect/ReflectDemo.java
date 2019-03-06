package com.notes.java.reflect;

import com.notes.java.reflect.domain.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/6
 * @time: 20:34
 * @modifier:
 * @since:
 */
public class ReflectDemo {

    public static void main(String[] args) throws Exception {
//        //不会触发初始化
//        Class userClass = User.class;
//        System.out.println("***********");
//        //触发初始化
//        Class<?> forName = Class.forName("com.notes.java.reflect.domain.User");
        User user = new User(1, "kinson");
//        className(user);
//        instance();
//        constructor(user);
//        field(user);
        method(user);
    }

    public static void instance() throws Exception {
        try {
            Class<?> forName = Class.forName("com.notes.java.reflect.domain.User");
            //此处User要有无参构造
            User user = (User) forName.newInstance();
            System.out.println(user.getName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 构造器
     * @param user
     */
    public static void constructor(User user) {
        Class<? extends User> aClass = user.getClass();

        Constructor[] cs = aClass.getDeclaredConstructors();
        for (Constructor constructor : cs) {
            System.out.print(constructor.getName() + "(");
            //获取构造函数的参数列表---》得到的是参数雷彪的类类型
            Class[] paramTypes = constructor.getParameterTypes();
            for (Class class1 : paramTypes) {
                System.out.print(class1.getName() + ",");
            }
            System.out.println(")");
        }
    }

    /**
     * 字段
     *
     * @param user
     * @throws Exception
     */
    public static void field(User user) throws Exception {
        Class<? extends User> aClass = user.getClass();

        System.out.println("getFields public修饰的>>>>>>>");
        Field[] fields = aClass.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        System.out.println("getDeclaredFields获取一个类的所有字段>>>>>>>");
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println("field.getName()>>>" + field.getName() + "  " + field.getType().getSimpleName());
            //name属性
            if ("name".equals(field.getName())) {
                // 要设置属性可达，不然会抛出IllegalAccessException异常
                field.setAccessible(Boolean.TRUE);
                System.out.println("    1>>>" + field.get(user));
                //重新设置值
                field.set(user, "jack");
                System.out.println("    2>>>" + field.get(user));
                field.setAccessible(Boolean.FALSE);
            }
        }

        System.out.println("单个字段");
        try {
            Field name = aClass.getField("city");
            System.out.println(name.getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    /**
     * 方法
     *
     * @param user
     * @throws Exception
     */
    public static void method(User user) throws Exception {
        Class<? extends User> aClass = user.getClass();

        System.out.println("getMethods public修饰的>>>>>>>");
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
//            System.out.println(method.getName());
        }

        System.out.println("getDeclaredMethods当前类自己的所有方法>>>>>>>");
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            System.out.print(method.getName() + "(");

            //获取参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                System.out.print(parameterType.getName() + ",");
            }
            System.out.println(")");

            if ("print".equals(method.getName())) {
                method.invoke(user);
            }

            if ("hello".equals(method.getName())) {
                // 要设置属性可达，不然会抛出IllegalAccessException异常
                method.setAccessible(Boolean.TRUE);
                method.invoke(user);
                method.setAccessible(Boolean.FALSE);
            }

            if ("say".equals(method.getName())) {
                // 要设置属性可达，不然会抛出IllegalAccessException异常
                method.setAccessible(Boolean.TRUE);
                //参数对上
                method.invoke(user, "yyy");
                method.setAccessible(Boolean.FALSE);
            }
        }

        Method method = aClass.getMethod("print");
        System.out.println("method.getName()=====" + method.getName());
    }

    public static void className(User user) throws IllegalAccessException {
        Class<? extends User> aClass = user.getClass();

        //类名
        String className = aClass.getName();
        System.out.println(className + "+++" + aClass.getCanonicalName() + "+++"
                + aClass.getSimpleName() + "+++" + aClass.getTypeName()
        );

    }
}
