package com.sinamo.mods;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 27, 2017 7:05:22 PM
 */
public class Module implements Serializable, Cloneable {

    //De la forma
    Head head;
    Content content;
    Map<Integer, Action> actions;

    //De la data
    String dataScriptName;

    public Module() {
        head = null;
        content = null;
        actions = null;
        dataScriptName = null;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Map<Integer, Action> getActions() {
        return actions;
    }

    public void setActions(Map<Integer, Action> actions) {
        this.actions = actions;
    }

    public String getDataScriptName() {
        return dataScriptName;
    }

    public void setDataScriptName(String dataScriptName) {
        this.dataScriptName = dataScriptName;
    }

    @Override
    public String toString() {
        return "Module{" + "head=" + head + ", content=" + content + ", actions=" + actions + '}';
    }

    @Override
    public Module clone() throws CloneNotSupportedException {
        Module moduleClone = (Module) super.clone();
        return moduleClone;
    }

}
