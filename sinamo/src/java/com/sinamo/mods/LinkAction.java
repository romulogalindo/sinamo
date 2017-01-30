package com.sinamo.mods;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 27, 2017 7:23:13 PM
 */
public class LinkAction extends Action implements Serializable {

    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
