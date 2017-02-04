package com.sinamo.mods;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 3, 2017 7:01:05 PM
 */
public class InputRegister extends Register implements Cloneable {

    String title;
    String value;
    String name;

    public InputRegister() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public InputRegister clone() throws CloneNotSupportedException {
        InputRegister registerClone = (InputRegister) super.clone();
        return registerClone;
    }

}
