package com.sinamo.kernel;

import com.google.gson.Gson;
import com.sinamo.bean.Module;
import com.sinamo.dto.TbModulo;
import com.sinamo.sys.db.Native;
import com.sinamo.sys.db.SysDBConector;
import com.sinamo.sys.json.Script;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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

    public Engine(Native _native) {
        this._native = _native;

        //Generamos la primera conexion "nativa" del sistema
        this.connectors.put("_native", new SysDBConector(this._native));
    }

    public void build() {
        List<TbModulo> ls = null;
        StatelessSession session = connectors.get("_native").getSessionFactory().openStatelessSession();

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

            modules.put(_module.getName(), module);
            System.out.println("modulo = " + module.getTitle());
        }
    }

    public Module getModule(String keyModuleName) {
        return modules.get(keyModuleName);
    }

}
