package com.sinamo.dto;

import com.sinamo.sys.db.Native;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 5, 2017 9:19:43 PM
 */
@Entity
public class DataGrid implements Serializable {

    @Id
    String Key;
    String value;

    public DataGrid() {
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String Key) {
        this.Key = Key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
