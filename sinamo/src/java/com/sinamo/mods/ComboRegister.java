package com.sinamo.mods;

import com.sinamo.dto.DataGrid;
import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 3, 2017 7:01:05 PM
 */
public class ComboRegister extends Register {

    String title;
    String data;
    List<DataGrid> values;
    String value;
    String name;

    public ComboRegister() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<DataGrid> getValues() {
        return values;
    }

    public void setValues(List<DataGrid> values) {
        this.values = values;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
