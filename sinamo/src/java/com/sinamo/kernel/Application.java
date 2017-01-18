package com.sinamo.kernel;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 16, 2017 5:07:45 PM
 */
public abstract class Application {

    private String uniqueNameApplication;

    public abstract void config(Object cfg) throws Exception;

    public abstract void run();

    public abstract DataService getDataService();

    public abstract CacheService getCacheService();

    public abstract JavaScriptService getJSService();

    public abstract HttpService getHttpService();

    public String getUniqueNameApplication() {
        return uniqueNameApplication;
    }

    public void setUniqueNameApplication(String uniqueNameApplication) {
        this.uniqueNameApplication = uniqueNameApplication;
    }

}
