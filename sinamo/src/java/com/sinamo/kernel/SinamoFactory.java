package com.sinamo.kernel;

import com.sinamo.bean.Module;
import com.sinamo.bean.SimpleModule;
import com.sinamo.sys.db.Native_PLSQL;
import com.sinamo.sys.json.Script;

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

    public static Module buildModule(Script script) {
        SimpleModule module = new SimpleModule();
        module.setTitle(script.getHead().getTitle());
        return module;
    }

}
