package com.sinamo.kernel;

import com.google.gson.Gson;
import com.sinamo.bean.Module;
import com.sinamo.bean.items.Action;
import com.sinamo.bean.items.MenuItem;
import com.sinamo.dto.TbModulo;
import com.sinamo.dto.VwMenuModulo;
import com.sinamo.sys.db.Native;
import com.sinamo.sys.db.SysDBConector;
import com.sinamo.sys.json.Script;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.hibernate.StatelessSession;

/**
 * Engine
 *
 * @author Romulo Galindo Tanta
 * @create Oct 21, 2016 13:22:40 PM
 */
public class Engine {

    Native _native;
    HashMap<String, SysDBConector> connectors = new LinkedHashMap<>();
    HashMap<String, Module> modules = new LinkedHashMap<>();
    List<MenuItem> sysmenu = new ArrayList<>();

    //Engine JS Nashron
    ScriptEngine scriptEngine;

    public Engine(Native _native) {
        this._native = _native;

        //Generamos la primera conexion "nativa" del sistema
        this.connectors.put("_native", new SysDBConector(this._native));
    }

    public void build() {
        //Levantando Motor JS
        scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        String nashronScript = "";
        try {
            nashronScript = SinamoFactory.readFile(new File(this.getClass().getResource("Nashron.js").toURI()));
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        //Iniciando cnx _native
        StatelessSession session = connectors.get("_native").getSessionFactory().openStatelessSession();

        /*Menus*/
        List<VwMenuModulo> vwmenumodulos = session.getNamedQuery(Native.VWMENUMODULO_ALL).list();
        sysmenu = SinamoFactory.builMenu(vwmenumodulos);

        /*Modulos*/
        List<TbModulo> ls = null;

        System.out.println("## (DB)consiguiendo lista de modulos");
        try {
            ls = session.getNamedQuery(Native.TBMODULO_ALL).list();
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        session.close();

        for (TbModulo _module : ls) {
            Script script = new Gson().fromJson(_module.getScript(), Script.class);

            Module module = SinamoFactory.buildModule(script);

            //Script de creacion
            String funcBuildName = "_func" + _module.getId();
            nashronScript = nashronScript + " function " + funcBuildName + "(_R){"
                    + (_module.getData() == null ? "" : _module.getData())
                    + "}";
            module.setDataScript(funcBuildName);

            //Script de accion
            nashronScript = nashronScript + " function _act" + _module.getId() + "(_requestStringValue){"
                    + (_module.getAction() == null ? "" : _module.getAction())
                    + "}";
            
            for (Action action : module.getActions()) {
                action.setDoit("execute(sJS.build(this,'" + action.getId() + "','_act" + _module.getId() + "'))");
            }

            System.out.println("_module.getId()==>" + _module.getId());
            modules.put("" + _module.getId(), module);
            System.out.println("modulo(" + _module.getId() + ") = " + module.getTitle());
        }

        try {
            System.out.println("Final script!\n" + nashronScript);
            scriptEngine.eval(nashronScript);
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }

    public Module getModule(String keyModuleName) {
        return modules.get(keyModuleName);
    }

    public List<MenuItem> getSysmenu() {
        return sysmenu;
    }

    public ScriptEngine getScriptEngine() {
        return scriptEngine;
    }

}
