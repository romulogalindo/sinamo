package com.sinamo.mods;

import com.sinamo.log.Log;
import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 27, 2017 7:18:30 PM
 */
public class Content implements Serializable, Cloneable {
    
    String type;
    String state;
    Form form;
    
    public Content() {
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public Form getForm() {
        return form;
    }
    
    public void setForm(Form form) {
        this.form = form;
    }
    
    @Override
    public String toString() {
        return "Content{" + "type=" + type + ", state=" + state + ", form=" + form + '}';
    }
    
    @Override
    public Content clone() throws CloneNotSupportedException {
        Log.debug("Clonando contenido");
        Content contentClone = (Content) super.clone();
        return contentClone;
    }
    
}
