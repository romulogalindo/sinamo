package com.sinamo.kernel;

import com.sinamo.log.Log;
import com.sinamo.units.ApplicationXMLUnit;
import com.sinamo.units.CacheServiceXMLUnit;
import com.sinamo.units.ServiceXMLUnit;
import com.sinamo.util.ReaderXML;
import java.io.File;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 16, 2017 5:08:22 PM
 */
public class SinamoApplication extends Application {

    //Objeto configurador
    ApplicationXMLUnit configuratorXml;

    //servicios
    DataService dataService;
    JavaScriptService jsService;
    CacheService cacheService;
    HttpService httpService;

    //Instanciar los servicios
    @Override
    public void config(Object cfg) throws Exception {
        //Seteamos la configuracion desde el archivo de configuracion
        configuratorXml = (ApplicationXMLUnit) cfg;
        Log.debug("Config xml = " + configuratorXml);

        for (ServiceXMLUnit serviceXml : configuratorXml.getServices()) {
            if (serviceXml.getType().contentEquals("data")) {
                dataService = new DataService();
                dataService.putConfiguration(new ReaderXML(new File(configuratorXml.getSce().getServletContext().getRealPath(serviceXml.getFile()))).getDataServiceXMLUnit());
            } else if (serviceXml.getType().contentEquals("js")) {
                jsService = new JavaScriptService();
                jsService.putConfiguration(new ReaderXML(new File(configuratorXml.getSce().getServletContext().getRealPath(serviceXml.getFile()))).getJavaScriptServiceXMLUnit());
            } else if (serviceXml.getType().contentEquals("cache")) {
                cacheService = new CacheService();
                cacheService.putConfiguration(new ReaderXML(new File(configuratorXml.getSce().getServletContext().getRealPath(serviceXml.getFile()))).getCacheServiceXMLUnit());
            } else if (serviceXml.getType().contentEquals("http")) {
                httpService = new HttpService();
                httpService.putConfiguration(new ReaderXML(new File(configuratorXml.getSce().getServletContext().getRealPath(serviceXml.getFile())), configuratorXml.getSce()).getHttpServiceXMLUnit());
            }
        }
    }

    @Override
    public void run() {
        //iniciar los servicios
        httpService.start();
        dataService.start();
        jsService.start();
        cacheService.start();

    }

    @Override
    public DataService getDataService() {
        return dataService;
    }

    @Override
    public CacheService getCacheService() {
        return cacheService;
    }

    @Override
    public JavaScriptService getJSService() {
        return jsService;
    }

    @Override
    public HttpService getHttpService() {
        return httpService;
    }

}
