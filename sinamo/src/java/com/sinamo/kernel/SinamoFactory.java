package com.sinamo.kernel;

import com.sinamo.sys.db.Native_PLSQL;

/**
 * SinamoFactory
 *
 * @author Romulo Galindo Tanta
 * @create Oct 21, 2016 13:22:40 PM
 */
public class SinamoFactory {

    static final Engine simanoEngine;

    static {
        simanoEngine = new Engine(new Native_PLSQL());
    }

    public static Engine getSimanoEngine() {
        return simanoEngine;
    }

}
