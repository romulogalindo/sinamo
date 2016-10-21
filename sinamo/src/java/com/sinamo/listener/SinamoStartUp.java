package com.sinamo.listener;

import com.sinamo.kernel.SinamoFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Romulo Galindo Tanta
 * @create Oct 21, 2016 3:32:22 AM
 */
public class SinamoStartUp implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /*primer paso*/
        System.out.println("Iniciando Sinamo ver 1.0");
        SinamoFactory.getSimanoEngine().build();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Fin!!");
    }
}
