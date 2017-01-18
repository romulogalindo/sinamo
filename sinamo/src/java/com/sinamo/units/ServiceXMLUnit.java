package com.sinamo.units;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 16, 2017 5:42:39 PM
 */
public class ServiceXMLUnit implements Serializable {

    String type;
    String file;

    public ServiceXMLUnit() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
