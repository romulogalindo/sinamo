package com.sinamo.sys.db;

import org.hibernate.cfg.Configuration;

/**
 * Native_PLSQL
 *
 * @author Romulo Galindo Tanta
 * @create Oct 21, 2016 13:22:40 PM
 */
public class Native_PLSQL extends Native {

    @Override
    public Configuration getConfiguration() {
        Configuration config = new Configuration().configure("Native.cfg.xml");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        config.setProperty("hibernate.connection.datasource", "java:comp/env/PoolSinamo");
        return config;
    }

}
