package com.sinamo.transa;

import java.io.Serializable;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 27, 2017 12:17:19 PM
 */
public class SimpleTransaction extends Transaction implements Serializable {

    //Time
    Date startTransa;
    Date servletTime;
    Date finishTransa;

    //Informativos
    String ip;
    String ua;
    String path;
    String query;
    String method;

    public SimpleTransaction() {
        this.transactionId = System.currentTimeMillis();

        //initial time
        Date date = new Date(this.transactionId);
        startTransa = date;
    }

    public void setInput(HttpServletRequest request) {
        ua = request.getHeader("User-Agent");
        ip = request.getRemoteAddr();
        path = request.getServletPath();
        query = request.getQueryString();
        method = request.getMethod();
    }

    public Date getStartTransa() {
        return startTransa;
    }

    public void setStartTransa(Date startTransa) {
        this.startTransa = startTransa;
    }

    public Date getFinishTransa() {
        return finishTransa;
    }

    public void setFinishTransa(Date finishTransa) {
        this.finishTransa = finishTransa;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getServletTime() {
        return servletTime;
    }

    public void setServletTime() {
        this.servletTime = new Date();
    }

}
