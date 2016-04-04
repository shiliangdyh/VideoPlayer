package com.i000phone.videoplayer.entities;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/2.
 */
public class Language implements Serializable {
    private String iso_639_1;
    private String name;

    public String getIso_639_1() {
        return iso_639_1;
    }

    public void setIso_639_1(String iso_639_1) {
        this.iso_639_1 = iso_639_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
