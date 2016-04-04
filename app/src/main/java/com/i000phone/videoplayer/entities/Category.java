package com.i000phone.videoplayer.entities;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/2.
 */
public class Category implements Serializable {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
