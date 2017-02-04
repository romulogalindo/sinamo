package com.sinamo.sys.servlet;

import com.sinamo.mods.Module;
import com.sinamo.kernel.Application;
import com.sinamo.kernel.SinamoApplication;
import com.sinamo.log.Log;
import com.sinamo.transa.SimpleTransaction;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author romulogalindo
 */
public class LinkManagerServlet extends DefaultSinamoServlet {

    public LinkManagerServlet(Application application) {
        super(application);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //Iniciando servlet

        //transa
        SimpleTransaction transa = new SimpleTransaction();

        //log
        String _log = "[" + this.getClass().getSimpleName() + ":" + request.getServletPath() + "][" + transa.getTransactionId() + "]";

        //variables iniciales!
        SinamoApplication sinamoApp = (SinamoApplication) application;
        SimpleRequest simpleRequest = new SimpleRequest(request);

        Log.log(_log + " ra << " + simpleRequest.getRA());

        //transa-initial values;
        transa.setInput(request);

        //Guardando
        sinamoApp.saveTransa(transa);

        //consulta a la cache!(en un futuro primero cache y luego db si no esta!!)
        Module module = sinamoApp.getModule(simpleRequest.getRA());

        try {
            //ejecuta el JS asignado para este modulo >> 
            Log.log(_log + " Ejecutando JS-DB");
            HashMap dataMap = (HashMap) sinamoApp.getJSService().executeFunction(module.getDataScriptName(), simpleRequest.getJsonRequest());
            System.out.println("_rs = " + dataMap);

            //Pintar el contenido! en el form ya creado
            module.getContent().getForm().draw(dataMap, module.getActions());
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        //Lo consultado y creado va como atributo
        request.setAttribute("_module", module);
        request.setAttribute("_transa", transa);

        //cache -> transa
        ((SimpleTransaction) sinamoApp.getTransa(transa.getTransactionId())).setServletTime();

        //Hacia la web(front-end)
        RequestDispatcher rd = request.getRequestDispatcher("sys/base.jsp");
        rd.forward(request, response);

        Log.log(_log + " Finish!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //transa
        SimpleTransaction transa = new SimpleTransaction();

        //log
        String _log = "[" + this.getClass().getSimpleName() + ":" + request.getServletPath() + "][" + transa.getTransactionId() + "]";

        //variables iniciales!
        SinamoApplication sinamoApp = (SinamoApplication) application;
        //!-- debe cambiar por un simplepostrequest
        SimpleRequest simpleRequest = new SimpleRequest(request);

        Log.log(_log + " ra << " + simpleRequest.getRA());

        //transa-initial values;
        transa.setInput(request);

        //Guardando
        sinamoApp.saveTransa(transa);

        System.out.println("request post recibido");
        String jsonOBject = request.getParameter("jsonOBject");
        String funcName = request.getParameter("func");
        String transaId = request.getParameter("transaId");

        try {
            //ejecuta el JS asignado para este modulo >> 
            Log.log(_log + " Ejecutando JS-DB");
            String resp = (String) sinamoApp.getJSService().executeFunction("_act" + funcName, jsonOBject, transaId);
            System.out.println("resp = " + resp);

            response.getOutputStream().print(resp);
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        response.getOutputStream().close();

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
