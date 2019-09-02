package com.notes.java.generic;

import com.notes.java.generic.ginterface.GenericStudy;
import com.notes.java.generic.ginterface.JavaStudy;

/**
 * descripiton:
 *
 * @author: kinson(2219945910 @ qq.com)
 * @date: 2019/3/20
 * @time: 18:15
 * @modifier:
 * @since:
 */
public class GenericTest {
    public static void main(String[] args) {
        GenericClass<Integer> genericClass = new GenericClass<Integer>(100);
        GenericClass<String> genericClass2 = new GenericClass<String>("kinson");

//        System.out.println(genericClass.getVar());
//        System.out.println(genericClass2.getVar());

        GenericStudy<String> stringGenericStudy = new GenericStudy<>();
        JavaStudy javaStudy = new JavaStudy();
//        System.out.println(stringGenericStudy.study());
//        System.out.println(javaStudy.study());

        GenericClass<String> genericClass3 = new GenericClass<String>("method");
        Integer show = genericClass3.show(1000);
        System.out.println(show);
        String print = GenericClass.print(genericClass3);
        System.out.println(print);
    }
}
