package com.sinamo.kernel;

import com.sinamo.bean.Module;
import com.sinamo.bean.SimpleModule;
import com.sinamo.bean.items.Register;
import com.sinamo.sys.db.Native_PLSQL;
import com.sinamo.sys.json.Action;
import com.sinamo.sys.json.Row;
import com.sinamo.sys.json.Script;
import com.sinamo.sys.json.Section;
import java.util.ArrayList;
import java.util.List;

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
        List<com.sinamo.bean.items.Section> sections = new ArrayList<>();
        for (Section section : script.getSections()) {
            com.sinamo.bean.items.Section _section = new com.sinamo.bean.items.Section();
            _section.setTitle(section.getTitle());

            List<Register> _registers = new ArrayList<>();
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

}
