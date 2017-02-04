package com.sinamo.mods;

import com.sinamo.log.Log;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 3, 2017 12:09:22 AM
 */
public class DefaultSection implements Serializable, Cloneable {

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

    @Override
    public DefaultSection clone() throws CloneNotSupportedException {
        DefaultSection sectionClone = (DefaultSection) super.clone();
//        getRegisters().forEach(r -> {
//            try {
//                sectionClone.addRegister(r.clone());
//            } catch (Exception ep) {
//                Log.error(ep, ep);
//            }
//        });
        return sectionClone;
    }

}
