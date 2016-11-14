package com.sinamo.bean.items;

import java.io.Serializable;

/**
 *
 * @author romulogalindo
 */
public abstract class Response implements Serializable {

    public static int RESPONSE_GOTO = 1;
    public static int RESPONSE_PROCESS = 2;
    public static int RESPONSE_PROCESS_GOTO = 3;

    public abstract int getType();
}
