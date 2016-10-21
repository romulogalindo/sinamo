package com.sinamo.kernel;

import com.sinamo.dto.TbModulo;
import com.sinamo.sys.db.Native;
import com.sinamo.sys.db.SysDBConector;
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

    public Engine(Native _native) {
        this._native = _native;

        //Generamos la primera conexion "nativa" del sistema
        this.connectors.put("_native", new SysDBConector(this._native));
    }

    public void build() {
        List<TbModulo> ls = null;
        StatelessSession session = connectors.get("_native").getSessionFactory().openStatelessSession();
        try {
            ls = session.getNamedQuery(Native.TBMODULO_ALL).list();
        } catch (Exception ep) {
            ep.printStackTrace();
        }
        
        session.close();
        
        for (TbModulo modulo : ls) {
            System.out.println("modulo = " + modulo);
        }
    }

}
