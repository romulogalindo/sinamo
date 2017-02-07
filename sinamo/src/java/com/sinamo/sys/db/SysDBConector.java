package com.sinamo.sys.db;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * SysDBConector
 *
 * @author Romulo Galindo Tanta
 * @create Oct 21, 2016 13:22:40 PM
 */
public class SysDBConector {

//    protected Native _native;
    private SessionFactory sessionFactory;
    private String nameSystemDBConector;
    private int idSystemDBConector;

    public SysDBConector(Native _native) {
        Configuration config = _native.getConfiguration();
        config = config.addAnnotatedClass(com.sinamo.dto.TbModulo.class);
        config = config.addAnnotatedClass(com.sinamo.dto.TbMenu.class);
        config = config.addAnnotatedClass(com.sinamo.dto.VwMenuModulo.class);
        config = config.addAnnotatedClass(com.sinamo.dto.TbDataGrid.class);

        Properties prop = config.getProperties();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(prop);
        ServiceRegistry registry = builder.build();

        this.sessionFactory = config.buildSessionFactory(registry);
        System.out.println("sessionFactory = " + sessionFactory);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
