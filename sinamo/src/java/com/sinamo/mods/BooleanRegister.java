package com.sinamo.mods;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 3, 2017 7:01:05 PM
 */
public class BooleanRegister extends Register {

    String title;
    boolean value = false;
    String name;

    public BooleanRegister() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
