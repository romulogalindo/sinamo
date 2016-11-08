<%-- 
    Document   : _base_content
    Created on : 06-nov-2016, 9:37:08
    Author     : romulogalindo
--%>

<%@page import="com.sinamo.bean.items.Action"%>
<%@page import="com.sinamo.bean.items.Register"%>
<%@page import="com.sinamo.bean.Module"%>
<%@page import="com.sinamo.bean.items.Section"%>
<%@page import="com.sinamo.kernel.SinamoFactory"%>
<%@page import="com.sinamo.kernel.Engine"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<main class="mdl-layout__content mdl-color--grey-100">
    <div class="mdl-grid demo-content">
        <div class="demo-card-wide mdl-card mdl-shadow--2dp">
            <div class="mdl-card__title">
                <h2 class="mdl-card__title-text">
                    <%
                        Module module = SinamoFactory.getSimanoEngine().getModule("hello_world");
                        out.print(module.getTitle());
                    %>
                </h2>
            </div>
            <!--            <div class="mdl-card__supporting-text">
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                            Mauris sagittis pellentesque lacus eleifend lacinia...
                        </div>-->
            <%
                System.out.println("-->" + module.getSections());
                for (Section section : module.getSections()) {
            %>
            <div class="mdl-card__supporting-text">
                <h4 class="mdl-card__subtitle-text sinamo-card--center">
                    <%=section.getTitle()%>
                </h4>

                <%
                    for (Register register : section.getRegisters()) {
                %>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <input class="mdl-textfield__input" type="text" id="sample1" value="<%=register.getValue()%>">
                    <label class="mdl-textfield__label" for="sample1"><%=register.getTitle()%></label>
                </div>
                <%
                    }
                %>
            </div>
            <%                }
            %>

            <div class="mdl-card__actions mdl-card--border">
                <%
                    for (Action action : module.getActions()) {
                %>
                <button id="<%=action.getId()%>" onclick="<%=action.getDoit()%>" class="mdl-button mdl-js-button mdl-js-ripple-effect">
                    <%=action.getTitle()%>
                </button>
                <%
                    }
                %>

            </div>

            <div class="mdl-card__menu">
                <button class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
                    <i class="material-icons">share</i>
                </button>
            </div>
        </div>
        <!--<div class="mdl-grid">-->
        <!--<div class="mdl-cell mdl-cell--6-col">6</div>-->
        <!--<div class="mdl-cell mdl-cell--4-col">4</div>-->
        <!--<div class="mdl-cell mdl-cell--2-col">2</div>-->
        <!--</div>-->
    </div>
</main>