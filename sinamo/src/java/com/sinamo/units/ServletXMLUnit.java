package com.sinamo.units;

import java.io.Serializable;
import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 16, 2017 9:42:05 AM
 */
public class ServletXMLUnit implements Serializable {

    String name;
    String className;
    List<String> patterns;

    public ServletXMLUnit() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }

}
