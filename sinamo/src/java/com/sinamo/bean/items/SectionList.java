package com.sinamo.bean.items;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulogalindo
 */
public class SectionList extends Section {

    List<ListItem> listItems;
    ListItem listItem;

    public SectionList() {
        listItems = new ArrayList<>();
    }

    public ListItem getListItem() {
        return listItem;
    }

    public void setListItem(ListItem listItem) {
        this.listItem = listItem;
    }

    @Override
    public List<ListItem> getRegisters() {
        return this.listItems;
    }

    @Override
    public void setRegisters(List registers) {
        this.listItems = registers;
    }

    @Override
    public void addRegister(Object register) {
        this.listItems.add((ListItem) register);
    }

}
