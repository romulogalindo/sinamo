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
                    ${_module.title}
                </h2>
            </div>
            <c:forEach var="section" items="${_module.sections}">
                <div class="mdl-card__supporting-text">
                    <h4 class="mdl-card__subtitle-text sinamo-card--list--title">
                        ${section.title}
                    </h4>
                    <ul class="demo-list-two mdl-list">
                        <c:forEach var="register" items="${section.registers}" varStatus="ix">
                            <li id="${_module.pid}_${ix.count}" class="mdl-list__item mdl-list__item--two-line" onclick="${_module.actions[0].doit}">
                                <input type="hidden" id="${_module.pid}_${ix.count}_v" name="${_module.pid}_${ix.count}_v" value="${register.value}" />
                                <span class="mdl-list__item-primary-content">
                                    <i class="material-icons mdl-list__item-avatar">${register.icon}</i>
                                    <span>${register.title}</span>
                                    <span class="mdl-list__item-sub-title">${register.title2}</span>
                                </span>
                                <span class="mdl-list__item-secondary-content">
                                    <span class="mdl-list__item-secondary-info">${register.title3}</span>
                                    <!--                                    <a class="mdl-list__item-secondary-action" href="#">
                                                                            <i class="material-icons">star</i>
                                                                        </a>-->
                                </span>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:forEach>

            <div class="mdl-card__actions mdl-card--border">
                <c:forEach var="action" items="${module.actions}">
                    <button id="${action.id}" onclick="${action.doit}" class="mdl-button mdl-js-button mdl-js-ripple-effect">
                        ${action.title}
                    </button>
                </c:forEach>
            </div>

            <!--            <div class="mdl-card__menu">
                            <button class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
                                <i class="material-icons">share</i>
                            </button>
                        </div>-->
        </div>
    </div>
</main>