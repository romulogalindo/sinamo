package com.sinamo.log;

import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

/**
 *
 * @author romulogalindo
 */
public class Log {

    public static int NONE = 0;
    public static int INFO = 1;
    public static int ERROR = 2;
    public static int WARN = 3;
    public static int TRACE = 4;
    public static int FATAL = 5;
    public static int DEBUG = 6;
    public static int ALL = 7;

    static private Logger log;

    public Log() {
    }

    static {
        try {
//            Configuration config = XmlConfigurationFactory.getInstance().getConfiguration(new ConfigurationSource(getClass().getResourceAsStream("/Log4j2.xml")));//modo servidor
            Mylog mylog = new Mylog();
            LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
            Configuration config = XmlConfigurationFactory.getInstance().getConfiguration(ctx, mylog.getConfigSource());//modo servidor
            ctx.stop();
            ctx.start(config);
//            ctx.start();
//            ctx.start(new Mylog().getConfig());

            log = ctx.getLogger("LOG1");
        } catch (Throwable ep) {
            System.out.println("Error:" + ep.getMessage());
        }

    }

    public static void setLoglevel(int level) {
        switch (level) {
            case 0:
                log.setLevel(Level.OFF);
                break;
            case 1:
                log.setLevel(Level.INFO);
                break;
            case 2:
                log.setLevel(Level.ERROR);
                break;
            case 3:
                log.setLevel(Level.WARN);
                break;
            case 4:
                log.setLevel(Level.TRACE);
                break;
            case 5:
                log.setLevel(Level.FATAL);
                break;
            case 6:
                log.setLevel(Level.DEBUG);
                break;
            case 7:
                log.setLevel(Level.ALL);
                break;
        }
    }

    public static void log(Object l) {
        log.info(l);
    }

    public static void debug(Object l) {
        log.debug(l);
    }

    public static void info(Object l) {
        log.info(l);
    }

    public static void error(Object l, Exception ex) {
        log.error(l, ex);
    }

    public static void error(Object l) {
        log.error(l);
    }

    public static void log(Object l, int level) {
        switch (level) {
            case 0:
                log.info(l);
                break;
            case 1:
                log.info(l);
                break;
            case 2:
                log.error(l);
                break;
            case 3:
                log.warn(l);
                break;
            case 4:
                log.trace(l);
                break;
            case 5:
                log.fatal(l);
                break;
            case 6:
                log.debug(l);
                break;
        }
    }

    static class Mylog {

        ConfigurationSource configSource;

        public Mylog() throws IOException {
            configSource = new ConfigurationSource(getClass().getResourceAsStream("/Log4j2.xml"));
        }

        public ConfigurationSource getConfigSource() {
            return configSource;
        }

        public void setConfigSource(ConfigurationSource configSource) {
            this.configSource = configSource;
        }

    }
}
