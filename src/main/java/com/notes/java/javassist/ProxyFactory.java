package com.notes.java.javassist;

import javassist.*;
import javassist.bytecode.AccessFlag;

import java.lang.reflect.InvocationHandler;

public class ProxyFactory {

    public static Object newProxyInstance(ClassLoader loader, Class<?> interfaceClass, InvocationHandler h) throws Throwable {
        ClassPool pool = ClassPool.getDefault();

        // 1.创建代理类：public class NewProxyClass
        CtClass proxyCc = pool.makeClass("NewProxyClass");

        /* 2.给代理类添加字段：private InvocationHandler h; */
        CtClass handlerCc = pool.get(InvocationHandler.class.getName());
        CtField handlerField = new CtField(handlerCc, "h", proxyCc); // CtField(CtClass fieldType, String fieldName, CtClass addToThisClass)
        handlerField.setModifiers(AccessFlag.PRIVATE);
        proxyCc.addField(handlerField);

        /* 3.添加构造函数：public NewProxyClass(InvocationHandler h) { this.h = h; } */
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{handlerCc}, proxyCc);
        ctConstructor.setBody("$0.h = $1;"); // $0代表this, $1代表构造函数的第1个参数
        proxyCc.addConstructor(ctConstructor);

        /* 4.为代理类添加相应接口方法及实现 */
        CtClass interfaceCc = pool.get(interfaceClass.getName());

        // 4.1 为代理类添加接口：public class NewProxyClass implements IHello
        proxyCc.addInterface(interfaceCc);

        // 4.2 为代理类添加相应方法及实现
        CtMethod[] ctMethods = interfaceCc.getDeclaredMethods();
        for (int i = 0; i < ctMethods.length; i++) {
            String methodFieldName = "m" + i; // 新的方法名

            // 4.2.1 为代理类添加反射方法字段
            // 如：private static Method m1 = Class.forName("chapter9.javassistproxy3.IHello").getDeclaredMethod("sayHello2", new Class[] { Integer.TYPE });
            /* 构造反射字段声明及赋值语句 */
            String classParamsStr = "new Class[0]"; // 方法的多个参数类型以英文逗号分隔
            if (ctMethods[i].getParameterTypes().length > 0) { // getParameterTypes获取方法参数类型列表
                for (CtClass clazz : ctMethods[i].getParameterTypes()) {
                    classParamsStr = (("new Class[0]".equals(classParamsStr)) ? clazz.getName() : classParamsStr + "," + clazz.getName()) + ".class";
                }
                classParamsStr = "new Class[] {" + classParamsStr + "}";
            }
            String methodFieldTpl = "private static java.lang.reflect.Method %s=Class.forName(\"%s\").getDeclaredMethod(\"%s\", %s);";
            String methodFieldBody = String.format(methodFieldTpl, "m" + i, interfaceClass.getName(), ctMethods[i].getName(), classParamsStr);
            // 为代理类添加反射方法字段. CtField.make(String sourceCodeText, CtClass addToThisClass)
            CtField methodField = CtField.make(methodFieldBody, proxyCc);
            proxyCc.addField(methodField);

            System.out.println("methodFieldBody: " + methodFieldBody);

            /* 4.2.2 为方法添加方法体 */
            /* 构造方法体. this.h.invoke(this, 反射字段名, 方法参数列表); */
            String methodBody = "$0.h.invoke($0, " + methodFieldName + ", $args)";
            // 如果方法有返回类型，则需要转换为相应类型后返回，因为invoke方法的返回类型为Object
            if (CtPrimitiveType.voidType != ctMethods[i].getReturnType()) {
                // 对8个基本类型进行转型
                // 例如：((Integer)this.h.invoke(this, this.m2, new Object[] { paramString, new Boolean(paramBoolean), paramObject })).intValue();
                if (ctMethods[i].getReturnType() instanceof CtPrimitiveType) {
                    CtPrimitiveType ctPrimitiveType = (CtPrimitiveType) ctMethods[i].getReturnType();
                    methodBody = "return ((" + ctPrimitiveType.getWrapperName() + ") " + methodBody + ")." + ctPrimitiveType.getGetMethodName() + "()";
                } else { // 对于非基本类型直接转型即可
                    methodBody = "return (" + ctMethods[i].getReturnType().getName() + ") " + methodBody;
                }
            }
            methodBody += ";";
            /* 为代理类添加方法. CtMethod(CtClass returnType, String methodName, CtClass[] parameterTypes, CtClass addToThisClass) */
            CtMethod newMethod = new CtMethod(ctMethods[i].getReturnType(), ctMethods[i].getName(),
                    ctMethods[i].getParameterTypes(), proxyCc);
            newMethod.setBody(methodBody);
            proxyCc.addMethod(newMethod);

            System.out.println("Invoke method: " + methodBody);
        }

        proxyCc.writeFile("D:/tmp");

        // 5.生成代理实例. 将入参InvocationHandler h设置到代理类的InvocationHandler h变量
        @SuppressWarnings("unchecked")
        Object proxy = proxyCc.toClass().getConstructor(InvocationHandler.class).newInstance(h);

        return proxy;
    }
}