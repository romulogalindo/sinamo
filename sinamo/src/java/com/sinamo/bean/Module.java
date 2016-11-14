package com.sinamo.bean;

import com.sinamo.bean.items.Action;
import com.sinamo.bean.items.Section;
import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Oct 22, 2016 1:20:27 AM
 */
public abstract class Module {

    long pid;
    String title;
    String type;
    String dataScript;
    List<Action> actions;

    public Module() {
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDataScript() {
        return dataScript;
    }

    public void setDataScript(String dataScript) {
        this.dataScript = dataScript;
    }

    public abstract List getSections();

    public abstract void setSections(List sections);

    public abstract void addSection(Section section);

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

}
