##  Optional

> java8 退出Optional的目的就是为了杜绝空指针异常，所以在使用Optional不正当时，将会违背设计者的初衷。

1、Optional的构造方式
* Optional.of(T) 入参对象一定不能为null,否则会NPE,在明确入参为非null时使用
* Optional.ofNullable(T) 入参对象可以为null
* Optional.empty() 返回空Optional，等效Optional.ofNullable(null)

2、 在代码中应尽量避免如下之类的使用：
* 使用Optional.isPresent() 
使用该方式和对象判空（null != obj）没有区别,与Optional的设计理念不符
* 使用Optional.get()
使用该方法需要先调用Optional.isPresent()来检查实例是否可用，不可用时会NPE
* 使用Optional作为类或者实例属性
Optional尽量用来包装返回的对象，不建议作为类或者实例属性
* 使用Optional作为方法参数
同上

3、 正确使用Optional方式
* 有值则直接返回
* 没有则提供默认值或者通过方法获取
* 存在使用进行相关的操作
* 使用map方法获取关联数据
* 使用flatMap方法获取关联数据
