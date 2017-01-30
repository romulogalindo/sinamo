package com.sinamo.kernel;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 11, 2017 10:47:13 AM
 */
public abstract class Source {

    public abstract String getSourceName();

    public abstract boolean connect();

    public abstract void disconnect();
}
