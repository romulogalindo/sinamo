package com.sinamo.kernel;

import com.sinamo.log.Log;
import com.sinamo.sys.servlet.DefaultSinamoServlet;
import com.sinamo.units.HttpServiceXMLUnit;
import com.sinamo.units.ServletXMLUnit;
import java.util.HashMap;
import java.util.Map;
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

    //Aplicacion -Acceso a la aplicacion por defecto
    SinamoApplication sinamoApplication;

    //Log-static !--cambiar
    String _log = "[" + getClass().getSimpleName() + "]";

    public SinamoApplication getSinamoApplication() {
        return sinamoApplication;
    }

    public void setSinamoApplication(SinamoApplication sinamoApplication) {
        this.sinamoApplication = sinamoApplication;
    }

    @Override
    public void putConfiguration(Object config) {
        //Seteamos la configuracion desde el archivo de configuracion
        configuratorXml = (HttpServiceXMLUnit) config;
        this.sce = configuratorXml.getSce();
        Log.debug(_log + " XML > " + configuratorXml);

        //aqui van todos los servlets
        servlets = new HashMap<>();
    }

    @Override
    public void start() {
        //Lanza los elementos
        Log.debug(_log + "##############################");
        for (ServletXMLUnit servletXml : configuratorXml.getServlets()) {
            try {
                Class[] cArg = new Class[1];
                cArg[0] = Application.class;
                Class servletClass = Class.forName(servletXml.getClassName());
                DefaultSinamoServlet servlet = (DefaultSinamoServlet) servletClass.getDeclaredConstructor(cArg).newInstance(sinamoApplication);

                Log.log(_log + " Servlet = " + servletXml.getName());
                servlets.put(servletXml.getName(), servlet);

                ServletRegistration.Dynamic dn = sce.getServletContext().addServlet(servletXml.getName(), servlet);
                for (String pat : servletXml.getPatterns()) {
                    Log.log(_log + " " + servletXml.getName() + " path: " + pat);
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
