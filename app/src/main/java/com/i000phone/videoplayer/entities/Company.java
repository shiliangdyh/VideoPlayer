package com.i000phone.videoplayer.entities;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/2.
 */
public class Company implements Serializable {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
