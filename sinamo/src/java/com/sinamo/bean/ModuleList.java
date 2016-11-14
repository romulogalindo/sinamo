package com.sinamo.bean;

import com.sinamo.bean.items.Section;
import com.sinamo.bean.items.SectionList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulogalindo
 */
public class ModuleList extends Module {

    List<SectionList> sectionlist;

    public ModuleList() {
        this.sectionlist = new ArrayList<>();
        this.type = "list";
    }

    @Override
    public void addSection(Section section) {
        this.sectionlist.add((SectionList) section);
    }

    @Override
    public List<SectionList> getSections() {
        return this.sectionlist;
    }

    @Override
    public void setSections(List sections) {
        sectionlist = sections;
    }

}
