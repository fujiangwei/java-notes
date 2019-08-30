# javassist

## 
```text
Javassist中最为重要的是ClassPool，CtClass ，CtMethod 以及 CtField这几个类。
ClassPool：一个基于HashMap实现的CtClass对象容器，其中键是类名称，值是表示该类的CtClass对象。默认的ClassPool使用与底层JVM相同的类路径，因此在某些情况下，可能需要向ClassPool添加类路径或类字节。

CtClass： (compile-time class) 表示一个类，这些CtClass对象可以从ClassPool获得。

CtMethods：表示类中的方法。

CtFields ：表示类中的字段。
```

## Javassist实现JDK动态代理
   前面简单分析了JDK动态代理的基本原理，其中，最核心的逻辑在于如何生成动态代理类，也就是
   java.lang.reflect.Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h)方法的实现。
   接下来我们将通过Javassist一步步实现newProxyInstance方法。
   1. 定义接口
   接口基本与Proxy.newProxyInstance相同。为简单说明，我们这里只定义了一个接口类型参数Class<?>而不是数组。
   public static Object newProxyInstance(ClassLoader loader, Class<?> interfaceClass, InvocationHandler h) {
       ...
   }
   复制代码2. 创建动态代理类
   Javassist可以通过简单的Java API来操作源代码，这样就可以在不了解Java字节码相关知识的情况下，动态生成类或修改类的行为。
   创建名称为NewProxyClass的代理类。
   ClassPool pool = ClassPool.getDefault();
   CtClass proxyCc = pool.makeClass("NewProxyClass");
   复制代码3. 添加实例变量InvocationHandler
   添加类型为InvocationHandler的实例变量h。
   CtClass handlerCc = pool.get(InvocationHandler.class.getName());
   /* 生成代码：private InvocationHandler h; */
   CtField handlerField = new CtField(handlerCc, "h", proxyCc);
   handlerField.setModifiers(AccessFlag.PRIVATE);
   proxyCc.addField(handlerField);
   复制代码4. 添加构造函数
   创建构造函数，参数类型为InvocationHandler。
   // 生成构造函数：public NewProxyClass(InvocationHandler h) { this.h = h; }
   CtConstructor ctConstructor = new CtConstructor(new CtClass[] { handlerCc }, proxyCc);
   ctConstructor.setBody("$0.h = $1;");
   proxyCc.addConstructor(ctConstructor);
   复制代码其中，$0代表this, $1代表构造函数的第1个参数。
   5. 实现IHello接口声明
   // 生成接口实现声明：public class NewProxyClass implements IHello
   CtClass interfaceCc = pool.get(interfaceClass.getName());
   proxyCc.addInterface(interfaceCc);
   复制代码6. 实现IHello相关接口方法
   6.1 遍历接口方法
   CtMethod[] ctMethods = interfaceCc.getDeclaredMethods();
   for (int i = 0; i < ctMethods.length; i++) {
   	// 核心逻辑在下方
   }
   复制代码6.2 代理方法实现
   由于代理类调用invoke方法需要传入接口的反射方法对象（Method），因此，我们需要为每个方法添加一个可复用的Method类变量。
   6.2.1 反射方法对象声明及初始化
   /* 构造方法参数，如：new Class[] { String.class, Boolean.TYPE, Object.class } */
   String classParamsStr = "new Class[0]";
   if (ctMethods[i].getParameterTypes().length > 0) {
   	for (CtClass clazz : ctMethods[i].getParameterTypes()) {
   		classParamsStr = ((classParamsStr == "new Class[0]") ? clazz.getName() : classParamsStr + "," + clazz.getName()) + ".class";
   	}
   	classParamsStr = "new Class[] {" + classParamsStr + "}";
   }
   // 字段生成模板
   String methodFieldTpl = "private static java.lang.reflect.Method %s=Class.forName(\"%s\").getDeclaredMethod(\"%s\", %s);";
   // 根据模板生成方法及参数构造方法字段生成语句
   String methodFieldBody = String.format(methodFieldTpl, "m" + i, interfaceClass.getName(), ctMethods[i].getName(), classParamsStr);
   // 为代理类添加反射方法字段
   CtField methodField = CtField.make(methodFieldBody, proxyCc);
   proxyCc.addField(methodField);
   复制代码通过以上逻辑，将生成类似代码如下：
   private static Method m0 = Class.forName("chapter9.javassistproxy3.IHello").getDeclaredMethod("sayHello", new Class[0]);
   private static Method m1 = Class.forName("chapter9.javassistproxy3.IHello").getDeclaredMethod("sayHello2", new Class[] { Integer.TYPE });
   private static Method m2 = Class.forName("chapter9.javassistproxy3.IHello").getDeclaredMethod("sayHello3", new Class[] { String.class, Boolean.TYPE, Object.class });
   复制代码6.2.2 接口方法体实现
   // invoke调用逻辑. 其中$args是实际方法传入的参数数组
   String methodBody = "$0.h.invoke($0, " + methodFieldName + ", $args)";
   
   // 如果方法有返回类型，则需要转换为相应类型后返回
   if (CtPrimitiveType.voidType != ctMethods[i].getReturnType()) {
   	// 对8个基本类型进行转型
   	// 例如：((Integer)this.h.invoke(this, this.m2, new Object[] { paramString, new Boolean(paramBoolean), paramObject })).intValue();
   	if (ctMethods[i].getReturnType() instanceof CtPrimitiveType) {
   		CtPrimitiveType ctPrimitiveType = (CtPrimitiveType) ctMethods[i].getReturnType();
   		methodBody = "return ((" + ctPrimitiveType.getWrapperName() + ") " + methodBody + ")." + ctPrimitiveType.getGetMethodName() + "()";
   	}
   	// 对于非基本类型直接转型即可
   	else {
   		methodBody = "return (" + ctMethods[i].getReturnType().getName() + ") " + methodBody;
   	}
   }
   methodBody += ";";
   
   /* 为代理类添加方法 */
   CtMethod newMethod = new CtMethod(ctMethods[i].getReturnType(), ctMethods[i].getName(),
   		ctMethods[i].getParameterTypes(), proxyCc);
   newMethod.setBody(methodBody);
   proxyCc.addMethod(newMethod);
   复制代码通过以上逻辑，将生成类似代码如下：
   public void sayHello() {
       this.h.invoke(this, m0, new Object[0]);
   }
   
   public String sayHello2(int paramInt) {
       return (String)this.h.invoke(this, m1, new Object[] { new Integer(paramInt) });
   }
   
   public int sayHello3(String paramString, boolean paramBoolean, Object paramObject) {
       return ((Integer)this.h.invoke(this, m2, new Object[] { paramString, new Boolean(paramBoolean), paramObject })).intValue();
   }
   复制代码7. 生成代理类字节码
   以下语句，将生成代理类字节码：D:/tmp/NewProxyClass.class
   proxyCc.writeFile("D:/tmp"); // 该步骤可选
   复制代码8. 生成代理对象
   最后，通过调用第3步创建的构造函数，传入InvocationHandler对象，生成并返回代理类。
   Object proxy = proxyCc.toClass().getConstructor(InvocationHandler.class).newInstance(h);
   return proxy;