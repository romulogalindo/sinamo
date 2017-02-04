package com.sinamo.dto;

import com.sinamo.sys.db.Native;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 3, 2017 3:53:08 PM
 */
@NamedNativeQueries({
    @NamedNativeQuery(
            name = Native.TBFUNCTION_ALL,
            query = "select * from system.tbfunctionmodulo",
            resultClass = TbFunction.class)
    ,
    @NamedNativeQuery(
            name = Native.TBFUNCTION_BYID,
            query = "select * from system.tbfunctionmodulo where _idmodulo = :_id",
            resultClass = TbFunction.class)
})
@Entity
public class TbFunction implements Serializable {

    @Id
    int _id;
    int _idmodulo;
    String function;

    public TbFunction() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public int getIdmodulo() {
        return _idmodulo;
    }

    public void setIdmodulo(int _idmodulo) {
        this._idmodulo = _idmodulo;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

}
