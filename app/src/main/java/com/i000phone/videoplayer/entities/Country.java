package com.i000phone.videoplayer.entities;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/2.
 */
public class Country implements Serializable {
    private String iso_3166_1;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }
}
