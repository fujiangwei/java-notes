package com.notes.java.guava;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.notes.domain.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        List<String> strList = Lists.newArrayList("a", null, "b");
        List<String> strings = Lists.newArrayList(strList);
        // 初始化
        List<String> ss = new ArrayList<String>(){{
            add("a");add("b");
        }};
        ArrayList<String> strings1 = new ArrayList<String>(strList);

        String join = String.join(",", strList);
        System.out.println("String.join is " + join);
        String join1 = joiner.join(strList);
        System.out.println("Joiner.join is " + join1);

        String splitStr = "  a,b,c, ,d,,e,";
        // 未排除空字符串,丢弃了尾部的分隔符
        String[] split = splitStr.split(",");
        System.out.println("String.split is " + Lists.newArrayList(split));
        Iterable<String> split1 = splitter.split(splitStr);
        System.out.println("Splitter.split is " + Lists.newArrayList(split1));

        // 新建Map/Set
        Map<String, String> map = Maps.newHashMap();
        Set<String> set = Sets.newHashSet();

        // 字符集
        splitStr.getBytes("UTF-8");
        // 替换 Charsets针对所有Java平台支持的六种字符集提供了常量引用
        splitStr.getBytes(Charsets.UTF_8);

        User user = null;
        if (null == user) {
            System.out.println("xxx不能null");
        }
//        Assert.notNull(user, "xxx不能null");

        Assert.isTrue(StringUtils.isNotEmpty(""), "xxx不能为空");
    }

}
