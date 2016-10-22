package com.sinamo.sys.json;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Oct 22, 2016 1:26:37 AM
 */
public class Head implements Serializable {

    String title;

    public Head() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
