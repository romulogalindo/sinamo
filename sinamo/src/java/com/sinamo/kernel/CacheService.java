package com.sinamo.kernel;

import com.sinamo.mods.Module;
import com.sinamo.bean.items.MenuItem;
import com.sinamo.dto.TbDataGrid;
import com.sinamo.log.Log;
import com.sinamo.transa.Transaction;
import com.sinamo.units.CacheServiceXMLUnit;
import com.sinamo.units.SpaceXMLUnit;
import com.sinamo.util.ReaderXML;
import java.util.HashMap;
import java.util.Map;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 15, 2017 5:40:09 PM
 */
public class CacheService extends Service {

    //Objeto configurador
    CacheServiceXMLUnit configuratorXml;

    //Manager de chaces
    CacheManager cacheManager;

    //DA to caches
    Map<String, Object> caches;

    //Log-static !--cambiar
    String _log = "[" + getClass().getSimpleName() + "]";

    @Override
    public void putConfiguration(Object config) {
        //Seteamos la configuracion desde el archivo de configuracion
        configuratorXml = (CacheServiceXMLUnit) config;
        Log.debug(_log + " XML > " + configuratorXml);
    }

    @Override
    public void start() {
        Log.debug(_log + "##############################");
        //Inicializacion!!
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        caches = new HashMap<>();

        for (SpaceXMLUnit spaceXml : configuratorXml.getSpaces()) {
            Object myCache = cacheManager.createCache(spaceXml.getName(),
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(getClass(spaceXml.getKey()), getClass(spaceXml.getValue()), ResourcePoolsBuilder.heap(Long.MAX_VALUE)).build());
            caches.put(spaceXml.getName(), myCache);
            Log.log(_log + " Cache creada > nombre= " + spaceXml.getName() + ", key=" + spaceXml.getKey() + ", value=" + spaceXml.getValue());
        }

        //Cache para los menus
        Object cache_menulist = cacheManager.createCache(CACHE_MENUNAME,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, MenuItem.class, ResourcePoolsBuilder.heap(Long.MAX_VALUE)).build());
        caches.put(CACHE_MENUNAME, cache_menulist);
        Log.log(_log + " Cache creada > nombre=" + CACHE_MENUNAME + ", key=Integer, value=MenuItem");

        //Cache para los modulos
        Object cache_modulelist = cacheManager.createCache(CACHE_MODULONAME,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, Module.class, ResourcePoolsBuilder.heap(Long.MAX_VALUE)).build());
        caches.put(CACHE_MODULONAME, cache_modulelist);
        Log.log(_log + " Cache creada > nombre=" + CACHE_MODULONAME + ", key=Integer, value=Module");

        //Cache para las transacciones
        Object cache_transa = cacheManager.createCache(CACHE_TRANSANAME,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Long.class, Transaction.class, ResourcePoolsBuilder.heap(Long.MAX_VALUE)).build());
        caches.put(CACHE_TRANSANAME, cache_transa);
        Log.log(_log + " Cache creada > nombre=" + CACHE_TRANSANAME + ", key=Integer, value=Module");

        //Cache para datagrid
        Object cache_datagrid = cacheManager.createCache(CACHE_DATAGRID,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(Integer.class, TbDataGrid.class, ResourcePoolsBuilder.heap(Long.MAX_VALUE)).build());
        caches.put(CACHE_DATAGRID, cache_datagrid);
        Log.log(_log + " Cache creada > nombre=" + CACHE_DATAGRID + ", key=Integer, value=Module");

//        ((Cache) caches.get("transaction")).put(1L, new String("Hell!!"));
//        ((Cache) caches.get("transaction")).put(2L, new String("Hell!!"));
//        Log.log("exis=" + ((Cache) caches.get("transaction")).get(1L));
    }

    @Override
    public void stop() {
        for (SpaceXMLUnit spaceXml : configuratorXml.getSpaces()) {
            cacheManager.removeCache(spaceXml.getName());
        }
        cacheManager.close();
        cacheManager = null;

        caches.clear();
        caches = null;
    }

    public Class getClass(String className) {
        switch (className) {
            case "long":
                return Long.class;
            case "string":
                return String.class;
            case "int":
                return Integer.class;
            case "double":
                return Double.class;
            case "float":
                return Float.class;
            case "boolean":
                return Boolean.class;
            case "object":
                return Object.class;
            default:
                return null;
        }
    }

    public Cache getCache(String cacheName) {
        return (Cache) caches.get(cacheName);
    }

    public static void main(String[] args) {
        CacheService cs = new CacheService();

        CacheServiceXMLUnit dsxml = new ReaderXML("/home/romulogalindo/NetBeansProjects/sinamo/sinamo/web/WEB-INF/cfg/CacheService.xml").getCacheServiceXMLUnit();
        cs.putConfiguration(dsxml);
        cs.start();
    }

    public static CacheService getCache() {
        CacheService cs = new CacheService();

        CacheServiceXMLUnit dsxml = new ReaderXML("/home/romulogalindo/NetBeansProjects/sinamo/sinamo/web/WEB-INF/cfg/CacheService.xml").getCacheServiceXMLUnit();
        cs.putConfiguration(dsxml);
        cs.start();
        return cs;
    }

    public static final String CACHE_MENUNAME = "@lsmenu";
    public static final String CACHE_MODULONAME = "@lsmodule";
    public static final String CACHE_TRANSANAME = "@transa";
    public static final String CACHE_DATAGRID = "@datagrid";
}
