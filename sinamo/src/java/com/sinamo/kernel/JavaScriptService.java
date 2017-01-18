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

/**
 * @author Romulo Galindo Tanta
 * @create Jan 14, 2017 12:25:45 AM
 */
public class JavaScriptService extends Service {

    //Configurador
    JavaScriptServiceXMLUnit configuratorXml;

    //Engine JS Nashron
    ScriptEngine scriptEngine;

    static CacheService cacheservice;

    //extra
    static {
        cacheservice = CacheService.getCache();
    }

    @Override
    public void putConfiguration(Object config) {
        //Seteamos la configuracion desde el archivo de configuracion
        configuratorXml = (JavaScriptServiceXMLUnit) config;
        Log.debug("Config xml = " + configuratorXml);
    }

    @Override
    public void start() {
        Log.info("[JS-Service] Levantando nashron engine");
        scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");

        try {
            Log.info("[JS-Service] Cargando ja-files");
            scriptEngine.eval(readerAllScript());
        } catch (ScriptException ep) {
            Log.error(ep, ep);
        }

        Invocable invocable = (Invocable) scriptEngine;
        Object result = new Object();
        try {
//            result = invocable.invokeFunction("getXmlUnit", "postgresql");
            result = invocable.invokeFunction("getValuefromCache", "transaction", 1L);
        } catch (Exception ep) {
            Log.error(ep, ep);
        }

        Log.log("result = " + result);
        Log.log("?->" + result != null ? result.getClass() : "null!");
    }

    public String readerAllScript() {
        String nashronScript = "";
        for (ScriptXMLUnit xml : configuratorXml.getScripts()) {
            try {
//                Log.debug("url = " + scriptXmlUnit.getUrl());
//                Log.debug("url = " + this.getClass().getResource(scriptXmlUnit.getUrl()));
//                nashronScript += SinamoFactory.readFile(new File(this.getClass().getResource(scriptXmlUnit.getUrl()).toURI()));
                nashronScript += SinamoFactory.readFile(new File("/home/romulogalindo/NetBeansProjects/sinamo/sinamo/web" + xml.getUrl())) + "\n";
            } catch (Exception ep) {
                Log.error(ep, ep);
            }
        }

        for (ClassXMLUnit xml : configuratorXml.getClasses()) {
            try {
                nashronScript += "var " + xml.getAccessName() + " = Java.type('" + xml.getUrl() + "');\n";
            } catch (Exception ep) {
                Log.error(ep, ep);
            }
        }

        Log.debug("[readerAllScript]" + nashronScript);
        return nashronScript;
    }

    @Override
    public void stop() {
        scriptEngine = null;
        Log.info("[JS-Service] Destruido!");
    }

    public static void main(String[] args) {
        JavaScriptService js = new JavaScriptService();
        JavaScriptServiceXMLUnit dsxml = new ReaderXML("/home/romulogalindo/NetBeansProjects/sinamo/sinamo/web/WEB-INF/cfg/JSService.xml").getJavaScriptServiceXMLUnit();
        js.putConfiguration(dsxml);
        js.start();
        js.stop();
    }

    public static Object get(String cacheName, Object key) {
        return cacheservice.getCache(cacheName).get(key);
    }

}
