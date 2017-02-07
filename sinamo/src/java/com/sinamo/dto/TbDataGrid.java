package com.sinamo.dto;

import com.sinamo.sys.db.Native;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

/**
 * @author Romulo Galindo Tanta
 * @create Feb 5, 2017 7:19:28 PM
 */
@NamedNativeQueries({
    @NamedNativeQuery(
            name = Native.TBDATAGRID_ALL,
            query = "SELECT * FROM system.tbdatagrid order by 1,3 desc",
            resultClass = TbDataGrid.class)
    ,
    @NamedNativeQuery(
            name = Native.TBDATAGRID_BYID,
            query = "SELECT * FROM system.tbdatagrid where _id = :_id",
            resultClass = TbDataGrid.class)
})
@Entity
public class TbDataGrid implements Serializable {

    @Id
    int _id;
    String query;
    @Column(name = "static")
    boolean estatico;
    @Transient
    List<DataGrid> datagrids;

    public TbDataGrid() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public boolean isEstatico() {
        return estatico;
    }

    public void setEstatico(boolean estatico) {
        this.estatico = estatico;
    }

    public List<DataGrid> getDatagrids() {
        return datagrids;
    }

    public void setDatagrids(List<DataGrid> datagrids) {
        this.datagrids = datagrids;
    }

}
