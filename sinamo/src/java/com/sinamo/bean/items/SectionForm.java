package com.sinamo.bean.items;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulogalindo
 */
public class SectionForm extends Section {

    List<Register> registers;

    public SectionForm() {
        registers = new ArrayList<>();
    }

    @Override
    public List<Register> getRegisters() {
        return registers;
    }

    @Override
    public void setRegisters(List registers) {
        this.registers = registers;
    }
    
    @Override
    public void addRegister(Object register) {
        this.registers.add((Register) register);
    }

}
