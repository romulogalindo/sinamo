package com.sinamo.units;

import java.io.Serializable;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 11, 2017 5:15:41 PM
 */
public class SourceXMLUnit implements Serializable {

    String type;
    String server;
    String port;
    String dbname;
    String user;
    String password;
    String engine;

    public SourceXMLUnit() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "SourceXMLUnit{" + "type=" + type + ", server=" + server + ", port=" + port + ", dbname=" + dbname + ", user=" + user + ", password=" + password + ", engine=" + engine + '}';
    }

}
