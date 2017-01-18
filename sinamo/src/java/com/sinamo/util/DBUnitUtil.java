package com.sinamo.util;

import com.sinamo.units.SinamoDriverDBUnit;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 13, 2017 12:53:55 PM
 */
public class DBUnitUtil {

    public static Map<String, SinamoDriverDBUnit> sinamoDriverDbUnits;

    static {
        sinamoDriverDbUnits = new HashMap<>();
        sinamoDriverDbUnits.put("postgresql", new SinamoDriverDBUnit("postgresql", "org.postgresql.Driver", "org.hibernate.dialect.PostgreSQL9Dialect"));
        sinamoDriverDbUnits.put("mysql", new SinamoDriverDBUnit("mysql", "com.mysql.jdbc.Driver", "org.hibernate.dialect.MySQLDialect"));
    }

    public static SinamoDriverDBUnit getSinamoDriverDbUnit(String sinamoDbName) {
        return sinamoDriverDbUnits.get(sinamoDbName);
    }
}
