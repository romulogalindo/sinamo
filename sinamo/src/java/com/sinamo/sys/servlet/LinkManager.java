package com.sinamo.sys.servlet;

import com.google.gson.Gson;
import com.sinamo.bean.Module;
import com.sinamo.bean.ModuleList;
import com.sinamo.bean.items.ListItem;
import com.sinamo.bean.items.SectionList;
import com.sinamo.kernel.SinamoFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.Invocable;
import javax.script.ScriptException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author romulogalindo
 */
@WebServlet(name = "LinkManager", urlPatterns = {"/snm"})
public class LinkManager extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String ra = request.getParameter("ra");
        Module module = SinamoFactory.getSimanoEngine().getModule(ra);
        module.setPid(System.currentTimeMillis());
        Invocable invocable = (Invocable) SinamoFactory.getSimanoEngine().getScriptEngine();
        try {
            HashMap _rs = (HashMap) invocable.invokeFunction(module.getDataScript(), request);
            System.out.println("_rs = " + _rs);

            ModuleList _module = (ModuleList) module;
            for (SectionList sectionList : _module.getSections()) {
                sectionList.getRegisters().clear();
                ListItem listItem = sectionList.getListItem();
                System.out.println("listItem > " + listItem);
                String _r = listItem.getTitle().split("\\$")[1];
                System.out.println("_r = " + _r);
                System.out.println("_rs.get(_r) = " + _rs.get(_r));

                String _titleKey = listItem.getTitle().split("\\$").length == 3 ? listItem.getTitle().split("\\$")[2].replace("{", "").replace("}", "") : "";
                String _title2Key = listItem.getTitle2().split("\\$").length == 3 ? listItem.getTitle2().split("\\$")[2].replace("{", "").replace("}", "") : "";
                String _title3Key = listItem.getTitle3().split("\\$").length == 3 ? listItem.getTitle3().split("\\$")[2].replace("{", "").replace("}", "") : "";
                String _iconKey = listItem.getIcon().split("\\$").length == 3 ? listItem.getIcon().split("\\$")[2].replace("{", "").replace("}", "") : "";
                String _valueKey = listItem.getValue().split("\\$").length == 3 ? listItem.getValue().split("\\$")[2].replace("{", "").replace("}", "") : "";

                List<HashMap> ls = (List<HashMap>) _rs.get(_r);
                for (HashMap map : ls) {
                    ListItem _listItem = new ListItem();

                    System.out.println("[" + _titleKey + ":" + map.get(_titleKey) + "]");
                    _listItem.setTitle(map.get(_titleKey) != null ? map.get(_titleKey).toString() : "");
                    _listItem.setTitle2(map.get(_title2Key) != null ? map.get(_title2Key).toString() : "");
                    _listItem.setTitle3(map.get(_title3Key) != null ? map.get(_title3Key).toString() : "");
                    _listItem.setIcon(map.get(_iconKey) != null ? map.get(_iconKey).toString() : "");
                    _listItem.setValue(map.get(_valueKey) != null ? map.get(_valueKey).toString() : "");

                    sectionList.addRegister(_listItem);
                }
            }
        } catch (Exception ep) {
            ep.printStackTrace();
        }
        request.setAttribute("_module", module);

        RequestDispatcher rd = request.getRequestDispatcher("sys/base.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
