package com.sinamo.mods;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 27, 2017 7:21:12 PM
 */
public class Action implements Serializable {

    int id;
    String type;
    String title;

    public Action() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
