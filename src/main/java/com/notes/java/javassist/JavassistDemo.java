package com.notes.java.javassist;

import javassist.*;

import java.io.FileOutputStream;
import java.lang.reflect.Method;

/**
 * 文件描述 javassist
 **/
public class JavassistDemo {

    public static void main(String[] args) throws Exception {
//        ClassPool classPool = ClassPool.getDefault();
//        CtClass ctClass = classPool.makeClass("com.notes.java.javassist.School");
        //设置类属性
//        CtField sname = new CtField(classPool.getCtClass("java.lang.String"), "sname", ctClass);
//        sname.setModifiers(Modifier.PRIVATE);
//        ctClass.addField(sname);
//
//        CtField sid = new CtField(classPool.getCtClass("int"), "sid", ctClass);
//        sid.setModifiers(Modifier.PRIVATE);
//        ctClass.addField(sid);
//
        //设置getter/setter
//        ctClass.addMethod(CtNewMethod.setter("setSname", sname));
//        ctClass.addMethod(CtNewMethod.getter("getSname", sname));
//        ctClass.addMethod(CtNewMethod.setter("setSid", sid));
//        ctClass.addMethod(CtNewMethod.getter("getSid", sid));
//
        //设置构造函数
//        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
////        StringBuilder sd = new StringBuilder();
//        String body = String.format("{\n\tsname = \"浙大\";\n\tsid = 0001;\n}");
////        String body = String.format("{\n\tsname = \"浙大\";\n\tsid = %d;\n}", 0001);
//        System.out.println(body);
//        ctConstructor.setBody(body);
//        ctClass.addConstructor(ctConstructor);
//
        //设置函数
//        CtMethod print = new CtMethod(CtClass.voidType, "print", new CtClass[]{}, ctClass);
//        print.setModifiers(Modifier.PUBLIC);
//        String body1 = String.format("{\n\tSystem.out.println(sname);\n}");
//        System.out.println(body1);
//        print.setBody(body1);
//        ctClass.addMethod(print);
//
//        //调用print方法测试
//        Class aClass = ctClass.toClass();
//        Object o = aClass.newInstance();
//        o.getClass().getMethod("print", new Class[]{}).invoke(o, new Object[]{});
//
        //生成class文件
//        byte[] bytes = ctClass.toBytecode();
////        FileOutputStream fileOutputStream = new FileOutputStream("D://hs//develop//workspace//2019//java-notes//src//main//java//com//notes//java//javassist//School.class");
//        FileOutputStream fileOutputStream = new FileOutputStream("D:\\hs\\develop\\workspace\\2019\\java-notes\\src\\main\\java\\com\\notes\\java\\javassist\\School.class");
//        fileOutputStream.write(bytes);
//        fileOutputStream.close();

        ClassPool pool = new ClassPool();
        //类路径
        ClassClassPath classPath = new ClassClassPath(JavassistDemo.class);
        pool.insertClassPath(classPath);
        //获取待修改的类
        CtClass emp = pool.get("com.notes.java.javassist.Emp");
        //类中待修改的方法
        CtMethod printInfo = emp.getDeclaredMethod("printInfo");
        //前插入
        printInfo.insertBefore("{ System.out.println(\"ename:\" + $1); System.out.println(\"eno:\" + $2); }");
        //后插入
        printInfo.insertAfter("{ System.out.println(ename);System.out.println(eno);}");
        //写到类中
        emp.writeFile();

        //反射调用
        Class aClass = emp.toClass();
        Method printInfo1 = aClass.getMethod("printInfo", new Class[]{String.class, int.class});
        printInfo1.invoke(aClass.newInstance(), "浙大", 0002);

    }
}
