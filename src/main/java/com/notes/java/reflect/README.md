## Reflect

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