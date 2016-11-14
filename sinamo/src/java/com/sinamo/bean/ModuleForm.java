package com.sinamo.bean;

import com.sinamo.bean.items.Section;
import com.sinamo.bean.items.SectionForm;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulogalindo
 */
public class ModuleForm extends Module {

    List<SectionForm> sectionForm;

    public ModuleForm() {
        this.sectionForm = new ArrayList<>();
        this.type = "form";
    }

    @Override
    public void addSection(Section section) {
        this.sectionForm.add((SectionForm) section);
    }

    @Override
    public List<SectionForm> getSections() {
        return this.sectionForm;
    }

    @Override
    public void setSections(List sections) {
        sectionForm = sections;
    }

}
