package com.sinamo.kernel;

import com.google.gson.Gson;
import com.sinamo.bean.Module;
import com.sinamo.bean.items.Action;
import com.sinamo.bean.items.MenuItem;
import com.sinamo.dto.TbFunction;
import com.sinamo.dto.TbModulo;
import com.sinamo.dto.VwMenuModulo;
import com.sinamo.mods.BooleanRegister;
import com.sinamo.mods.ButtonAction;
import com.sinamo.mods.ComboRegister;
import com.sinamo.mods.DefaultList;
import com.sinamo.mods.DefaultSection;
import com.sinamo.mods.DynamicListForm;
import com.sinamo.mods.HiddenRegister;
import com.sinamo.mods.InputRegister;
import com.sinamo.mods.LinkAction;
import com.sinamo.mods.Register;
import com.sinamo.mods.SectionForm;
import com.sinamo.sys.db.Native;
import com.sinamo.sys.db.SysDBConector;
import com.sinamo.sys.json.Script;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.hibernate.StatelessSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

    public Engine() {
    }

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
                    //                    + (_module.getAction() == null ? "" : _module.getAction())
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

    public void buildSystem(DataService dataService, CacheService cacheService, JavaScriptService jsService) {
        //Objeto de conexion
        StatelessSession ss = dataService.getSysDataBaseSource().getSession();

        /*Menus*/
        List<VwMenuModulo> lsmenus = ss.getNamedQuery(Native.VWMENUMODULO_ALL).list();
        List<TbModulo> vwmodule = ss.getNamedQuery(Native.TBMODULO_ALL).list();
        for (TbModulo _module : vwmodule) {
            _module.setFunctions(ss.getNamedQuery(Native.TBFUNCTION_BYID).setInteger("_id", _module.getId()).list());
        }
        //Cierre de conexion
        ss.close();

        //En base a lo extraido crear!
        for (MenuItem menu : SinamoFactory.builMenu(lsmenus)) {
            cacheService.getCache(CacheService.CACHE_MENUNAME).put(menu.getId(), menu);
        }

        String nashronScript = "";

        for (TbModulo _module : vwmodule) {
            try {
                //leyendo el json
                JSONObject json = parseTextToJson(_module.getScript());

                com.sinamo.mods.Module _mod = new com.sinamo.mods.Module();
                //Head
                _mod.setHead(new com.sinamo.mods.Head(((JSONObject) json.get("head")).get("title").toString()));

                //Actions
                Map<Integer, com.sinamo.mods.Action> _actions = new HashMap<>();
                Iterator it = ((JSONArray) json.get("actions")).iterator();
                while (it.hasNext()) {

                    JSONObject _act = (JSONObject) it.next();
                    if (_act.get("type").toString().contentEquals("button")) {
                        com.sinamo.mods.ButtonAction _btnact = new ButtonAction();
                        _btnact.setId(Integer.parseInt(_act.get("id").toString()));
                        _btnact.setType(_act.get("type").toString());
                        _btnact.setTitle(_act.get("title").toString());
                        _btnact.setFunc(_act.get("func").toString());
                        _actions.put(_btnact.getId(), _btnact);
                    } else if (_act.get("type").toString().contentEquals("link")) {
                        com.sinamo.mods.LinkAction _btnact = new LinkAction();
                        _btnact.setId(Integer.parseInt(_act.get("id").toString()));
                        _btnact.setType(_act.get("type").toString());
                        _btnact.setTitle(_act.get("title").toString());
                        _btnact.setUrl(_act.get("url").toString());
                        _actions.put(_btnact.getId(), _btnact);
                    } else if (_act.get("type").toString().contentEquals("linkbutton")) {
                        com.sinamo.mods.LinkAction _btnact = new LinkAction();
                        _btnact.setId(Integer.parseInt(_act.get("id").toString()));
                        _btnact.setType(_act.get("type").toString());
                        _btnact.setTitle(_act.get("title").toString());
                        _btnact.setUrl(_act.get("url").toString());
                        _actions.put(_btnact.getId(), _btnact);
                    }
                }
                _mod.setActions(_actions);

                //Content
                com.sinamo.mods.Content _content = new com.sinamo.mods.Content();
                _content.setType(((JSONObject) json.get("content")).get("type").toString());
                _content.setState(((JSONObject) json.get("content")).get("state").toString());

                if (_content.getType().contentEquals("list") & _content.getState().contentEquals("dynamic")) {
                    DynamicListForm dynalistForm = new DynamicListForm();

                    JSONObject _list = (JSONObject) ((JSONObject) ((JSONObject) json.get("content")).get("form")).get("list");

                    DefaultList defautList = new DefaultList();
                    defautList.setTitle(_list.get("title").toString());
                    defautList.setStitle(_list.get("stitle").toString());
                    defautList.setStatus(_list.get("status").toString());
                    defautList.setIcon(_list.get("icon").toString());
                    defautList.setValue(_list.get("value").toString());
                    defautList.setActionId(Integer.parseInt(_list.get("actionId").toString()));

                    dynalistForm.setBaseList(defautList);

                    _content.setForm(dynalistForm);
                } else if (_content.getType().contentEquals("sectionform")) {
                    SectionForm sectionForm = new SectionForm();
                    Iterator _sections = ((JSONArray) ((JSONObject) ((JSONObject) json.get("content")).get("form")).get("sections")).iterator();
                    while (_sections.hasNext()) {
                        DefaultSection defaultSection = new DefaultSection();
                        JSONObject _section = (JSONObject) _sections.next();
                        defaultSection.setTitle(_section.get("title").toString());
                        Iterator _registers = ((JSONArray) _section.get("registers")).iterator();
                        while (_registers.hasNext()) {
                            JSONObject _register = (JSONObject) _registers.next();
                            String _typeRegister = _register.get("type").toString();
                            switch (_typeRegister) {
                                case "input": {
                                    InputRegister register = new InputRegister();
                                    register.setTitle(_register.get("title").toString());
                                    register.setType(_register.get("type").toString());
                                    register.setValue(_register.get("value").toString());
                                    register.setName(_register.get("name").toString());
                                    defaultSection.addRegister(register);
                                    break;
                                }
                                case "hidden": {
                                    HiddenRegister register = new HiddenRegister();
                                    register.setType(_register.get("type").toString());
                                    register.setValue(_register.get("value").toString());
                                    register.setName(_register.get("name").toString());
                                    defaultSection.addRegister(register);
                                    break;
                                }
                                case "boolean": {
                                    BooleanRegister register = new BooleanRegister();
                                    register.setTitle(_register.get("title").toString());
                                    register.setType(_register.get("type").toString());
                                    //!-- falta
                                    register.setValue(false);
                                    register.setName(_register.get("name").toString());
                                    defaultSection.addRegister(register);
                                    break;
                                }
                                case "combo": {
                                    ComboRegister register = new ComboRegister();
                                    register.setTitle(_register.get("title").toString());
                                    register.setType(_register.get("type").toString());
                                    //!-- falta
                                    register.setValues(new ArrayList<>());
                                    register.setName(_register.get("name").toString());
                                    defaultSection.addRegister(register);
                                    break;
                                }
                                default:
                                    break;
                            }
                        }

                        sectionForm.addSection(defaultSection);
                    }
                    _content.setForm(sectionForm);
                }
                _mod.setContent(_content);

                ////////////////////////////////////////////////////////////////////
//            Script script = new Gson().fromJson(_module.getScript(), Script.class);
//            Module module = SinamoFactory.buildModule(script);
//            module.setId(_module.getId());
                //Script de creacion
                String dataScriptName = "_func" + _module.getId();
                nashronScript = nashronScript + " function " + dataScriptName + "(_requestStringValue){\n"
                        + "var responsedatamap = new java.util.HashMap();\n"
                        + "var request = JSON.parse(_requestStringValue);\n"
                        + (_module.getData() == null ? "" : _module.getData())
                        + "return responsedatamap;\n"
                        + "}\n\n";
                _mod.setDataScriptName(dataScriptName);

                //Script de accion
                for (TbFunction function : _module.getFunctions()) {
                    nashronScript = nashronScript + " function _act" + function.getId() + "(_requestStringValue, transaId){\n"
                            + "var request = JSON.parse(_requestStringValue);\n"
                            + "var returnUrl = '';\n"
                            + (function.getFunction() == null ? "" : function.getFunction()) + "\n"
                            + "return returnUrl;\n"
                            + "}\n\n";

                }

//            for (Action action : module.getActions()) {
//                action.setDoit("execute(sJS.build(this,'" + action.getId() + "','_act" + _module.getId() + "'))");
//            }
                System.out.println("_module.getId()==>" + _module.getId());
                cacheService.getCache(CacheService.CACHE_MODULONAME).put(_module.getId(), _mod);
                System.out.println("modulo(" + _module.getId() + ") = " + _mod.getHead().getTitle());

            } catch (Exception ep) {
                //!-- cambiar: error de no poder crear el modulo
            }

        }

        jsService.setScriptFromDBs(nashronScript);
        jsService.restart();
    }

    public JSONObject parseTextToJson(String jsonScript) {
        JSONParser parser = new JSONParser();
        Object json = null;
        try {
            json = parser.parse(jsonScript);
        } catch (Exception ep) {
        }
        return json != null ? (JSONObject) json : null;
    }

    public static void main(String[] args) {
//        String jsonscript = "{\n"
//                + "	\"head\": {\n"
//                + "		\"title\": \"Manejador de usuarios\"\n"
//                + "	},\n"
//                + "	\"content\": {\n"
//                + "		\"type\": \"list\",\n"
//                + "		\"state\": \"dynamic\",\n"
//                + "		\"form\": {\n"
//                + "			\"list\": {\n"
//                + "				\"icon\": \"casa.png\",\n"
//                + "				\"title\": \"Romulo Galindo\",\n"
//                + "				\"stitle\": \"romulogalindo@gmail.com\",\n"
//                + "				\"status\": \"Manager\",\n"
//                + "				\"value\": 4,\n"
//                + "				\"actionId\": 1\n"
//                + "			}\n"
//                + "		}\n"
//                + "	},\n"
//                + "	\"actions\": [{\n"
//                + "		\"id\": 1,\n"
//                + "		\"type\": \"button\",\n"
//                + "		\"title\": \"Nuevo usuario\",\n"
//                + "		\"func\": \"functionzx\"\n"
//                + "	}, {\n"
//                + "		\"id\": 2,\n"
//                + "		\"type\": \"link\",\n"
//                + "		\"title\": \"\",\n"
//                + "		\"url\": \"snm?ra=2&p1=${value}\"\n"
//                + "	}]\n"
//                + "}";
//        Engine e = new Engine();
//        JSONObject json = e.parseTextToJson(jsonscript);
//        ((JSONObject) json.get("head")).get("title");
//        System.out.println("json = " + ((JSONObject) json.get("head")).get("title").toString());
        String h = "snm?ra=2&p1=${value}";
        h = h.replaceAll(Pattern.quote("${value}"), "juan");
        System.out.println("h = " + h);
    }
}
