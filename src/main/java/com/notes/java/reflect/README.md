## Reflect
> 反射概述
       
  Reflection（反射）是Java被视为动态语言的关键，反射机制允许程序在执行期借助于Reflection API取得任何类的內部信息，并能直接操作任意对象的内部属性及方法。
  
  Java反射机制主要提供了以下功能：
  
  * 在运行时构造任意一个类的对象
  * 在运行时获取任意一个类所具有的成员变量和方法
  * 在运行时调用任意一个对象的方法（属性）
  * 生成动态代理
   
  　　Class 是一个类; 一个描述类的类.
  　　封装了描述方法的 Method,描述字段的 Filed,描述构造器的 Constructor 等属性.

> Class获取

    //实际告诉我们任何一个类都有一个隐含的静态成员变量class（知道类名时用），不会触发初始化
    Class c1 = User.class;

    //已知该类的对象通过getClass方法（知道对象时用）
    Class c2 = user.getClass();  
    
    //会有一个ClassNotFoundException异常，会触发初始化
    Class c3 = Class.forName("类的全名");

> 根据Class创建实例

    //前提是必须要有无参的构造方法，因为该语句会去调用其无参构造方法。该语句会抛出异常。
    User user = (User)c1.newInstance();
    
> 