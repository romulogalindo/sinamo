package com.sinamo.sys.servlet;

import com.google.gson.Gson;
import com.sinamo.mods.Module;
import com.sinamo.kernel.Application;
import com.sinamo.kernel.SinamoApplication;
import com.sinamo.kernel.SinamoFactory;
import com.sinamo.log.Log;
import com.sinamo.transa.SimpleTransaction;
import java.io.IOException;
import java.util.HashMap;
import javax.script.Invocable;
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

//        //!-- Esto debe de modificarse
//        module.setPid(System.currentTimeMillis());
        try {
            //ejecuta el JS asignado para este modulo >> 
            Log.log(_log + " Ejecutando JS-DB");
            HashMap dataMap = (HashMap) sinamoApp.getJSService().executeFunction(module.getDataScriptName(), simpleRequest.getJsonRequest());
            System.out.println("_rs = " + dataMap);

            //Pintar el contenido! en el form ya creado
            module.getContent().getForm().draw(dataMap, module.getActions());

//            ModuleList _module = (ModuleList) module;
//            for (SectionList sectionList : _module.getSections()) {
//                sectionList.getRegisters().clear();
//                ListItem listItem = sectionList.getListItem();
//                System.out.println("listItem > " + listItem);
//                String _r = listItem.getTitle().split("\\$")[1];
//                System.out.println("_r = " + _r);
//                System.out.println("_rs.get(_r) = " + dataMap.get(_r));
//
//                String _titleKey = listItem.getTitle().split("\\$").length == 3 ? listItem.getTitle().split("\\$")[2].replace("{", "").replace("}", "") : "";
//                String _title2Key = listItem.getTitle2().split("\\$").length == 3 ? listItem.getTitle2().split("\\$")[2].replace("{", "").replace("}", "") : "";
//                String _title3Key = listItem.getTitle3().split("\\$").length == 3 ? listItem.getTitle3().split("\\$")[2].replace("{", "").replace("}", "") : "";
//                String _iconKey = listItem.getIcon().split("\\$").length == 3 ? listItem.getIcon().split("\\$")[2].replace("{", "").replace("}", "") : "";
//                String _valueKey = listItem.getValue().split("\\$").length == 3 ? listItem.getValue().split("\\$")[2].replace("{", "").replace("}", "") : "";
//
//                List<HashMap> ls = (List<HashMap>) dataMap.get(_r);
//                for (HashMap map : ls) {
//                    ListItem _listItem = new ListItem();
//
//                    System.out.println("[" + _titleKey + ":" + map.get(_titleKey) + "]");
//                    _listItem.setTitle(map.get(_titleKey) != null ? map.get(_titleKey).toString() : "");
//                    _listItem.setTitle2(map.get(_title2Key) != null ? map.get(_title2Key).toString() : "");
//                    _listItem.setTitle3(map.get(_title3Key) != null ? map.get(_title3Key).toString() : "");
//                    _listItem.setIcon(map.get(_iconKey) != null ? map.get(_iconKey).toString() : "");
//                    _listItem.setValue(map.get(_valueKey) != null ? map.get(_valueKey).toString() : "");
//
//                    sectionList.addRegister(_listItem);
//                }
//            }
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

        System.out.println("request post recibido");
        String jsonOBject = request.getParameter("jsonOBject");
        String funcName = null;

        Invocable invocable = (Invocable) SinamoFactory.getSimanoEngine().getScriptEngine();
        try {
            funcName = (String) invocable.invokeFunction("_goto", jsonOBject);
            System.out.println("rpta = " + funcName);
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        Object responseGoto = null;
        try {
            responseGoto = invocable.invokeFunction(funcName, jsonOBject);
            System.out.println("rpta = " + responseGoto);
            System.out.println("rpta = " + responseGoto.getClass());
        } catch (Exception ep) {
            ep.printStackTrace();
        }

        response.getOutputStream().print(new Gson().toJson(responseGoto));
        response.getOutputStream().close();

//        Enumeration<String> enu = request.getParameterNames();
//        while (enu.hasMoreElements()) {
//            String k = enu.nextElement();
//            String v = request.getParameter(k);
//            System.out.println("k=" + k + ",v=" + v);
//        }
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
