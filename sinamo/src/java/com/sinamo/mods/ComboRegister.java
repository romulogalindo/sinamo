package com.sinamo.mods;

import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 3, 2017 7:01:05 PM
 */
public class ComboRegister extends Register {

    String title;
    List<String> values;
    String name;

    public ComboRegister() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
