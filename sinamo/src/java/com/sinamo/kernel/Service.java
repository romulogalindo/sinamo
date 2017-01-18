package com.sinamo.kernel;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 10, 2017 9:44:46 PM
 */
public abstract class Service {

    public String name;
    public String description;
    public Object defaultConfigurator;

    public abstract void putConfiguration(Object config);

    public abstract void start();

    public abstract void stop();
}
