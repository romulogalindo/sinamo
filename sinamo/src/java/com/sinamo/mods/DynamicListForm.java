package com.sinamo.mods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 27, 2017 7:25:42 PM
 */
public class DynamicListForm extends Form implements Serializable, Cloneable {

    List<DefaultList> list;
    DefaultList baseList;

    public DynamicListForm() {
        list = null;
        baseList = null;
    }

    public List<DefaultList> getList() {
        return list;
    }

    public void setList(List<DefaultList> list) {
        this.list = list;
    }

    public DefaultList getBaseList() {
        return baseList;
    }

    public void setBaseList(DefaultList baseList) {
        this.baseList = baseList;
    }

    public void addList(DefaultList ls) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(ls);
    }

    @Override
    public String toString() {
        return "DynamicListForm{" + "list=" + list + '}';
    }

    @Override
    public void draw(HashMap dataMap, Map<Integer, Action> actions) {
        if (baseList != null) {
//            list = null;
            //Obtenemos el Key
            String keyDataMap = baseList.getTitle().split("\\$")[1];

            //obtenemos los keyElements
            String _titleKey = baseList.getTitle().split("\\$")[2].replace("{", "").replace("}", "");
            String _title2Key = baseList.getStitle().split("\\$")[2].replace("{", "").replace("}", "");
            String _title3Key = baseList.getStatus().split("\\$")[2].replace("{", "").replace("}", "");
            String _iconKey = baseList.getIcon().split("\\$")[2].replace("{", "").replace("}", "");
            String _valueKey = baseList.getValue().split("\\$")[2].replace("{", "").replace("}", "");
            String _actionKey = "";

            if (baseList.getActionId() != null) {
//                Action action = actions.get(baseList.getActionId());
                System.out.println("actions = " + actions);
                System.out.println("baseList.getActionId() = " + baseList.getActionId());
                System.out.println("baseList.getActionId() = " + actions.get(baseList.getActionId()));
                System.out.println("baseList.getActionId() = " + actions.get(baseList.getActionId()).getType());
                System.out.println("baseList.getActionId() = " + (actions.get(baseList.getActionId()).getType().contentEquals("link")));
                if (actions.get(baseList.getActionId()).getType().contentEquals("link")) {
                    LinkAction action = (LinkAction) actions.get(baseList.getActionId());
                    _actionKey = action.getUrl();
                    System.out.println("_actionKey = " + _actionKey);
                }
            }

            //Se obtiene la data
            List<HashMap> ls = (List<HashMap>) dataMap.get(keyDataMap);
            for (HashMap map : ls) {
                DefaultList _listItem = new DefaultList();

                System.out.println("[" + _titleKey + ":" + map.get(_titleKey) + "]");
                _listItem.setTitle(map.get(_titleKey) != null ? map.get(_titleKey).toString() : "");
                _listItem.setStitle(map.get(_title2Key) != null ? map.get(_title2Key).toString() : "");
                _listItem.setStatus(map.get(_title3Key) != null ? map.get(_title3Key).toString() : "");
                _listItem.setIcon(map.get(_iconKey) != null ? map.get(_iconKey).toString() : "");
                _listItem.setValue(map.get(_valueKey) != null ? map.get(_valueKey).toString() : "");
                _listItem.setActionId(Integer.SIZE);
                //.replaceAll("\\${value}", _valueKey)
                _listItem.setAction(_actionKey.replaceAll(Pattern.quote("${value}"), _listItem.getValue()));
                System.out.println("_listItem =>>> " + _listItem.getAction());

                addList(_listItem);
            }
        }
        System.out.println("list = " + list);
    }

    @Override
    public Form clone() throws CloneNotSupportedException {
        DynamicListForm formClone = (DynamicListForm) super.clone();
        return formClone;
    }
}
