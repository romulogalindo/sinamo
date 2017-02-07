package com.sinamo.mods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 3, 2017 12:09:22 AM
 */
public class DefaultSection implements Serializable {

    String title;
    List<Register> registers;

    public DefaultSection() {
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

    public void addRegister(Register register) {
        if (registers == null) {
            registers = new ArrayList<>();
        }
        registers.add(register);
    }

}
