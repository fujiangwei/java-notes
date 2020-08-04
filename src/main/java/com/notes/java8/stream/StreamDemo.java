package com.notes.java8.stream;

import com.google.common.collect.Lists;
import com.notes.domain.User;
import com.notes.utils.UserUtil;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件描述 Stream 流使用demo
 **/
public class StreamDemo {
    public static void main(String[] args) {
//        /*************流的来源*************/
        // 1、of方法
        //      of(T... values)：返回含有多个T元素的Stream
        //      of(T t)：返回含有一个T元素的Stream
        Stream<String> single = Stream.of("a");
        Stream<String> multiple = Stream.of("a", "b", "c");

        // 2、generator方法，返回一个无限长度的Stream,其元素由Supplier接口的提供。
        Stream<String> generate = Stream.generate(() -> "a");
        Stream<Double> generateA = Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return java.lang.Math.random();
            }
        });
        //  lambda写法
        Stream<Double> generateB = Stream.generate(() -> java.lang.Math.random());
        Stream<Double> generateC = Stream.generate(java.lang.Math::random);

        // 3、iterate方法，返回的也是一个无限长度的Stream，与generate方法不同的是，其是通过函数f迭代对给指定的元素种子而产生无限连续有序Stream，其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环
        Stream<Integer> iterate = Stream.iterate(1, item -> item + 2);
        // 无限流处理
        iterate.limit(10).forEach(System.out::println);

        // 4、empty方法返回一个空的顺序Stream，该Stream里面不包含元素项。
        Stream<Object> empty = Stream.empty();

        // 5、Collection接口和数组的默认方法
        String[] chars = new String[]{"a", "b", "c"};
        Arrays.stream(chars).forEach(System.out::println);

        List list = Arrays.asList(chars);
        list.stream().forEach(System.out::println);

        /*************流的操作*************/
        //concat 将两个Stream连接在一起，合成一个Stream。若两个输入的Stream都时排序的，则新Stream也是排序的；若输入的Stream中任何一个是并行的，则新的Stream也是并行的；若关闭新的Stream时，原两个输入的Stream都将执行关闭处理。
        Stream.concat(Stream.of(1, 2, 3), Stream.of(4, 5))
                .forEach(integer -> System.out.print(integer + "  "));

        //distinct 去除掉原Stream中重复的元素，生成的新Stream中没有没有重复的元素。
        Stream.of(1, 1, 3, 4, 3).distinct().forEach(System.out::println);

        //filter 对原Stream按照指定条件过滤，过滤出满足条件的元素。
        Stream.of(1, 1, 3, 4, 3).filter(x -> x > 2).forEach(System.out::println);

        //map方法将对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。
        //为了提高处理效率，官方已封装好了，三种变形：mapToDouble，mapToInt，mapToLong，将原Stream中的数据类型，转换为double,int或者long类型。
        Stream.of("a", "b", "c")
                .map(item -> item.toUpperCase()) // .map(String::toUpperCase)
                .forEach(System.out::println);

        // flatMap方法与map方法类似，都是将原Stream中的每一个元素通过转换函数转换，
        // 不同的是，该换转函数的对象是一个Stream，也不会再创建一个新的Stream，而是将原Stream的元素取代为转换的Stream。
        List<User> users = UserUtil.getUsers(6);
        users.stream()
                .flatMap(s -> s.getInterests().stream())
                .forEach(System.out::println);

        Stream.of("a", "b", "c").flatMap(s -> Stream.of(s.toUpperCase()));

        //peek 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例)
        Stream.of("a", "b", "c")
                //优先执行
                .peek(s -> System.out.println("peek:" + s))
                .forEach(System.out::println);

        //skip 将过滤掉原Stream中的前N个元素，返回剩下的元素所组成的新Stream。
        // 如果原Stream的元素个数大于N，将返回原Stream的后的元素所组成的新Stream；
        // 如果原Stream的元素个数小于或等于N，将返回一个空Stream。
        Stream.of("a", "b", "c").skip(2)
                .forEach(System.out::println);

        // sorted方法将对原Stream进行排序，返回一个有序列的新Stream。sorterd有两种变体sorted()，sorted(Comparator)，
        // 前者将默认使用Object.equals(Object)进行排序，而后者接受一个自定义排序规则函数(Comparator)，可自定义进行排序。
        Stream.of(5, 6, 3, 9, 1)
                .sorted()
                .forEach(System.out::println);

        System.out.println("+++++++++++++++++++++++++++++++");
        Stream.of(5, 6, 3, 9, 1)
                .sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        //asc
                        return o1 - o2;
                    }
                })
                .forEach(System.out::println);

        Stream.of(5, 6, 3, 9, 1)
                //desc
                .sorted(((o1, o2) -> o2 - o1))
                .forEach(System.out::println);
        System.out.println("+++++++++++++++++++++++++++++++");


        // count 将返回Stream中元素的个数。
        long count = Stream.of(1, 2, 3, 4, 5).count();
        System.out.println("count:" + count);

        // forEach 用于遍历Stream中的所元素，避免了使用for循环，让代码更简洁，逻辑更清晰。
        Stream.of("a", "b", "c").forEach(System.out::println);

        // forEachOrdered 与forEach类似，都是遍历Stream中的所有元素，
        // 不同的是，如果该Stream预先设定了顺序，会按照预先设定的顺序执行（Stream是无序的），默认为元素插入的顺序。
        Stream.of(5, 2, 1, 4, 3)
                .forEachOrdered(integer ->
                        System.out.println("integer:" + integer)
                );

        // max 根据指定的Comparator，返回一个Optional，该Optional中的value值就是Stream中最大的元素。
        Optional<Integer> max = Stream.of(5, 2, 2, 3, 4, 8)
                .max((o1, o2) -> o2 - o1);
