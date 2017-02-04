package com.sinamo.mods;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 3, 2017 7:02:42 PM
 */
public class HiddenRegister extends Register {

    String value;
    String name;

    public HiddenRegister() {
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
    public HiddenRegister clone() throws CloneNotSupportedException {
        HiddenRegister registerClone = (HiddenRegister) super.clone();
        return registerClone;
    }
}
