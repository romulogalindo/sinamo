package com.sinamo.units;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 11, 2017 12:58:56 PM
 */
public class SinamoDriverDBUnit implements Serializable {

    long id;
    String name;
    String drivername;
    String dialect;

    public SinamoDriverDBUnit(String name, String drivername, String dialect) {
        this(System.currentTimeMillis(), name, drivername, dialect);
    }

    public SinamoDriverDBUnit(long id, String name, String drivername, String dialect) {
        this.id = id;
        this.name = name;
        this.drivername = drivername;
        this.dialect = dialect;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    @Override
    public String toString() {
        return "SinamoDriverDBUnit{" + "id=" + id + ", name=" + name + ", drivername=" + drivername + ", dialect=" + dialect + '}';
    }

}
