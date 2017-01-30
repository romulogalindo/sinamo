package com.sinamo.bean.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulogalindo
 */
public class MenuItem implements Serializable {

    Integer id;
    String title;
    List<ModuleItem> moduleitems;

    public MenuItem() {
        moduleitems = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ModuleItem> getModuleitems() {
        return moduleitems;
    }

    public void setModuleitems(List<ModuleItem> moduleitems) {
        this.moduleitems = moduleitems;
    }

    public void addModuleItem(ModuleItem moduleitem) {
        this.moduleitems.add(moduleitem);
    }

    @Override
    public String toString() {
        return "MenuItem{" + "id=" + id + ", title=" + title + ", moduleitems=" + moduleitems + '}';
    }

}