//        Optional<Integer> max3 = Stream.of(1, 2, 3, 4, 5)
//                .max(Comparator.comparingInt(x -> x));
        Optional<Integer> max3 = Stream.of(1, 2, 3, 4, 5)
                .max((o1, o2) -> o1 - o2);
        int max2 = Stream.of(1, 2, 3, 4, 5)
                .mapToInt(x -> x).max().getAsInt();
        System.out.println("max = " + max.get() + "  max2 = " + max2 + "  max3 = " + max3.orElse(-1));

        UserUtil.getUsers(6).stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getId))
                .forEach(u -> System.out.println(u.getName()));

        // min 根据指定的Comparator，返回一个Optional，该Optional中的value值就是Stream中最小的元素。
        Optional<Integer> min = Stream.of(1, 2, 3, 4, 5)
                .min((o1, o2) -> o1 - o2);
        System.out.println("min:" + min.get());

        System.out.println("*********************reduce********************");
        // reduce
        //  1、reduce((sum, item) -> { ... }); //返回Optional，因为可能存在为空的情况，
        //  2、reduce(0, (sum, item) -> { ... }); /返回对应类型，不存在为空的情况
        //无初始值，第一个参数为stream的第一个元素，第二个参数为stream的第二个元素，计算的结果赋值给下一轮计算的sum
        Optional<Integer> optional = Stream.of(1, 2, 3, 4, 5).reduce((sum, item) -> {
            System.out.println("sum before:" + sum);
            System.out.println("item:" + item);
            sum = sum + item;
            System.out.println("sum after:" + sum);

            return sum;

//            return Integer.sum(sum, item);
        });
        //等效
        Optional<Integer> optional1 = Stream.of(1, 2, 3, 4, 5).reduce((sum, item) ->
                Integer.sum(sum, item)
        );
        //等效
        Optional<Integer> optional2 = Stream.of(1, 2, 3, 4, 5).reduce(Integer::sum);
        System.out.println("integer = " + optional.orElse(-1));
        System.out.println("*****************************************");
        //给定初始值，第一个参数为初始值，第二个参数为stream的第一个元素，计算的结果赋值给下一轮计算的sum
        Integer reduce = Stream.of(1, 2, 3, 4, 5).reduce(5, (sum, item) -> {
            System.out.println("sum2 before:" + sum);
            System.out.println("item:" + item);
            sum = sum + item;
            System.out.println("sum2 after:" + sum);
            return sum;
        });
        //等效
        Integer reduce2 = Stream.of(1, 2, 3, 4, 5).reduce(0, (sum, item) ->
                Integer.sum(sum, item)
        );
        //等效
        Integer reduce3 = Stream.of(1, 2, 3, 4, 5).reduce(0, Integer::sum);
        System.out.println("reduce = " + reduce);


        System.out.println("*********************collect********************");
        List<Integer> toList = Stream.of(1, 2, 3, 4)
                .collect(Collectors.toList());
        List<Integer> toList2 = Stream.of(1, 2, 3, 4)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("toList: " + toList);

        Set<Integer> toSet = Stream.of(1, 2, 3, 4)
                .collect(Collectors.toSet());
        Set<Integer> toSet2 = Stream.of(1, 2, 3, 4)
                .collect(Collectors.toCollection(() -> new TreeSet()));
        System.out.println("toSet: " + toSet);

        //(value1, value2) -> value1  用前面的value覆盖后面的value，保持不变
        List<User> userList = UserUtil.getUsers(5);
        userList.add(new User(2, "fjw"));
        Map<Integer, String> toMap = userList.stream()
                .collect(Collectors.toMap(User::getId, User::getName, (value1, value2) -> value1));
        System.out.println("(value1, value2) -> value1");
        toMap.forEach((k, v) -> System.out.println(k + "-" + v));

        // 对value值进行了限定不能为null，否则抛出空指针异常
