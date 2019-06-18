##  lambda  

> lambda表达式是java8发布的一个非常重要的新特性，我们可以把它当做是一种闭包。它允许把函数作为一个方法的参数。使用lambda表达式可以使代码看起来更加简洁。
> lambda表达式语法

    (paramters) -> expression
    或者
    (paramters) -> {statements;}  
    具体如：
    (Type1 param1, Type2 param2, Type2 param2, ...) -> {
        statement1;
        statement2;
        statement3;
        ...
        return statementX;
    }   
> lambda表达式特征

* 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
* 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
* 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
* 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。    
 
> 方法引用

* objectName::instanceMethod
* ClassName::staticMethod
* ClassName::instanceMethod

> 构造函数

ClassName::new