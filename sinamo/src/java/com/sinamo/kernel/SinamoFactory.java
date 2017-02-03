package com.sinamo.kernel;

import com.sinamo.bean.Module;
import com.sinamo.bean.ModuleForm;
import com.sinamo.bean.ModuleList;
import com.sinamo.bean.items.ListItem;
import com.sinamo.bean.items.MenuItem;
import com.sinamo.bean.items.ModuleItem;
import com.sinamo.bean.items.Register;
import com.sinamo.dto.VwMenuModulo;
import com.sinamo.sys.db.Native_PLSQL;
import com.sinamo.sys.json.Action;
import com.sinamo.sys.json.Row;
import com.sinamo.sys.json.Script;
import com.sinamo.sys.json.Section;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.StatelessSession;

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
        Module module = null;
        System.out.println("script.getHead().getType() = " + script.getHead().getType());
        switch (script.getHead().getType()) {
            case "list": {
                module = buildModuleList(script);
                break;
            }
            case "form": {
                module = buildModuleForm(script);
                break;
            }
            case "table": {
                module = buildModuleTable(script);
                break;
            }
        }
        return module;
    }

    public static Module buildModuleList(Script script) {
        ModuleList module = new ModuleList();
        module.setTitle(script.getHead().getTitle());
        //Ejecuta pre-buil-data

        List<com.sinamo.bean.items.SectionList> sections = new ArrayList<>();
        for (Section section : script.getSections()) {
            com.sinamo.bean.items.SectionList _section = new com.sinamo.bean.items.SectionList();
            _section.setTitle(section.getTitle());

            ListItem listItem = new ListItem();
            listItem.setTitle(section.getItem().getTitle());
            listItem.setTitle2(section.getItem().getTitle2());
            listItem.setTitle3(section.getItem().getTitle3());
            listItem.setIcon(section.getItem().getIcon());
            listItem.setValue(section.getItem().getValue());

            List<ListItem> _registers = new ArrayList<>();
//            _registers.add(listItem);

            //Agregando los registros a la section
            _section.setRegisters(_registers);
            _section.setListItem(listItem);
            //agregando la section a la lista de sections
            sections.add(_section);
        }
        module.setSections(sections);

        List<com.sinamo.bean.items.Action> actions = new ArrayList<>();
        for (Action action : script.getActions()) {
            com.sinamo.bean.items.Action _action = new com.sinamo.bean.items.Action();
            _action.setDoit(action.getDoit());
            _action.setId(action.getId());
            _action.setTitle(action.getTitle());
            actions.add(_action);
        }
        module.setActions(actions);

        return module;
    }

    public static Module buildModuleForm(Script script) {
        ModuleForm module = new ModuleForm();
        module.setTitle(script.getHead().getTitle());
        //Ejecuta pre-buil-data

        List<com.sinamo.bean.items.SectionList> sections = new ArrayList<>();
        for (Section section : script.getSections()) {
            com.sinamo.bean.items.SectionList _section = new com.sinamo.bean.items.SectionList();
            _section.setTitle(section.getTitle());

//            ListItem listItem = new ListItem();
//            listItem.setTitle(section.getItem().getTitle());
//            listItem.setTitle2(section.getItem().getTitle2());
//            listItem.setTitle3(section.getItem().getTitle3());
//            listItem.setIcon(section.getItem().getIcon());
//            listItem.setValue(section.getItem().getValue());
            List<Register> _registers = new ArrayList<>();
//            _registers.add(listItem);
            for (Row row : section.getRows()) {
                Register register = new Register();
                register.setTitle(row.getTitle());
                register.setType(row.getType());
                register.setValue(row.getValue());
                _registers.add(register);
            }

            //Agregando los registros a la section
            _section.setRegisters(_registers);

            //agregando la section a la lista de sections
            sections.add(_section);
        }
        module.setSections(sections);

        List<com.sinamo.bean.items.Action> actions = new ArrayList<>();
        for (Action action : script.getActions()) {
            com.sinamo.bean.items.Action _action = new com.sinamo.bean.items.Action();
            _action.setDoit(action.getDoit());
            _action.setId(action.getId());
            _action.setTitle(action.getTitle());
            actions.add(_action);
        }
        module.setActions(actions);

        return module;
    }

    public static Module buildModuleTable(Script script) {
        ModuleList module = new ModuleList();
        module.setTitle(script.getHead().getTitle());
        //Ejecuta pre-buil-data

        List<com.sinamo.bean.items.SectionList> sections = new ArrayList<>();
        for (Section section : script.getSections()) {
            com.sinamo.bean.items.SectionList _section = new com.sinamo.bean.items.SectionList();
            _section.setTitle(section.getTitle());

//            ListItem listItem = new ListItem();
//            listItem.setTitle(section.getItem().getTitle());
//            listItem.setTitle2(section.getItem().getTitle2());
//            listItem.setTitle3(section.getItem().getTitle3());
//            listItem.setIcon(section.getItem().getIcon());
//            listItem.setValue(section.getItem().getValue());
            List<Register> _registers = new ArrayList<>();
//            _registers.add(listItem);
            for (Row row : section.getRows()) {
                Register register = new Register();
                register.setTitle(row.getTitle());
                register.setType(row.getType());
                register.setValue(row.getValue());
                _registers.add(register);
            }

            //Agregando los registros a la section
            _section.setRegisters(_registers);

            //agregando la section a la lista de sections
            sections.add(_section);
        }
        module.setSections(sections);

        List<com.sinamo.bean.items.Action> actions = new ArrayList<>();
        for (Action action : script.getActions()) {
            com.sinamo.bean.items.Action _action = new com.sinamo.bean.items.Action();
            _action.setDoit(action.getDoit());
            _action.setId(action.getId());
            _action.setTitle(action.getTitle());
            actions.add(_action);
        }
        module.setActions(actions);

        return module;
    }

    public static List<MenuItem> builMenu(List<VwMenuModulo> vwmenumodulos) {
        Map<String, MenuItem> menuitems = new HashMap<>();

        for (VwMenuModulo vwmenumodulo : vwmenumodulos) {
            MenuItem menuitem = menuitems.get(vwmenumodulo.getMenu_name());
            if (menuitem == null) {
                menuitem = new MenuItem();
                menuitem.setId(vwmenumodulo.getId());
                menuitem.setTitle(vwmenumodulo.getMenu_title());
                menuitems.put(vwmenumodulo.getMenu_name(), menuitem);
            }

            ModuleItem moduleitem = new ModuleItem();
            moduleitem.setId("" + vwmenumodulo.getIdmodulo());
            moduleitem.setTitle(vwmenumodulo.getModulo_title());
            menuitem.addModuleItem(moduleitem);

            menuitems.put(vwmenumodulo.getMenu_name(), menuitem);
        }

        return menuitems.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
    }

    public static List<HashMap> SQL(String connectorName, String query) {
        StatelessSession session = null;
        List result = null;
        try {
            session = simanoEngine.connectors.get(connectorName).getSessionFactory().openStatelessSession();
            SQLQuery q = session.createNativeQuery(query);
            q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
            result = q.list();
            for (Object obj : result) {
                System.out.println("obj = " + obj.getClass() + "(" + obj + ")");
            }
        } catch (Exception ep) {
            ep.printStackTrace();
        }
        try {
            session.close();
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        return result;
    }

    public static String readFile(File file) {
        String contenFile;
        try {
            contenFile = new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ep) {
            ep.printStackTrace();
            contenFile = "";
        }
        return contenFile;
    }

    public static void LOG(String log) {
        System.out.println(log);
    }
}
