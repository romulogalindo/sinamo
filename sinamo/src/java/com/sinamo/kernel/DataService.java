package com.sinamo.kernel;

import com.sinamo.log.Log;
import com.sinamo.units.DataServiceXMLUnit;
import com.sinamo.units.SourceXMLUnit;
import com.sinamo.util.ReaderXML;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 11, 2017 8:37:57 AM
 */
public class DataService extends Service {

    //Configurador
    DataServiceXMLUnit configuratorXml;

    //Controlador raiz db
    DataBaseSource sysDataBaseSource;
    Map<String, DataBaseSource> dataBaseSources;

    @Override
    public void putConfiguration(Object config) {
        //Seteamos la configuracion desde el archivo de configuracion
        configuratorXml = (DataServiceXMLUnit) config;
        Log.debug("Config xml = " + configuratorXml);
    }

    @Override
    public void start() {
        //Conseguir el elemento principal sysdatasoruce
        sysDataBaseSource = new DataBaseSource(configuratorXml.getSyssource().get(0));
        Log.debug("[start] sysDataBaseSource = " + sysDataBaseSource);
        //Luego de crearla conectarse!
        sysDataBaseSource.connect();
        Log.debug("[start] sysDataBaseSource.connect()");

        //Configurar la lista de bases de datos
        dataBaseSources = new HashMap<>();
        for (SourceXMLUnit sourceXmlUnit : configuratorXml.getSources()) {
            DataBaseSource dataBaseSource = new DataBaseSource(sourceXmlUnit);
            dataBaseSources.put(sourceXmlUnit.getServer() + "_" + sourceXmlUnit.getDbname(), dataBaseSource);
            Log.debug("[start] dataBaseSource = " + dataBaseSource);
            //Luego de crearla conectarse!
            dataBaseSource.connect();
            Log.debug("[start] dataBaseSource.connect()");
        }
    }

    @Override
    public void stop() {
        //Forza el cierre y destruccion de toda conexion a los sources
        try {
            sysDataBaseSource.disconnect();
            dataBaseSources.forEach((k, v) -> {
                v.disconnect();
            });
        } catch (Exception ep) {
            Log.error(ep, ep);
        }
    }

    public static void main(String[] args) {
        DataService dataService = new DataService();
//        ReaderXML rxml = new ReaderXML("/home/romulogalindo/NetBeansProjects/sinamo/sinamo/web/WEB-INF/cfg/DataService.xml");
        DataServiceXMLUnit dsxml = new ReaderXML("/home/romulogalindo/NetBeansProjects/sinamo/sinamo/web/WEB-INF/cfg/DataService.xml").getDataServiceXMLUnit();
        dataService.putConfiguration(dsxml);
        dataService.start();
        dataService.stop();
    }

}
