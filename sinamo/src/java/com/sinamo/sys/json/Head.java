package com.sinamo.sys.json;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Oct 22, 2016 1:26:37 AM
 */
public class Head implements Serializable {

    String title;
    String type;

    public Head() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
