package com.sinamo.bean.items;

import java.io.Serializable;

/**
 *
 * @author romulogalindo
 */
public class ListItem implements Serializable {

    String title;
    String title2;
    String title3;
    String icon;
    String value;

    public ListItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getTitle3() {
        return title3;
    }

    public void setTitle3(String title3) {
        this.title3 = title3;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ListItem{" + "title=" + title + ", title2=" + title2 + ", title3=" + title3 + ", icon=" + icon + ", value=" + value + '}';
    }

}
