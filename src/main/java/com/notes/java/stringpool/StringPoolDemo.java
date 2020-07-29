package com.notes.java.stringpool;

/**
 * 文件描述 String常量池
 *  使用javac编译StringPoolDemo.java生成class文件，再用javap -c -l -v StringPoolDemo.class > a.log 生成汇编代码写入到a.log
 **/
public class StringPoolDemo {

    public static void main(String[] args) {
        String x1 = "a" + "b";

        x1.intern();
        String x2 = "ab";
        System.out.println(x1 == x2);

        String x3 = "cd";
        String x4 = "c" + "d";
        System.out.println(x3 == x4);

        String x5 = "c";
        String x6 = "d";
        String x7 = x5 + x6;
        System.out.println(x3 == x7);

//        test();
//        test2();
//        test3();
    }

    /**
     *  第一、str5.equals(str3)这个结果为true，不用太多的解释，因为字符串的值的内容相同。
     *  第二、str5 == str3对比的是引用的地址是否相同，由于str5采用new String方式定义的，所以地址引用一定不相等。所以结果为false。
     *  第三、当str5调用intern的时候，会检查字符串池中是否含有该字符串。由于之前定义的str3已经进入字符串池中，所以会得到相同的引用。
     */
    public static void test() {
        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = str1 + str2;
        String str5 = "ab";

        System.out.println(str5.equals(str3));
        System.out.println(str5 == str3);
        System.out.println(str5.intern() == str3);
        System.out.println(str5.intern() == str4);
    }

    /**
     * b.intern() == a和b.intern() == c可知，采用new 创建的字符串对象不进入字符串池，并且
     * 通过b.intern() == d和b.intern() == f可知，字符串相加的时候，都是静态字符串的结果会添加到字符串池，如果其中含有变量（如f中的e）则不会进入字符串池中。
     * 但是字符串一旦进入字符串池中，就会先查找池中有无此对象。如果有此对象，则让对象引用指向此对象。如果无此对象，则先创建此对象，再让对象引用指向此对象。
     */
    public static void test2() {
        String a = "ab";
        String b = "ab";
        String c = "ab";
        String d = "a" + "b";
        String e = "b";
        String f = "a" + e;

        System.out.println(b.intern() == a);
        System.out.println(b.intern() == c);
        System.out.println(b.intern() == d);
        System.out.println(b.intern() == f);
        System.out.println(b.intern() == a.intern());
    }

    /**
     * equal和==的区别，如果赋值的是静态的字符串，就会执行进入字符串池的操作，如果池中含有该字符串，则返回引用。
     */
    public static void test3() {
        String a = "abc";
        String b = "abc";
        String c = "a" + "b" + "c";
        String d = "a" + "bc";
        String e = "ab" + "c";

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a == d);
        System.out.println(a == e);
        System.out.println(c == d);
        System.out.println(c == e);
    }
}