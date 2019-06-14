##  FunctionalInterface

> Function<T, R>
接受一个入参T，返回R类型对象，使用apply方法获取方法执行的内容

    R apply(T t);
> Consumer<T>
接受一个参数T，没有返回值，使用accept方法对参数执行操作

    void accept(T t);
> Supplier<T>
没有入参，返回T类型结果，使用get方法获取返回结果

    T get();
> Predicate<T>
接受一个入参，返回结果为true或者false,使用test方法进行测试并返回测试结果

    boolean test(T t);