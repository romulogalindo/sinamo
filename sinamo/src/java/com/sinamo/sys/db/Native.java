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
    public static final String TBMENU_ALL = "system.tbmenu";
    public static final String TBFUNCTION_ALL = "system.tbfunction";
    public static final String TBFUNCTION_BYID = "system.tbfunction_byid";
    public static final String TBDATAGRID_ALL = "system.tbdatagrid";
    public static final String TBDATAGRID_BYID = "system.tbdatagrid_byid";
    public static final String VWMENUMODULO_ALL = "system.vwmenumodulo";

    public abstract Configuration getConfiguration();

}
