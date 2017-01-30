package com.sinamo.kernel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 16, 2017 5:09:06 PM
 */
public class Sinamo implements Serializable {

//    private static Application application;
    //Lista de aplicaciones corriendo;
    private static Map<String, Application> applications = new HashMap<>();

    public static synchronized void putApplication(Application app) {
        applications.put(app.getUniqueNameApplication(), app);
    }

    public static synchronized Application getApplication(String uniqueNameApplication) {
        return applications.get(uniqueNameApplication);
    }

}
