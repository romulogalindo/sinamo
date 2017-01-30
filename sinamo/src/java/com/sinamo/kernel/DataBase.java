package com.sinamo.kernel;

import org.hibernate.StatelessSession;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 11, 2017 10:47:51 AM
 */
public interface DataBase {

    public abstract StatelessSession getSession();
}
