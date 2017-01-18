package com.sinamo.units;

import java.io.Serializable;
import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 16, 2017 7:54:06 AM
 */
public class CacheServiceXMLUnit implements Serializable {

    List<SpaceXMLUnit> spaces;

    public CacheServiceXMLUnit() {
    }

    public List<SpaceXMLUnit> getSpaces() {
        return spaces;
    }

    public void setSpaces(List<SpaceXMLUnit> spaces) {
        this.spaces = spaces;
    }

}
