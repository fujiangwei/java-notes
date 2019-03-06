package com.notes.java.reflect.domain;

import java.io.Serializable;

/**
 * descripiton:
 *
 * @author: kinson(2219945910@qq.com)
 * @date: 2019/3/6
 * @time: 20:36
 * @modifier:
 * @since:
 */
public class User implements Serializable {

    private final static Long serialVersionUID = 5088697673359856350L;

    private Integer id;

    private String name;

    static String firstName = "Json";

    public String city;

    static {
//        System.out.println("静态代码块" + firstName);
    }

    public User() {

    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void print() {
        System.out.println("===========" + this.id + "-" + this.name);
    }

    private void say(String say) {
        System.out.println("say>>>>" + say);
    }

    protected void hello() {
        System.out.println("===========hello, kinson");
    }

    void hi() {
        System.out.println("hi, kinson");
    }
}
