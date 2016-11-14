package com.sinamo.bean.items;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author romulogalindo
 */
public abstract class Section implements Serializable {

    String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract List getRegisters();

    public abstract void setRegisters(List registers);
    
    public abstract void addRegister(Object register);

}
