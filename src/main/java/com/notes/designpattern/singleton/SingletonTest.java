package com.notes.designpattern.singleton;

/**
 * @author kingson
 * @Description: 文件描述
 **/
public class SingletonTest {

    public static void main(String[] args) {
        LazySingleton instance = LazySingleton.getInstance();
        LazySingleton instance1 = LazySingleton.getInstance();
        System.out.println("LazySingleton:" + (instance == instance1));

        LazySafeSingleton instance2 = LazySafeSingleton.getInstance();
        LazySafeSingleton instance3 = LazySafeSingleton.getInstance();
        System.out.println("LazySafeSingleton:" + (instance2 == instance3));

        HungerSingleton instance4 = HungerSingleton.getInstance();
        HungerSingleton instance5 = HungerSingleton.getInstance();
        System.out.println("HungerSingleton:" + (instance4 == instance5));

        EnumSingle instance6 = EnumSingle.getInstance();
        EnumSingle instance7 = EnumSingle.getInstance();
        System.out.println("EnumSingle:" + (instance6 == instance7));

        StaticInnerSingleton instance8 = StaticInnerSingleton.getInstance();
        StaticInnerSingleton instance9 = StaticInnerSingleton.getInstance();
        System.out.println("StaticInnerSingleton:" + (instance8 == instance9));

        DoubleLockSingleton instance10 = DoubleLockSingleton.getInstance();
        DoubleLockSingleton instance11 = DoubleLockSingleton.getInstance();
        System.out.println("DoubleLockSingleton:" + (instance10 == instance11));
    }
}