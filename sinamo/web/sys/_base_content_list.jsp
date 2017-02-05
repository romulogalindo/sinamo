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
<c:set var="_transa" value="${requestScope._transa}"/>
<style>
    .mdl-card__supporting-text {
        padding: 16px 0px;
        width: 100%;
    }
</style>
<main class="mdl-layout__content mdl-color--grey-100">
    <div class="mdl-grid demo-content">
        <div class="demo-card-wide mdl-card mdl-shadow--2dp">
            <div class="mdl-card__title">
                <h2 class="mdl-card__title-text">
                    ${_module.head.title}
                </h2>
            </div>
            <%--<c:forEach var="section" items="${_module.sections}">--%>
            <div class="mdl-card__supporting-text">
                <!--                    <h4 class="mdl-card__subtitle-text sinamo-card--list--title">
                                        Titulo
                                    </h4>-->
                <ul class="demo-list-two mdl-list">
                    <c:forEach var="item" items="${_module.content.form.list}" varStatus="ix">
                        <li id="${_transa.transactionId}_${ix.count}" class="mdl-list__item mdl-list__item--two-line" onclick="snm.goto('${item.action}')">
                            <input type="hidden" id="${_transa.transactionId}_${ix.count}_v" name="${_transa.transactionId}_${ix.count}_v" value="${item.value}" />
                            <span class="mdl-list__item-primary-content">
                                <i class="material-icons mdl-list__item-icon">${item.icon}</i>
                                <span>${item.title}</span>
                                <span class="mdl-list__item-sub-title">${item.stitle}</span>
                            </span>
                            <span class="mdl-list__item-secondary-content">
                                <span class="mdl-list__item-secondary-info">${item.status}</span>
                                <!--                                <a class="mdl-list__item-secondary-action" href="#">
                                                                    <i class="material-icons">star</i>
                                                                </a>-->
                            </span>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="mdl-card__actions mdl-card--border">
                <c:forEach var="action" items="${_module.actions}">
                    <c:if test="${action.value.type == 'linkbutton'}">
                        <button  id="${action.value.id}" onclick="snm.goto('${action.value.url}')" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            ${action.value.title}
                        </button>
                    </c:if>
                    <c:if test="${action.value.type == 'button' }">
                        <button  id="${action.value.id}" onclick="snm.call('${action.value.func}', '${_transa.transactionId}')" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            ${action.value.title}
                        </button>
                    </c:if>
                </c:forEach>
            </div>
            <%--</c:forEach>--%>

            <!--div class="mdl-card__actions mdl-card--border">
            <%--<c:forEach var="action" items="${module.actions}">--%>
                <button id="{action.id}" onclick="{action.doit}" class="mdl-button mdl-js-button mdl-js-ripple-effect">
                    {action.title}
                </button>
            <%--</c:forEach>--%>
        </div--!>

            <!--            <div class="mdl-card__menu">
                            <button class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
                                <i class="material-icons">share</i>
                            </button>
                        </div>-->
        </div>
    </div>
</main>