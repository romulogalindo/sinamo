package com.sinamo.kernel;

import com.sinamo.mods.Module;
import com.sinamo.bean.items.MenuItem;
import com.sinamo.log.Log;
import com.sinamo.mods.Content;
import com.sinamo.mods.DynamicListForm;
import com.sinamo.mods.SectionForm;
import com.sinamo.transa.Transaction;
import com.sinamo.units.ApplicationXMLUnit;
import com.sinamo.units.ServiceXMLUnit;
import com.sinamo.util.ReaderXML;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    //
    //Log-static !--cambiar
    String _log = "[" + getClass().getSimpleName() + "]";

    //Instanciar los servicios
    @Override
    public void config(Object cfg) throws Exception {
        //Seteamos la configuracion desde el archivo de configuracion
        configuratorXml = (ApplicationXMLUnit) cfg;
        this.setUniqueNameApplication(configuratorXml.getApplicationName());

        Log.log(_log + " Iniciando configuracion de la app ##" + configuratorXml.getApplicationName() + "##");

        Log.debug(_log + " Configurando servicios################");
        for (ServiceXMLUnit serviceXml : configuratorXml.getServices()) {
            ReaderXML readerXml = new ReaderXML(
                    new File(configuratorXml.getSce().getServletContext().getRealPath(serviceXml.getFile())),
                    configuratorXml.getSce()
            );
            Log.debug(_log + " XML > " + readerXml);

            switch (serviceXml.getType()) {
                case "data":
                    dataService = new DataService();
                    dataService.putConfiguration(readerXml.getDataServiceXMLUnit());
                    Log.log(_log + " DataService[" + dataService + "] Configurado!");
                    break;
                case "js":
                    jsService = new JavaScriptService();
                    jsService.putConfiguration(readerXml.getJavaScriptServiceXMLUnit());
                    Log.log(_log + " JsService[" + jsService + "] Configurado!");
                    break;
                case "cache":
                    cacheService = new CacheService();
                    cacheService.putConfiguration(readerXml.getCacheServiceXMLUnit());
                    Log.log(_log + " CacheService[" + cacheService + "] Configurado!");
                    break;
                case "http":
                    httpService = new HttpService();
                    httpService.putConfiguration(readerXml.getHttpServiceXMLUnit());
                    httpService.setSinamoApplication(this);
                    Log.log(_log + " HttpService[" + httpService + "] Configurado!");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void run() {
        //iniciar los servicios
        Log.log(_log + " Iniciando servicios####################");

        //Iniciar la DBSYS y las otras fuentes de datos necesarias
        dataService.start();
        Log.log(_log + " dataService OK");

        jsService.start();
        Log.log(_log + " jsService OK");

        cacheService.start();
        Log.log(_log + " cacheService OK");

        httpService.start();
        Log.log(_log + " httpService OK");

        //UP! modules
        Engine engine = new Engine();
        engine.buildSystem(dataService, cacheService, jsService);

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

    //Metodos para acceso rapido
    public Module getModule(Integer idModule) {
        Module module = null;
        try {
            Module moduleBase = ((Module) this.getCacheService().getCache(CacheService.CACHE_MODULONAME).get(idModule)).clone();
            module = (Module) copy(moduleBase);
        } catch (Exception ep) {
        }
        return module;
    }

    public static Object copy(Object fromBean) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder out = new XMLEncoder(bos);
        out.writeObject(fromBean);
        out.close();
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        XMLDecoder in = new XMLDecoder(bis);
        Object toBean = in.readObject();
        in.close();
        return toBean;
    }

    public void saveTransa(Transaction transa) {
        this.getCacheService().getCache(CacheService.CACHE_TRANSANAME).put(transa.getTransactionId(), transa);
    }

    public Transaction getTransa(Long transaId) {
        return (Transaction) this.getCacheService().getCache(CacheService.CACHE_TRANSANAME).get(transaId);
    }

    //!-- Metodo debe de cambiar
    public List<MenuItem> getSysmenu() {
        List<MenuItem> lsmenu = new ArrayList<>();
        lsmenu.add((MenuItem) this.getCacheService().getCache(CacheService.CACHE_MENUNAME).get(1));
//        lsmenu.add((MenuItem) this.getCacheService().getCache(CacheService.CACHE_MENUNAME).get(2));
        return lsmenu;
    }

}
