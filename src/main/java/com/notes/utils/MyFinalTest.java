package com.notes.utils;

/**
 * @Description: 文件描述
 * @ProductName: Hundsun IASP
 * @ProjectName: java-notes
 * @Package: com.notes.utils
 * @Author: fujw27320
 * @Date: 2020/12/9 12:09
 * @UpdateUser: fujw27320
 * @UpdateDate: 2020/12/9 12:09
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2020 Hundsun Technologies Inc. All Rights Reserved
 **/
public class MyFinalTest {

    public static void main(String[] args) {
//        String testReturn = testReturn();
//        System.out.println("main testReturn :" + testReturn);
        String testReturnE = testReturnE();
        System.out.println("main testReturnE :" + testReturnE);

//        int testReturnEI = testReturnEI();
//        System.out.println("main testReturnEI :" + testReturnEI);
    }

    public static String testReturn() {
        try {
            System.out.println("normal");
            return "normal";
        } catch (Exception e) {
            System.out.println("exception");
            return "exception";
        } finally {
            System.out.println("finally");
            // 存在finally语句块的时候，
            //
            //   finally语句是一定会执行，但他们的执行顺序是怎么样的呢？他们的执行顺序如下：
            //    1、执行：expression，计算该表达式，结果保存在操作数栈顶；
            //    2、执行：操作数栈顶值（expression的结果）复制到局部变量区作为返回值；
            //    3、执行：finally语句块中的代码；
            //    4、执行：将第2步复制到局部变量区的返回值又复制回操作数栈顶；
            //    5、执行：return指令，返回操作数栈顶的值；

            // 如果在finally中使用return的话则是会将新的操作数栈的顶端数据返回，而不是之前复制到局部变量区用作返回内容快照的值返回
            return "finally";
        }
    }

    public static String testReturnE() {
        String ret = "testE";
        try {
            System.out.println("testReturnE normal");
            int i = 1 / 0;
            return ret;
        } catch (Exception e) {
            System.out.println("testReturnE exception");
            ret += " Ex";
            return ret;
        } finally {
            System.out.println("testReturnE finally");
            ret += " Finally";
            return ret;
        }
    }

    public static int testReturnEI() {
        int x = 1;
        int y = 3;
        int sum = 0;
        try {
//            int i = 1 / 0;
            return x + y;
        } catch (Exception e) {
            return -1;
        } finally {

            return x;
        }
    }
}