package com.sinamo.dto;

import com.sinamo.sys.db.Native;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 * TbModulo
 *
 * @author Romulo Galindo Tanta
 * @create Oct 21, 2016 13:22:40 PM
 */
@NamedNativeQueries({
    @NamedNativeQuery(
            name = Native.VWMENUMODULO_ALL,
            query = "select * from system.vw_menumodulo",
            resultClass = VwMenuModulo.class)
})
@Entity
public class VwMenuModulo implements Serializable {

    @Id
    int _id;
    int _idmenu;
    String _menu_name;
    String _menu_title;
    int _idmodulo;
    String _modulo_name;
    String _modulo_title;

    public VwMenuModulo() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getIdmenu() {
        return _idmenu;
    }

    public void setIdmenu(int _idmenu) {
        this._idmenu = _idmenu;
    }

    public String getMenu_name() {
        return _menu_name;
    }

    public void setMenu_name(String _menu_name) {
        this._menu_name = _menu_name;
    }

    public String getMenu_title() {
        return _menu_title;
    }

    public void setMenu_title(String _menu_title) {
        this._menu_title = _menu_title;
    }

    public int getIdmodulo() {
        return _idmodulo;
    }

    public void setIdmodulo(int _idmodulo) {
        this._idmodulo = _idmodulo;
    }

    public String getModulo_name() {
        return _modulo_name;
    }

    public void setModulo_name(String _modulo_name) {
        this._modulo_name = _modulo_name;
    }

    public String getModulo_title() {
        return _modulo_title;
    }

    public void setModulo_title(String _modulo_title) {
        this._modulo_title = _modulo_title;
    }

    @Override
    public String toString() {
        return "VwMenuModulo{" + "_id=" + _id + ", _idmenu=" + _idmenu + ", _menu_name=" + _menu_name + ", _menu_title=" + _menu_title + ", _idmodulo=" + _idmodulo + ", _modulo_name=" + _modulo_name + ", _modulo_title=" + _modulo_title + '}';
    }

}
