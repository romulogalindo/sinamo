package com.sinamo.sys.db;

import org.hibernate.cfg.Configuration;

/**
 * Native_MYSQL
 *
 * @author Romulo Galindo Tanta
 * @create Oct 21, 2016 13:22:40 PM
 */
public class Native_MYSQL extends Native {

    @Override
    public Configuration getConfiguration() {
        Configuration config = new Configuration().configure("Native.cfg.xml");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        config.setProperty("hibernate.connection.datasource", "java:comp/env/PoolSinamo");
        return config;
    }

}
