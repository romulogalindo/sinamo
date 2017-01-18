package com.sinamo.kernel;

import com.sinamo.log.Log;
import com.sinamo.sys.servlet.LinkManager;
import com.sinamo.units.HttpServiceXMLUnit;
import com.sinamo.units.ServletXMLUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 16, 2017 10:39:40 AM
 */
public class HttpService extends Service {

    //Objeto configurador
    HttpServiceXMLUnit configuratorXml;

    //Obligatorio recibir el objeto sce
    ServletContextEvent sce;

    //Lista de servlets
    Map<String, HttpServlet> servlets;

    @Override
    public void putConfiguration(Object config) {
        //Seteamos la configuracion desde el archivo de configuracion
        configuratorXml = (HttpServiceXMLUnit) config;
        this.sce = configuratorXml.getSce();
        Log.debug("Config xml = " + configuratorXml);

        //aqui van todos los servlets
        servlets = new HashMap<>();
    }

    @Override
    public void start() {
        //Lanza los elementos
        for (ServletXMLUnit servletXml : configuratorXml.getServlets()) {
            try {
                Class servletClass = Class.forName(servletXml.getClassName());
                HttpServlet servlet = (HttpServlet) servletClass.newInstance();
                Log.log("Name = " + servletXml.getName());
                Log.log("Instancia creada = " + servlet);
                Log.log("Instancia creada = " + servlet.getClass());
                servlets.put(servletXml.getName(), servlet);

                ServletRegistration.Dynamic dn = sce.getServletContext().addServlet(servletXml.getName(), servlet);
                for (String pat : servletXml.getPatterns()) {
                    Log.log("Pat=" + pat);
                    dn.addMapping(pat);
                }
                dn.setAsyncSupported(true);
            } catch (Exception ep) {
                Log.error(ep, ep);
            }

        }
    }

    @Override
    public void stop() {
        servlets.clear();
    }

}
