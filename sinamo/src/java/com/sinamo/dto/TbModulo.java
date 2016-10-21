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
            name = Native.TBMODULO_ALL,
            query = "select * from system.tbmodulo",
            resultClass = TbModulo.class)
})
@Entity
public class TbModulo implements Serializable {

    @Id
    int _id;
    String name;
    String script;

    public TbModulo() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    @Override
    public String toString() {
        return "TbModulo{" + "_id=" + _id + ", name=" + name + ", script=" + script + '}';
    }

}
