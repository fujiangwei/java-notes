package com.notes.java.guava;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.notes.domain.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.*;

/**
 * 文件描述 guava API测试
 **/
public class GuavaTest {

    /**
     * 连接器
     */
    private static final Joiner joiner = Joiner.on(",")
            // 排除null值
            .skipNulls();

    /**
     * 连接器
     */
    private static final Joiner joiner2 = Joiner.on(",")
            // 替换null为""
            .useForNull("");

    /**
     * 分割器
     */
    private static final Splitter splitter = Splitter.on(",")
            // 去除前后空格
            .trimResults()
            // 从结果中自动忽略空字符串
            .omitEmptyStrings();

    public static void main(String[] args) throws Exception {
        // 新建List 指定起始元素
//        List<String> strList = Lists.newArrayList("a", null, "b");
//        List<String> strings = Lists.newArrayList(strList);
//        // 初始化
//        List<String> ss = new ArrayList<String>(){{
//            add("a");add("b");
//        }};
//        ArrayList<String> strings1 = new ArrayList<String>(strList);
//
//        String join = String.join(",", strList);
//        System.out.println("String.join is " + join);
//        String join1 = joiner.join(strList);
//        System.out.println("Joiner.join is " + join1);
//
//        String splitStr = "  a,b,c, ,d,,e,";
//        // 未排除空字符串,丢弃了尾部的分隔符
//        String[] split = splitStr.split(",");
//        System.out.println("String.split is " + Lists.newArrayList(split));
//        Iterable<String> split1 = splitter.split(splitStr);
//        System.out.println("Splitter.split is " + Lists.newArrayList(split1));
//
//        // 新建Map/Set
//        Map<String, String> map = Maps.newHashMap();
//        Set<String> set = Sets.newHashSet();
//
//        // 字符集
//        splitStr.getBytes("UTF-8");
//        // 替换 Charsets针对所有Java平台支持的六种字符集提供了常量引用
//        splitStr.getBytes(Charsets.UTF_8);
//
//        User user = null;
//        if (null == user) {
//            System.out.println("xxx不能null");
//        }
////        Assert.notNull(user, "xxx不能null");
//
//        CollectionUtils.isNotEmpty(strList);
//        CollectionUtils.isEmpty(strList);
//
//        Assert.isTrue(StringUtils.isNotEmpty(""), "xxx不能为空");

        List<String> aList = Lists.newArrayList("1", "ae", "ae", "ae", "b", "c", "c", "d");
        List<String> bList = Lists.newArrayList("1", "c", "2", "3", "1", "ae", "ae");

        // 两个个集合合并之后会显示元素个数是两个集合中的该元素的个数最多的。
        Collection<String> unionList = CollectionUtils.union(aList, bList);
        System.out.println("============ CollectionUtils.union ==============");
        System.out.println(unionList.toString());

        // 两个集合的交集
        Collection<String> intersectionList = CollectionUtils.intersection(aList, bList);
        System.out.println("============ CollectionUtils.intersection ==============");
        System.out.println(intersectionList.toString());

        // 两个集合补集
        Collection<String> disjunctionList  = CollectionUtils.disjunction(aList, bList);
        System.out.println("============ CollectionUtils.disjunction ==============");
        System.out.println(disjunctionList.toString());

        // 两个集合相减
        Collection<String> subtractList  = CollectionUtils.subtract(aList, bList);
        System.out.println("============ CollectionUtils.subtract ==============");
        System.out.println(subtractList.toString());

        // 判断两个集合是否存在交集 * return:如果两个指定 collection 中有相同的元素，则返回 true。
        boolean isContainsAny = CollectionUtils.containsAny(aList, bList);
        System.out.println("============ CollectionUtils.containsAny ==============");
        System.out.println("CollectionUtils.containsAny : " + isContainsAny);
        //return:如果两个指定 collection 中有相同的元素，则返回 true。
        boolean disjoint = Collections.disjoint(aList, bList);
        System.out.println("Collections.disjoint : " + disjoint);

        // 第一个集合中的所有与第二个集合相同的元素
        List<String> retainAll = (List<String>) CollectionUtils.retainAll(bList, aList);
        System.out.println("============ CollectionUtils.retainAll ==============");
        System.out.println(retainAll);
    }

}
