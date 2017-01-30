package com.sinamo.listener;

import com.sinamo.kernel.Application;
import com.sinamo.kernel.Sinamo;
import com.sinamo.kernel.SinamoApplication;
import com.sinamo.kernel.SinamoFactory;
import com.sinamo.log.Log;
import com.sinamo.util.ReaderXML;
import java.io.File;
import java.io.InputStream;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Web application lifecycle listener.
 *
 * @author Romulo Galindo Tanta
 * @create Oct 21, 2016 3:32:22 AM
 */
@WebListener(value = "SinamoStartUp")
public class SinamoStartUp implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Log.log("== Iniciando SINAMO v1.0");

            //Crea una aplicacion en blanco
            Application sinamoApplication = new SinamoApplication();

            //!-- Mejorar esta parte -> solo debe haber una unica llamada a ReaderXML.
            ReaderXML readerXml = new ReaderXML(new File(sce.getServletContext().getRealPath("/WEB-INF/cfg/SinamoApp.xml")), sce);
            sinamoApplication.config(readerXml.getApplicationXMLunit());

            //Se pone la app en la pila statica de aplicaciones
            Sinamo.putApplication(sinamoApplication);

            //Iniciar la aplicacion
            sinamoApplication.run();
        } catch (Exception ep) {
            Log.error(ep, ep);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Log.log("== Finalizando Sinamo");
    }
}
