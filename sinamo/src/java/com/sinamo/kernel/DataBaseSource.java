package com.sinamo.kernel;

import com.sinamo.log.Log;
import com.sinamo.units.SinamoDriverDBUnit;
import com.sinamo.units.SourceXMLUnit;
import com.sinamo.util.DBUnitUtil;
import java.util.List;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.service.ServiceRegistry;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 11, 2017 10:49:34 AM
 */
public class DataBaseSource extends Source implements DataBase {

    private SourceXMLUnit sourceXmlUnit;
    private SessionFactory sessionFactory;

    public DataBaseSource(SourceXMLUnit sourceXmlUnit) {
        this.sourceXmlUnit = sourceXmlUnit;
        
        //!--Mejorar!!
        _log = "[" + getClass().getSimpleName() + ":" + sourceXmlUnit.getDbname() + "@" + sourceXmlUnit.getServer() + "]";
    }

    @Override
    public String getSourceName() {
        return sourceXmlUnit.getDbname() + "@" + sourceXmlUnit.getServer();
    }

    @Override
    public boolean connect() {
        //Se conecta a la DB!sample todo con hibernate
        Log.debug(_log + " @type = " + this.sourceXmlUnit.getEngine());
        SinamoDriverDBUnit sinamoDriverDbUnit = DBUnitUtil.getSinamoDriverDbUnit(sourceXmlUnit.getEngine());
        Log.debug(_log + " @info = " + sinamoDriverDbUnit);
        Configuration config = new Configuration();

        config.setProperty("hibernate.dialect", sinamoDriverDbUnit.getDialect());
        config.setProperty("hibernate.connection.driver_class", sinamoDriverDbUnit.getDrivername());
        config.setProperty("hibernate.connection.url", "jdbc:" + sinamoDriverDbUnit.getName() + "://" + sourceXmlUnit.getServer() + ":" + sourceXmlUnit.getPort() + "/" + sourceXmlUnit.getDbname());
        config.setProperty("hibernate.connection.username", sourceXmlUnit.getUser());
        config.setProperty("hibernate.connection.password", sourceXmlUnit.getPassword());
        config.setProperty("hibernate.current_session_context_class", "thread");

        config.setProperty("hibernate.query.factory_class", "org.hibernate.hql.internal.classic.ClassicQueryTranslatorFactory");

        config.setProperty("hibernate.connection.autocommit", "false");
        config.setProperty("hibernate.cache.use_second_level_cache", "false");
        config.setProperty("hibernate.cache.use_query_cache", "false");
        /*Disable the second-level cache*/
        config.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");
        /*Echo all executed SQL to stdout*/
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.use_sql_comments", "true");

        config.setProperty("hibernate.hbm2ddl.auto", "update");

        config.setProperty("hibernate.dbcp.initialSize", "5");
        config.setProperty("hibernate.dbcp.maxTotal", "20");
        config.setProperty("hibernate.dbcp.maxIdle", "10");
        config.setProperty("hibernate.dbcp.minIdle", "5");
        config.setProperty("hibernate.dbcp.maxWaitMillis", "-1");

        config.addAnnotatedClass(com.sinamo.dto.TbModulo.class);
        config.addAnnotatedClass(com.sinamo.dto.TbMenu.class);
        config.addAnnotatedClass(com.sinamo.dto.VwMenuModulo.class);

        Properties prop = config.getProperties();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(prop);
        ServiceRegistry registry = builder.build();

        sessionFactory = config.buildSessionFactory(registry);

        //Testing !-- cambiar el como se testea
        StatelessSession statelessSession = sessionFactory.openStatelessSession();
        NativeQuery nativeQuery = statelessSession.createNativeQuery("select 1;");

        List<Object[]> queryReturn = nativeQuery.list();
        statelessSession.close();

        for (Object rw : queryReturn) {
            System.out.println("r = " + rw);
        }
//        Log.log("queryReturn=" + queryReturn.get(0));

        return (queryReturn.get(0) + "").contentEquals("1");
    }

    @Override
    public void disconnect() {
        try {
            Log.debug("[disconnect] " + this.sourceXmlUnit.getServer());
            sessionFactory.close();
            sessionFactory = null;
        } catch (Exception ep) {
            Log.error(ep, ep);
        }
    }

    @Override
    public StatelessSession getSession() {
        return sessionFactory.openStatelessSession();
    }

    //Log-static !--cambiar
    String _log = "";

}
