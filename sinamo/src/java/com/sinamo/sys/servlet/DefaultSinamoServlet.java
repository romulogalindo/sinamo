package com.sinamo.sys.servlet;

import com.sinamo.kernel.Application;
import javax.servlet.http.HttpServlet;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 27, 2017 8:06:42 AM
 */
public class DefaultSinamoServlet extends HttpServlet {

    Application application;

    public DefaultSinamoServlet(Application application) {
        this.application = application;
    }

}
