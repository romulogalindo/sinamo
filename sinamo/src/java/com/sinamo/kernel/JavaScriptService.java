package com.sinamo.kernel;

import com.sinamo.log.Log;
import com.sinamo.units.ClassXMLUnit;
import com.sinamo.units.JavaScriptServiceXMLUnit;
import com.sinamo.units.ScriptXMLUnit;
import com.sinamo.util.ReaderXML;
import java.io.File;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContextEvent;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 14, 2017 12:25:45 AM
 */
public class JavaScriptService extends Service {

    //Configurador
    JavaScriptServiceXMLUnit configuratorXml;

    //Obligatorio recibir el objeto sce
    ServletContextEvent sce;

    //Engine JS Nashron
    ScriptEngine scriptEngine;

    //Puntero de invocacion de funciones
    Invocable invocable;

    //Scripts
    String scriptFromFiles = "";
    String scriptFromClasses = "";
    String scriptFromDBs = "";

    public Object executeFunction(String functionName, Object... args) throws Exception {
        return invocable != null ? invocable.invokeFunction(functionName, args) : null;
    }

    @Override
    public void putConfiguration(Object config) {
        //Seteamos la configuracion desde el archivo de configuracion
        configuratorXml = (JavaScriptServiceXMLUnit) config;
        this.sce = configuratorXml.getSce();
        Log.debug(_log + " XML > " + configuratorXml);
    }

    @Override
    public void start() {
        Log.debug(_log + "##############################");
        scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");

        try {
            Log.info(_log + " Procesando js-files");
            scriptEngine.eval(readerAllScript());
        } catch (ScriptException ep) {
            Log.error(ep, ep);
        }

        invocable = (Invocable) scriptEngine;
    }

    @Override
    public void stop() {
        scriptEngine = null;
        Log.info("[JS-Service] Destruido!");
    }

    public void restart() {
        try {
            Log.info(_log + " re-Procesando js-files");
            scriptEngine.eval(readerAllScript());
        } catch (ScriptException ep) {
            Log.error(ep, ep);
        }

        invocable = (Invocable) scriptEngine;
    }

    public String readerAllScript() {
        scriptFromFiles = "";
        scriptFromClasses = "";
        
        for (ScriptXMLUnit xml : configuratorXml.getScripts()) {
            try {
                File jsFile = new File(sce.getServletContext().getRealPath(xml.getUrl()));
                Log.log(_log + " @file = " + jsFile.getAbsolutePath());
                scriptFromFiles += SinamoFactory.readFile(jsFile) + "\n";
            } catch (Exception ep) {
                Log.error(ep, ep);
            }
        }

        for (ClassXMLUnit xml : configuratorXml.getClasses()) {
            try {
                scriptFromClasses += "var " + xml.getAccessName() + " = Java.type('" + xml.getUrl() + "');\n";
                Log.log(_log + " @var " + xml.getAccessName() + " = Java.type('" + xml.getUrl() + "');");
            } catch (Exception ep) {
                Log.error(ep, ep);
            }
        }

        Log.log(_log + " Script>>>\n" + scriptFromFiles + "\n"
                + scriptFromClasses + "\n"
                + scriptFromDBs);
        return scriptFromFiles + "\n"
                + scriptFromClasses + "\n"
                + scriptFromDBs;
    }

    public String getScriptFromFiles() {
        return scriptFromFiles;
    }

    public void setScriptFromFiles(String scriptFromFiles) {
        this.scriptFromFiles = scriptFromFiles;
    }

    public String getScriptFromClasses() {
        return scriptFromClasses;
    }

    public void setScriptFromClasses(String scriptFromClasses) {
        this.scriptFromClasses = scriptFromClasses;
    }

    public String getScriptFromDBs() {
        return scriptFromDBs;
    }

    public void setScriptFromDBs(String scriptFromDBs) {
        this.scriptFromDBs = scriptFromDBs;
    }

//    public static void main(String[] args) {
//        JavaScriptService js = new JavaScriptService();
//        JavaScriptServiceXMLUnit dsxml = new ReaderXML("/home/romulogalindo/NetBeansProjects/sinamo/sinamo/web/WEB-INF/cfg/JSService.xml").getJavaScriptServiceXMLUnit();
//        js.putConfiguration(dsxml);
//        js.start();
//        js.stop();
//    }
    //Log-static !--cambiar
    String _log = "[" + getClass().getSimpleName() + "]";
}
