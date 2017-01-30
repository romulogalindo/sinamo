package com.sinamo.sys.servlet;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 28, 2017 10:16:01 AM
 */
public class SimpleRequest {

    private HttpServletRequest request;

    public SimpleRequest(HttpServletRequest request) {
        this.request = request;
    }

    public int getRA() {
        int ra = -1;
        try {
            ra = Integer.parseInt(request.getParameter("ra"));
        } catch (Exception ep) {
        }
        return ra;
    }

    public String getJsonRequest() {
        String jsonRequest = "";

        JSONObject obj = new JSONObject();
        JSONArray params = new JSONArray();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = enu.nextElement();
            String val = request.getParameter(key);
            JSONObject obj2 = new JSONObject();
            obj2.put(key, val);
            params.add(obj2);
        }
        obj.put("params", params);

        return obj.toJSONString();
    }

}
