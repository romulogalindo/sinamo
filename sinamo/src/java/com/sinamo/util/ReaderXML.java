package com.sinamo.util;

import com.sinamo.log.Log;
import com.sinamo.units.ApplicationXMLUnit;
import com.sinamo.units.CacheServiceXMLUnit;
import com.sinamo.units.ClassXMLUnit;
import com.sinamo.units.DataServiceXMLUnit;
import com.sinamo.units.HttpServiceXMLUnit;
import com.sinamo.units.JavaScriptServiceXMLUnit;
import com.sinamo.units.RestFulXMLUnit;
import com.sinamo.units.ScriptXMLUnit;
import com.sinamo.units.ServiceXMLUnit;
import com.sinamo.units.ServletXMLUnit;
import com.sinamo.units.SourceXMLUnit;
import com.sinamo.units.SpaceXMLUnit;
import com.sinamo.units.WebSocketXMLUnit;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Romulo Galindo Tanta
 * @create Jan 11, 2017 5:53:04 PM
 */
public class ReaderXML {

    File file;
    ServletContextEvent sce;

    public ReaderXML(String fileName) {
        this(new File(fileName));
    }

    public ReaderXML(File file) {
        this(file, null);
    }

    public ReaderXML(File file, ServletContextEvent sce) {
        this.file = file;
        this.sce = sce;
    }

