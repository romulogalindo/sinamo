package com.sinamo.bean;

import com.sinamo.bean.items.Action;
import com.sinamo.bean.items.Section;
import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Oct 22, 2016 1:20:27 AM
 */
public abstract class Module {

    String title;
    List<Section> sections;
    List<Action> actions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

}
