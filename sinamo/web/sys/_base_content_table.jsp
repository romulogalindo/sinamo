<%-- 
    Document   : _base_content
    Created on : 06-nov-2016, 9:37:08
    Author     : romulogalindo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.sinamo.bean.items.Action"%>
<%@page import="com.sinamo.bean.items.Register"%>
<%@page import="com.sinamo.bean.Module"%>
<%@page import="com.sinamo.bean.items.Section"%>
<%@page import="com.sinamo.kernel.SinamoFactory"%>
<%@page import="com.sinamo.kernel.Engine"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="_module" value="${requestScope._module}"/>

<main class="mdl-layout__content mdl-color--grey-100">
    <div class="mdl-grid demo-content">


        <div class="demo-card-wide mdl-card mdl-shadow--2dp">
            <div class="mdl-card__title">
                <h2 class="mdl-card__title-text">
                    ${_module.title}
                </h2>
            </div>

            <table class="mdl-data-table mdl-js-data-table">
                <thead>
                    <tr>
                        <c:forEach var="section" items="${_module.sections}">
                            <c:forEach var="register" items="${section.registers}">
                                <th class="mdl-data-table__cell--non-numeric">${register.title}</th>
                                </c:forEach>
                            </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${section.rows}">
                        <tr>
                            <c:forEach var="cell" items="${row.cells}">
                                <td class="mdl-data-table__cell--non-numeric">${cell.value}</td>
                            </c:forEach>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>

            <div class="mdl-card__actions mdl-card--border">
                <c:forEach var="action" items="${module.actions}">
                    <button id="${action.id}" onclick="${action.doit}" class="mdl-button mdl-js-button mdl-js-ripple-effect">
                        ${action.title}
                    </button>
                </c:forEach>
            </div>

        </div>

    </div>
</main>