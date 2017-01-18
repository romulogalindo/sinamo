package com.sinamo.units;

import java.io.Serializable;
import java.util.List;
import javax.servlet.ServletContextEvent;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 16, 2017 5:40:56 PM
 */
public class ApplicationXMLUnit implements Serializable {

    String applicationName;
    List<ServiceXMLUnit> services;
    ServletContextEvent sce;

    public ApplicationXMLUnit() {
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public List<ServiceXMLUnit> getServices() {
        return services;
    }

    public void setServices(List<ServiceXMLUnit> services) {
        this.services = services;
    }

    public ServletContextEvent getSce() {
        return sce;
    }

    public void setSce(ServletContextEvent sce) {
        this.sce = sce;
    }

}
