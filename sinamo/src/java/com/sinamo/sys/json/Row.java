package com.sinamo.sys.json;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Oct 22, 2016 1:06:38 AM
 */
public class Row implements Serializable {

    String title;
    String type;
    String value;

    public Row() {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
