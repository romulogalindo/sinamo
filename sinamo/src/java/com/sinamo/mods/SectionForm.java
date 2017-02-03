package com.sinamo.mods;

import com.sinamo.log.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 3, 2017 12:03:39 AM
 */
public class SectionForm extends Form {

    List<DefaultSection> sections;

    public List<DefaultSection> getSections() {
        return sections;
    }

    public void setSections(List<DefaultSection> sections) {
        this.sections = sections;
    }

    public String getValue(HashMap dataMap, String val) {
        String dmap = val.split("\\$")[1];
        String vmap = val.split("\\$")[2].replaceAll("\\{", "").replaceAll("\\}", "");
        HashMap<String, Object> data = ((List<HashMap>) dataMap.get(dmap)).get(0);
        return "" + data.get(vmap);
    }

    public void addSection(DefaultSection section) {
        if (sections == null) {
            sections = new ArrayList<>();
        }
        sections.add(section);
    }

    @Override
    public void draw(HashMap dataMap, Map<Integer, Action> actions) {
        System.out.println("sections=" + sections);
        if (sections != null) {
            for (DefaultSection section : sections) {
                System.out.println("section = " + section);
                for (Register register : section.getRegisters()) {
                    register.setValue(getValue(dataMap, register.getValue()));
                }
            }
        }
    }

}
