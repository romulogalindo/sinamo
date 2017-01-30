package com.sinamo.mods;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 27, 2017 7:06:39 PM
 */
public class Head implements Serializable {

    String title;

    public Head() {
    }

    public Head(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Head{" + "title=" + title + '}';
    }

}
