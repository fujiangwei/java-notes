## Java Proxy和CGLIB动态代理原理   
```text
静态代理的代理关系在编译时就确定了，而动态代理的代理关系是在编译期确定的。静态代理实现简单，适合于代理类较少且确定的情况，而动态代理则给我们提供了更大的灵活性。
```
> Java中两种常见的动态代理方式：JDK原生动态代理和CGLIB动态代理。
* JDK原生动态代理

实现接口InvocationHandler，代理对象所有方法调用都会被转发到该类的invoke()方法。
      
    //newProxyInstance()方法返回一个实现指定接口的代理对象，该对象的所有方法调用都会转发到InvocationHandler.invoke()方法
    Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler handler)
    
* CGLIB动态代理
实现接口MethodInterceptor，代理对象所有非final方法调用都会被转发到该类的intercept()方法。

    
    //通过Enhancer来指定要代理的目标对象、实际处理代理逻辑的对象，最后通过调用create()方法得到代理对象   
    Enhancer enhancer = new Enhancer();
    //设置代理对象字节
    enhancer.setSuperclass(this.fruit.getClass());
    //设置回调方法
    enhancer.setCallback(this);
    //创建代理对象
    Object o = enhancer.create();    
    
## JDK原生动态代理和CGLIB动态代理区别

> java动态代理是领用反射机制生成一个代理接口匿名类，在调用具体的方法前调用InvocationHandler.invoke()方法来处理。CGLIB动态代理是对代理对象类的class文件加载进来，通过修改器字节码成成子类来处理。

> 两者使用：
* 如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP
* 如果目标对象实现了接口，可以强制使用CGLIB实现AOP
* 如果目标对象没有实现了接口，必须采用CGLIB库，spring会自动在JDK动态代理和CGLIB之间转换
    

