package com.sinamo.units;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 14, 2017 8:55:30 AM
 */
public class ClassXMLUnit implements Serializable {

    String url;
    String accessName;

    public ClassXMLUnit() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }

}