//        userList.add(new User(3, null));
        //(value1, value2) -> value2  用后面的value覆盖前面的value
        Map<Integer, String> toMap2 = userList.stream()
                .collect(Collectors.toMap(User::getId, User::getName, (value1, value2) -> value2));
        System.out.println("(value1, value2) -> value2");
        toMap2.forEach((k, v) -> System.out.println(k + "-" + v));

        // 解决value值为null方式
        userList.add(new User(4, null));
        Map<Integer, String> toMap3 = userList.stream()
                .collect(HashMap::new, (m, u) -> m.put(u.getId(), u.getName()), HashMap::putAll);
        toMap3.forEach((k, v) -> System.out.println(k + "-" + v));

        HashMap<Integer, User> collect4 = userList.stream()
                .collect(HashMap::new, (m, u) -> m.put(u.getId(), u), HashMap::putAll);
        toMap3.forEach((k, v) -> System.out.println(k + "-" + v));

        Optional<Integer> maxBy = Stream.of(1, 2, 3, 4)
                .collect(Collectors.maxBy(Comparator.comparingInt(o -> o)));
        System.out.println("maxBy:" + maxBy.get());

        Long counting = Stream.of(1, 2, 3, 4)
                .collect(Collectors.counting());
        System.out.println("counting:" + counting);

        //分割数据块
        Map<Boolean, List<Integer>> partitioningBy = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.partitioningBy(item -> item > 3));
        //partitioningBy : {false=[1, 2, 3], true=[4, 5]}
        System.out.println("partitioningBy : " + partitioningBy);

        Map<Boolean, Long> collect = Stream.of(1, 2, 3, 4)
                .collect(Collectors.partitioningBy(item -> item > 3, Collectors.counting()));
        System.out.println("collect: " + collect);

        //数据分组
        Map<Boolean, List<Integer>> groupingBy = Stream.of(1, 2, 3, 4, 5)
                .collect(Collectors.groupingBy(item -> item > 3));
        //partitioningBy : {false=[1, 2, 3], true=[4, 5]}
        System.out.println("groupingBy : " + groupingBy);

        //字符串
        String joining = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining(","));
        System.out.println(joining);

        String joining2 = Stream.of("a", "b", "c", "d")
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(joining2);

        System.out.println(userList);
        User uu = new User(1, "kk", Lists.newArrayList("i66"));
        userList.add(uu);

        // 根据id分组
        Map<Integer, List<User>> collect3 = userList.stream().collect(Collectors.groupingBy(User::getId));
        // 将id映射为id集合
        List<Integer> collect1 = userList.stream().collect(Collectors.mapping(User::getId, Collectors.toList()));
        // 根据id分组后将interests映射成集合
        Map<Integer, List<List<String>>> collect2 = userList.stream()
                .collect(Collectors.groupingBy(User::getId, Collectors.mapping(User::getInterests, Collectors.toList())));
        System.out.println(collect3);
        System.out.println(collect1);
        System.out.println(collect2);
    }
}