    public ApplicationXMLUnit getApplicationXMLunit() {
        ApplicationXMLUnit dsxml = new ApplicationXMLUnit();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            Document document = documentBuilderFactory.newDocumentBuilder().parse(this.file);
            document.normalize();
            Element elementRoot = (Element) document.getElementsByTagName("app").item(0);

            //Syssource
            Node syssource = elementRoot.getElementsByTagName("name").item(0);
            dsxml.setApplicationName(syssource.getTextContent());

            //Sources
            List<ServiceXMLUnit> sourceList = new ArrayList();
            NodeList sources = ((Element) elementRoot.getElementsByTagName("services").item(0)).getElementsByTagName("service");
            for (int i = 0; i < sources.getLength(); i++) {
                sourceList.add(getServicefromNode(sources.item(i)));
            }
            dsxml.setServices(sourceList);
            dsxml.setSce(sce);
        } catch (Exception ep) {
            Log.error(ep, ep);
        }
        return dsxml;
    }

    public DataServiceXMLUnit getDataServiceXMLUnit() {
        DataServiceXMLUnit dsxml = new DataServiceXMLUnit();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            Document document = documentBuilderFactory.newDocumentBuilder().parse(this.file);
            document.normalize();
            Element elementRoot = (Element) document.getElementsByTagName("data-service").item(0);

            //Syssource
            Node syssource = ((Element) elementRoot.getElementsByTagName("syssource").item(0)).getElementsByTagName("source").item(0);
            List<SourceXMLUnit> syssourceList = new ArrayList();
            syssourceList.add(getSourcefromNode(syssource));
            dsxml.setSyssource(syssourceList);

            //Sources
            List<SourceXMLUnit> sourceList = new ArrayList();
            NodeList sources = ((Element) elementRoot.getElementsByTagName("sources").item(0)).getElementsByTagName("source");
            for (int i = 0; i < sources.getLength(); i++) {
                sourceList.add(getSourcefromNode(sources.item(i)));
            }
            dsxml.setSources(sourceList);
        } catch (Exception ep) {
            Log.error(ep, ep);
        }

        return dsxml;
    }

    public JavaScriptServiceXMLUnit getJavaScriptServiceXMLUnit() {
        JavaScriptServiceXMLUnit dsxml = new JavaScriptServiceXMLUnit();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            Document document = documentBuilderFactory.newDocumentBuilder().parse(this.file);
            document.normalize();
            Element elementRoot = (Element) document.getElementsByTagName("js-service").item(0);

            //scripts
            List<ScriptXMLUnit> scriptsList = new ArrayList();
            NodeList scripts = ((Element) elementRoot.getElementsByTagName("scripts").item(0)).getElementsByTagName("script");
            for (int i = 0; i < scripts.getLength(); i++) {
                scriptsList.add(getScriptfromNode(scripts.item(i)));
            }
            dsxml.setScripts(scriptsList);

            //classes
            List<ClassXMLUnit> sourceList = new ArrayList();
            NodeList sources = ((Element) elementRoot.getElementsByTagName("classes").item(0)).getElementsByTagName("class");
            for (int i = 0; i < sources.getLength(); i++) {
                sourceList.add(getClassfromNode(sources.item(i)));
            }
            dsxml.setClasses(sourceList);
        } catch (Exception ep) {
            Log.error(ep, ep);
        }

        return dsxml;
    }

    public CacheServiceXMLUnit getCacheServiceXMLUnit() {
        CacheServiceXMLUnit dsxml = new CacheServiceXMLUnit();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            Document document = documentBuilderFactory.newDocumentBuilder().parse(this.file);
            document.normalize();
            Element elementRoot = (Element) document.getElementsByTagName("cache-service").item(0);

            //scripts
            List<SpaceXMLUnit> spaceList = new ArrayList();
            NodeList scripts = elementRoot.getElementsByTagName("space");
            for (int i = 0; i < scripts.getLength(); i++) {
                spaceList.add(getSpacefromNode(scripts.item(i)));
            }
            dsxml.setSpaces(spaceList);
        } catch (Exception ep) {
            Log.error(ep, ep);
        }

        return dsxml;
    }

    public HttpServiceXMLUnit getHttpServiceXMLUnit() {
        HttpServiceXMLUnit dsxml = new HttpServiceXMLUnit();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            Document document = documentBuilderFactory.newDocumentBuilder().parse(this.file);
            document.normalize();
            Element elementRoot = (Element) document.getElementsByTagName("http-service").item(0);

            //servlets
            List<ServletXMLUnit> servletList = new ArrayList();
            NodeList sources = ((Element) elementRoot.getElementsByTagName("servlets").item(0)).getElementsByTagName("servlet");
            for (int i = 0; i < sources.getLength(); i++) {
                servletList.add(getServletfromNode(sources.item(i)));
            }
            dsxml.setServlets(servletList);

            //restfull
            List<RestFulXMLUnit> restfullList = new ArrayList();
            NodeList sources2 = ((Element) elementRoot.getElementsByTagName("servlets").item(0)).getElementsByTagName("restfull");
            for (int i = 0; i < sources2.getLength(); i++) {
                restfullList.add(getRestFullfromNode(sources2.item(i)));
            }
            dsxml.setRestfuls(restfullList);

            //websocket
            List<WebSocketXMLUnit> websocketList = new ArrayList();
            NodeList sources3 = ((Element) elementRoot.getElementsByTagName("servlets").item(0)).getElementsByTagName("restfull");
            for (int i = 0; i < sources3.getLength(); i++) {
                websocketList.add(getWebSocketfromNode(sources3.item(i)));
            }
            dsxml.setWebsockets(websocketList);
            dsxml.setSce(sce);
        } catch (Exception ep) {
            Log.error(ep, ep);
        }

        return dsxml;
    }

    private ServiceXMLUnit getServicefromNode(Node source) {
        NamedNodeMap attr = source.getAttributes();
        ServiceXMLUnit xml = new ServiceXMLUnit();
        xml.setType(attr.getNamedItem("type") != null ? attr.getNamedItem("type").getNodeValue() : "");
        xml.setFile(attr.getNamedItem("file") != null ? attr.getNamedItem("file").getNodeValue() : "");
        return xml;
    }

    private SourceXMLUnit getSourcefromNode(Node source) {
        NamedNodeMap attr = source.getAttributes();
        SourceXMLUnit xml = new SourceXMLUnit();
        xml.setType(attr.getNamedItem("type") != null ? attr.getNamedItem("type").getNodeValue() : "");
        xml.setServer(attr.getNamedItem("server") != null ? attr.getNamedItem("server").getNodeValue() : "");
        xml.setPort(attr.getNamedItem("port") != null ? attr.getNamedItem("port").getNodeValue() : "");
        xml.setDbname(attr.getNamedItem("dbname") != null ? attr.getNamedItem("dbname").getNodeValue() : "");
        xml.setUser(attr.getNamedItem("user") != null ? attr.getNamedItem("user").getNodeValue() : "");
        xml.setPassword(attr.getNamedItem("pass") != null ? attr.getNamedItem("pass").getNodeValue() : "");
        xml.setEngine(attr.getNamedItem("engine") != null ? attr.getNamedItem("engine").getNodeValue() : "");
        return xml;
    }

    private ScriptXMLUnit getScriptfromNode(Node source) {
        NamedNodeMap attr = source.getAttributes();
        ScriptXMLUnit xml = new ScriptXMLUnit();
        xml.setUrl(attr.getNamedItem("url") != null ? attr.getNamedItem("url").getNodeValue() : "");
        return xml;
    }

    private ClassXMLUnit getClassfromNode(Node source) {
        NamedNodeMap attr = source.getAttributes();
        ClassXMLUnit xml = new ClassXMLUnit();
        xml.setUrl(attr.getNamedItem("url") != null ? attr.getNamedItem("url").getNodeValue() : "");
        xml.setAccessName(attr.getNamedItem("accessname") != null ? attr.getNamedItem("accessname").getNodeValue() : "");
        return xml;
    }

    private SpaceXMLUnit getSpacefromNode(Node source) {
        NamedNodeMap attr = source.getAttributes();
        SpaceXMLUnit xml = new SpaceXMLUnit();
        xml.setName(attr.getNamedItem("name") != null ? attr.getNamedItem("name").getNodeValue() : "");
        xml.setKey(attr.getNamedItem("key") != null ? attr.getNamedItem("key").getNodeValue() : "");
        xml.setValue(attr.getNamedItem("value") != null ? attr.getNamedItem("value").getNodeValue() : "");
        return xml;
    }

    private ServletXMLUnit getServletfromNode(Node source) {
        NamedNodeMap attr = source.getAttributes();
        ServletXMLUnit xml = new ServletXMLUnit();
        xml.setName(attr.getNamedItem("name") != null ? attr.getNamedItem("name").getNodeValue() : "");
        xml.setClassName(attr.getNamedItem("class") != null ? attr.getNamedItem("class").getNodeValue() : "");
        if (attr.getNamedItem("maps") != null) {
            String[] patts = attr.getNamedItem("maps").getNodeValue().split(",");
            List<String> pts = new ArrayList<>();
            for (String patt : patts) {
                pts.add(patt);
            }
            xml.setPatterns(pts);
        }
        return xml;
    }

    private RestFulXMLUnit getRestFullfromNode(Node source) {
        NamedNodeMap attr = source.getAttributes();
        RestFulXMLUnit xml = new RestFulXMLUnit();
        xml.setName(attr.getNamedItem("name") != null ? attr.getNamedItem("name").getNodeValue() : "");
        xml.setClassName(attr.getNamedItem("class") != null ? attr.getNamedItem("class").getNodeValue() : "");
        xml.setPatterns(attr.getNamedItem("value") != null ? Arrays.asList(attr.getNamedItem("value").getNodeValue().split(",")) : new ArrayList<>());
        return xml;
    }

    private WebSocketXMLUnit getWebSocketfromNode(Node source) {
        NamedNodeMap attr = source.getAttributes();
        WebSocketXMLUnit xml = new WebSocketXMLUnit();
        xml.setName(attr.getNamedItem("name") != null ? attr.getNamedItem("name").getNodeValue() : "");
        xml.setClassName(attr.getNamedItem("class") != null ? attr.getNamedItem("class").getNodeValue() : "");
        xml.setPatterns(attr.getNamedItem("value") != null ? Arrays.asList(attr.getNamedItem("value").getNodeValue().split(",")) : new ArrayList<>());
        return xml;
    }

    public static void main(String[] args) {
        try {
            ReaderXML rxml = new ReaderXML("/home/romulogalindo/NetBeansProjects/sinamo/sinamo/web/WEB-INF/cfg/DataService.xml");
            DataServiceXMLUnit dsxml = rxml.getDataServiceXMLUnit();
            System.out.println("dsxml = " + dsxml);
            System.out.println("dsxml = " + dsxml.getSources().size());
            System.out.println("dsxml = " + dsxml.getSyssource().size());
        } catch (Exception ep) {
            ep.printStackTrace();
        }
    }
}
