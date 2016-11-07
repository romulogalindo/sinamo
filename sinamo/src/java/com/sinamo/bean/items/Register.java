package com.sinamo.bean.items;

import java.io.Serializable;

/**
 *
 * @author romulogalindo
 */
public class Register implements Serializable {

    String title;
    String type;
    String value;

    public Register() {
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
