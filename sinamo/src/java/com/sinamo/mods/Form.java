package com.sinamo.mods;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 27, 2017 7:25:16 PM
 */
public abstract class Form {

    public abstract void draw(HashMap dataMap, Map<Integer, Action> actions);
}
