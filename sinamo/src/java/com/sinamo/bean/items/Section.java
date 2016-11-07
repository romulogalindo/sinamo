package com.sinamo.bean.items;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author romulogalindo
 */
public class Section implements Serializable{

    String title;
    List<Register> registers;

    public Section() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

}
