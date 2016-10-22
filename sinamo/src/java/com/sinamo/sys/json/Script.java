package com.sinamo.sys.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Oct 22, 2016 1:03:39 AM
 */
public class Script implements Serializable {

    Head head;
    List<Section> sections;
    List<Action> actions;

    public Script() {
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Script{" + "head=" + head + ", sections=" + sections + ", actions=" + actions + '}';
    }

}
