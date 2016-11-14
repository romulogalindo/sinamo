package com.sinamo.bean.items;

import java.io.Serializable;

/**
 *
 * @author romulogalindo
 */
public class ModuleItem implements Serializable {

    public String id;
    public String title;

    public ModuleItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
