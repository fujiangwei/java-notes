package com.notes.domain;

/**
 * 文件描述
 **/
public class S extends F{
    public int age = 10;

//    static String qa = "QS";

    public static void main(String[] args) {
        S s = new S();
        F f = new F();
//        System.out.println(((F)s).age);
//        System.out.println(s.getFAge());
//        System.out.println(s.age);
//        System.out.println(s.getSAge());
//        System.out.println(s.getSAge2());

        // 如果子类自身包含父类中的普通变量如age，修改子类的中的覆盖的变量值不会修改父类的变量值，分别持有各自的变量。
        // 若果子类不包含父类中的静态变量值，则子类修改该静态变量值则会修改父类的静态变量值,如果包含则不会修改父类的静态变量
        System.out.println("S has own variable age, not tall");
        s.printQA(s);
        s.print("S");
        s.set(12, "S1", 180, "QS");
        s.printQA(s);
        s.print("S");
        f.print("F");
        s = new S(18, "S2", 190, "QS2");
        s.printQA(s);
        s.print("S");
        f.print("F");

    }

    public S() {}

    public S(int age2, String name2, int tall2) {
        age = age2;
        name = name2;
        tall = tall2;
    }

    public S(int age2, String name2, int tall2, String qa2) {
        this(age2, name2, tall2);
        qa = qa2;
    }

    public void set (int age2, String name2, int tall2) {
        age = age2;
        name = name2;
        tall = tall2;
    }

    public void set (int age2, String name2, int tall2, String qa2) {
        this.set(age2, name2, tall2);
        qa = qa2;
    }

    public int getSAge() {
        return this.age;
    }

    public int getSAge2() {
        return age;
    }

    public int getFAge() {
        return super.age;
    }

    public void printQA(S s) {
        System.out.println(qa + " " + S.qa + " " + qa);
    }
}