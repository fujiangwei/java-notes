## Map

> 各种Map对比

* HashMap
    HashMap是常规的哈希表，查询以及插入的性能最好，如果没有特殊要求，应该使用这个
* LinkedHashMap
   LinkedHashMap可以指定遍历的顺序：
   1、元素添加的顺序    
   2、按最近最少使用的顺序
这个类的实现上继承了HashMap，只是比HashMap多维护了一个双链表。因此，它的查询及插入性能稍差于HashMap。但是它的遍历性能要好于HashMap。
如果想要按照元素添加顺序遍历，那么使用LinkedHashMap。
* TreeMap
TreeMap这个类的内部实现类似于C++的std::map
TreeMap将元素存储在一个红黑树中，存储在它里面的Key必须实现Comparable接口。
由于使用了红黑树的原因，存储在它里面的key是从小到大排好序的。它的各项性能指标都要比HashMap差。
如果想要Map中的元素是有序的，那么使用TreeMap。

> 同步 Map
  
  * 使用 Collections.synchronizedMap() 将未同步的 Map 转换为同步的 Map。
  
     
     HashMap<Integer,String> hashMap = new HashMap<>();
     Map<Integer,String> synchronizedMap = Collections.synchronizedMap(hashMap);

  
  * ConcurrentReaderHashMap 和 ConcurrentHashMap。
  
```
这些 Map 实现是线程安全的，并且不需要对并发访问或更新进行同步，同时还适用于大多数需要 Map 的情况。它们还远比同步的 Map（如 Hashtable）或使用同步的包装器更具伸缩性，并且与 HashMap 相比，它们对性能的破坏很小。util.concurrent 程序包构成了 JSR166 的基础；JSR166 已经开发了一个包含在 Java 1.5 版中的并发实用程序，而 Java 1.5 版将把这些 Map 包含在一个新的 java.util.concurrent 程序包中。
 
 ConcurrentHashMap<Integer,String> concurrentHashMap = new ConcurrentHashMap<>();
```

> Map遍历

见示例com.notes.java.map.MapTest
