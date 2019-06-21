#  stream  

> 介绍

Java8 中的 Stream 是对集合（Collection）对象功能的增强，它专注于对集合对象进行各种非常便利、高效的聚合操作（aggregate operation），或者大批量数据操作 (bulk data operation)。Stream API 借助于同样新出现的 Lambda 表达式，极大的提高编程效率和程序可读性。
同时它提供串行和并行两种模式进行汇聚操作，并发模式能够充分利用多核处理器的优势，使用 fork/join 并行方式来拆分任务和加速处理过程。
Java 的集合 API 中，仅仅有极少量的辅助型方法，更多的时候是程序员需要用 Iterator 来遍历集合，完成相关的聚合应用逻辑。
聚合操作就类似SQL语句一样的操作， 比如filter, map, reduce, find, match, sorted等。
配合lambda表达式写法可以使代码写起来更加流畅简洁。

> 特点

Stream 不是集合元素，它不是数据结构并不保存数据，它是有关算法和计算的，它更像一个高级版本的 Iterator。
原始版本的 Iterator，用户只能显式地一个一个遍历元素并对其执行某些操作；
高级版本的 Stream，用户只要给出需要对其包含的元素执行什么操作，比如 “过滤掉长度大于 10 的字符串”、“获取每个字符串的首字母”等，Stream 会隐式地在内部进行遍历，做出相应的数据转换。
Stream 就如同一个迭代器（Iterator），单向，不可往复，数据只能遍历一次，遍历过一次后即用尽了。
和迭代器又不同的是，Stream 可以并行化操作，迭代器只能命令式地、串行化操作，Stream 的并行操作依赖于 Java7 中引入的 Fork/Join 框架（JSR166y）来拆分任务和加速处理过程。

> 流的操作类型

* 中间操作（intermediate ）
一个流可以后面跟随零个或多个 intermediate 操作。其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用。这类操作都是惰性化的（lazy），就是说，仅仅调用到这类方法，并没有真正开始流的遍历。

对应有map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 skip、 parallel、 sequential、 unordered等

* 终止操作（terminal ）
一个流只能有一个 terminal 操作，当这个操作执行后，流就被使用“光”了，无法再被操作，这是流的最后一个操作。
Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果或者一个错误。

对应有forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、iterator

* 短路操作（short-circuiting）
对于一个 intermediate 操作，如果它接受的是一个无限大（infinite/unbounded）的 Stream，但返回一个有限的新 Stream。
对于一个 terminal 操作，如果它接受的是一个无限大的 Stream，但能在有限的时间计算出结果。

对应有anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit

> 流的生成

流的来源可以是集合，数组，I/O channel， 产生器generator 等。
基本数值型，目前有三种对应的包装类型 Stream：IntStream、LongStream、DoubleStream。
可以用 Stream<Integer>、Stream<Long> >、Stream<Double>，但是 boxing 和 unboxing 会很耗时，故提供了对应的 Stream。

> 使用

见demo