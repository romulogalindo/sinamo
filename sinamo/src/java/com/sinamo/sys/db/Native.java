package com.sinamo.sys.db;

import org.hibernate.cfg.Configuration;

/**
 * abstract class Native
 *
 * @author Romulo Galindo Tanta
 * @create Oct 21, 2016 13:20:40 PM
 */
public abstract class Native {

//    public String TBMODULO_ALL;
    public static final String TBMODULO_ALL = "system.tbmodulo";

    public abstract Configuration getConfiguration();

}
