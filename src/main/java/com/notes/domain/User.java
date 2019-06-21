package com.notes.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 文件描述
 **/
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;

    private List<String> interests;

    public User(){}

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(Integer id, String name, List<String> interests) {
        this.id = id;
        this.name = name;
        this.interests = interests;
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

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }
}
