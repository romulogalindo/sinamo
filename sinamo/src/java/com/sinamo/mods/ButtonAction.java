package com.sinamo.mods;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 27, 2017 7:22:21 PM
 */
public class ButtonAction extends Action {

    String func;
    String confirm = "false";

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

}
