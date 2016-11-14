package com.sinamo.bean.items;

import java.util.HashMap;

/**
 *
 * @author romulogalindo
 */
public class ResponseGoTo extends Response {

    public String goTo;
    public HashMap<String, String> values;

    public ResponseGoTo() {
        values = new HashMap<>();
    }

    @Override
    public int getType() {
        return RESPONSE_GOTO;
    }

    public String getGoTo() {
        return goTo;
    }

    public void setGoTo(String goTo) {
        this.goTo = goTo;
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }

    public void addValue(String K, String V) {
        this.values.put(K, V);
    }

}
