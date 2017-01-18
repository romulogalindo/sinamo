package com.sinamo.kernel;

import com.sinamo.log.Log;
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

    @Override
    public void putConfiguration(Object config) {
        //Seteamos la configuracion desde el archivo de configuracion
        configuratorXml = (CacheServiceXMLUnit) config;
        Log.debug("Config xml = " + configuratorXml);
    }

    @Override
    public void start() {
        //Inicializacion!!
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        caches = new HashMap<>();

        for (SpaceXMLUnit spaceXml : configuratorXml.getSpaces()) {
            Object myCache = cacheManager.createCache(spaceXml.getName(),
                    CacheConfigurationBuilder.newCacheConfigurationBuilder(getClass(spaceXml.getKey()), getClass(spaceXml.getValue()), ResourcePoolsBuilder.heap(Long.MAX_VALUE)).build());
            caches.put(spaceXml.getName(), myCache);
            Log.log("myCache = " + myCache);
        }

        ((Cache) caches.get("transaction")).put(1L, new String("Hell!!"));
        ((Cache) caches.get("transaction")).put(2L, new String("Hell!!"));

        Log.log("exis=" + ((Cache) caches.get("transaction")).get(1L));
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
}
