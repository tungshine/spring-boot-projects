package com.tungshine.mongo.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: TungShine
 * @Description:
 * @Date: Create in 1:10 2018/7/19
 * @Modified By:
 */
@Getter
@Setter
public class User implements Serializable {

    private static final long serialVersionUID = 5754518979671349912L;

    private Integer id;
    private String name;
    private int age;

    public User() {

    }

    public User(Integer id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "User [id=" + getId() + ", name=" + getName() + ", age=" + getAge() + "]";
    }
}
