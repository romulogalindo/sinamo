package com.sinamo.mods;

import com.sinamo.bean.items.*;
import java.io.Serializable;

/**
 *
 * @author romulogalindo
 */
public class Register implements Serializable, Cloneable {

    String type;

    public Register() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Register clone() throws CloneNotSupportedException {
        Register registerClone = (Register) super.clone();
        return registerClone;
    }

}
