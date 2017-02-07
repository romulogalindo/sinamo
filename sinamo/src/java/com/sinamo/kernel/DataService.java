package com.sinamo.kernel;

import com.sinamo.log.Log;
import com.sinamo.units.DataServiceXMLUnit;
import com.sinamo.units.SourceXMLUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 11, 2017 8:37:57 AM
 */
public class DataService extends Service {

    //Configurador
    private DataServiceXMLUnit configuratorXml;

    //Controlador raiz db
    private DataBaseSource sysDataBaseSource;
    private Map<String, DataBaseSource> dataBaseSources;

    @Override
    public void putConfiguration(Object config) {
        //Seteamos la configuracion desde el archivo de configuracion
        configuratorXml = (DataServiceXMLUnit) config;
        Log.debug(_log + " XML > " + configuratorXml);
    }

    @Override
    public void start() {
        //Conseguir el elemento principal sysdatasoruce
        Log.debug(_log + "##############################");
        sysDataBaseSource = new DataBaseSource(configuratorXml.getSyssource().get(0));
        Log.debug(_log + " DBSys = " + sysDataBaseSource.getSourceName());

        //Luego de crearla conectarse!
        sysDataBaseSource.connect();
        Log.debug(_log + " " + sysDataBaseSource.getSourceName() + " Conectada!");

        //Configurar la lista de bases de datos
        dataBaseSources = new HashMap<>();
        for (SourceXMLUnit sourceXmlUnit : configuratorXml.getSources()) {
            DataBaseSource dataBaseSource = new DataBaseSource(sourceXmlUnit);
            dataBaseSources.put(sourceXmlUnit.getName(), dataBaseSource);

            //Luego de crearla conectarse!
            boolean dbConnected = dataBaseSource.connect();
            Log.debug(_log + " status =" + (dbConnected ? "Conected" : "Disconect"));
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

    public DataBaseSource getSysDataBaseSource() {
        return sysDataBaseSource;
    }

    public Map<String, DataBaseSource> getDataBaseSources() {
        return dataBaseSources;
    }

    //Log-static !--cambiar
    String _log = "[" + getClass().getSimpleName() + "]";
}
