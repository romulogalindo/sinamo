package com.sinamo.mods;

import com.sinamo.dto.DataGrid;
import com.sinamo.dto.TbDataGrid;
import com.sinamo.kernel.CacheService;
import com.sinamo.kernel.Sinamo;
import com.sinamo.log.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.StatelessSession;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 3, 2017 12:03:39 AM
 */
public class SectionForm extends Form implements Cloneable {

    List<DefaultSection> sections;

    public SectionForm() {
        sections = null;
    }

    public List<DefaultSection> getSections() {
        return sections;
    }

    public void setSections(List<DefaultSection> sections) {
        this.sections = sections;
    }

    public String getValue(HashMap dataMap, String val) {
        Log.debug("Evaluando!!!--->" + val);
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
        if (sections != null) {
            for (DefaultSection section : sections) {
                for (Register register : section.getRegisters()) {
                    if (register.getType().contentEquals("input") | register.getType().contentEquals("text")) {
                        ((InputRegister) register).setValue(getValue(dataMap, ((InputRegister) register).getValue()));
                    } else if (register.getType().contentEquals("hidden")) {
                        ((HiddenRegister) register).setValue(getValue(dataMap, ((HiddenRegister) register).getValue()));
                    } else if (register.getType().contentEquals("combo")) {
                        ComboRegister comboRegister = (ComboRegister) register;
                        ((ComboRegister) register).setValue(getValue(dataMap, ((ComboRegister) register).getValue()));

                        TbDataGrid tbDataGrid = (TbDataGrid) Sinamo.getApplication("Sinamo").getCacheService().getCache(CacheService.CACHE_DATAGRID).get(Integer.parseInt(comboRegister.getData()));
                        if (tbDataGrid.isEstatico()) {
                            //conseguir de este lado los elementos
                            ((ComboRegister) register).setValues(tbDataGrid.getDatagrids());
                        } else {
                            //hacer un select
                            StatelessSession ss = Sinamo.getApplication("Sinamo").getDataService().getDataBaseSources().get("_native").getSession();
                            ((ComboRegister) register).setValues(ss.createNativeQuery(tbDataGrid.getQuery()).addEntity(DataGrid.class).list());
                            ss.close();
                        }
                    }
                }
            }
        }
    }

    @Override
    public SectionForm clone() throws CloneNotSupportedException {
        SectionForm formClone = (SectionForm) super.clone();
//        getSections().forEach(f -> {
//            try {
//                formClone.addSection(f.clone());
//            } catch (Exception ex) {
//                
//            }
//        });
        return formClone;
    }

}
