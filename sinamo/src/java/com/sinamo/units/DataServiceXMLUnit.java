package com.sinamo.units;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 12, 2017 4:47:52 PM
 */
public class DataServiceXMLUnit {

    List<SourceXMLUnit> syssource;

    List<SourceXMLUnit> sources;

    public List<SourceXMLUnit> getSyssource() {
        return syssource;
    }

    public void setSyssource(List<SourceXMLUnit> syssource) {
        this.syssource = syssource;
    }

    public List<SourceXMLUnit> getSources() {
        return sources;
    }

    public void setSources(List<SourceXMLUnit> sources) {
        this.sources = sources;
    }

    @Override
    public String toString() {
        return "DataServiceXMLUnit{" + "syssource=" + syssource + ", sources=" + sources + '}';
    }

}
