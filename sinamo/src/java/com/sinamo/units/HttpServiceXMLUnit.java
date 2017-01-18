package com.sinamo.units;

import java.io.Serializable;
import java.util.List;
import javax.servlet.ServletContextEvent;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 16, 2017 9:35:16 AM
 */
public class HttpServiceXMLUnit implements Serializable {

    List<ServletXMLUnit> servlets;
    List<RestFulXMLUnit> restfuls;
    List<WebSocketXMLUnit> websockets;

    //Object no transaccionales
    ServletContextEvent sce;

    public HttpServiceXMLUnit() {
    }

    public List<ServletXMLUnit> getServlets() {
        return servlets;
    }

    public void setServlets(List<ServletXMLUnit> servlets) {
        this.servlets = servlets;
    }

    public List<RestFulXMLUnit> getRestfuls() {
        return restfuls;
    }

    public void setRestfuls(List<RestFulXMLUnit> restfuls) {
        this.restfuls = restfuls;
    }

    public List<WebSocketXMLUnit> getWebsockets() {
        return websockets;
    }

    public void setWebsockets(List<WebSocketXMLUnit> websockets) {
        this.websockets = websockets;
    }

    public ServletContextEvent getSce() {
        return sce;
    }

    public void setSce(ServletContextEvent sce) {
        this.sce = sce;
    }

}
