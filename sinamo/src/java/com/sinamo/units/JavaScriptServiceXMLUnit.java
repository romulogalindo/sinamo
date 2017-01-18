package com.sinamo.units;

import java.io.Serializable;
import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 14, 2017 8:52:56 AM
 */
public class JavaScriptServiceXMLUnit implements Serializable {

    List<ScriptXMLUnit> scripts;
    List<ClassXMLUnit> classes;

    public JavaScriptServiceXMLUnit() {
    }

    public List<ScriptXMLUnit> getScripts() {
        return scripts;
    }

    public void setScripts(List<ScriptXMLUnit> scripts) {
        this.scripts = scripts;
    }

    public List<ClassXMLUnit> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassXMLUnit> classes) {
        this.classes = classes;
    }

}
