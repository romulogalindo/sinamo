package com.sinamo.sys.json;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Oct 22, 2016 1:08:37 AM
 */
public class Action implements Serializable {

    String id;
    String doit;
    String title;

    public Action() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoit() {
        return doit;
    }

    public void setDoit(String doit) {
        this.doit = doit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
