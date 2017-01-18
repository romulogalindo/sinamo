package com.sinamo.units;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 16, 2017 7:54:42 AM
 */
public class SpaceXMLUnit implements Serializable {

    String name;
    String key;
    String value;

    public SpaceXMLUnit() {
    }

    public SpaceXMLUnit(String name, String key, String value) {
        this.name = name;
        this.key = key;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
