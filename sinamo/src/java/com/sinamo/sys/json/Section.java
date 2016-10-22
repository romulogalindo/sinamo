package com.sinamo.sys.json;

import java.io.Serializable;
import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Oct 22, 2016 1:05:51 AM
 */
public class Section implements Serializable {

    String title;
    List<Row> rows;

    public Section() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

}
