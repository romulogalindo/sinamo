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
                    ${_module.head.title}
                </h2>
            </div>
            <c:forEach var="section" items="${_module.content.form.sections}" varStatus="ix">
                <div class="mdl-card__supporting-text">
                    <h4 class="mdl-card__subtitle-text sinamo-card--center">
                        ${section.title}
                    </h4>
                    <c:forEach var="register" items="${section.registers}" varStatus="ix2">
                        <c:choose>
                            <c:when test="${register.type =='input'}">
                                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                    <input class="mdl-textfield__input" type="text" id="${_transa.transactionId}_${register.name}" name="${_transa.transactionId}_${register.name}" value="${register.value}">
                                    <label class="mdl-textfield__label" for="sample1">${register.title}</label>
                                </div>
                            </c:when>
                            <c:when test="${register.type =='text'}">
                                <div class="mdl-textfield mdl-js-textfield">
                                    <textarea class="mdl-textfield__input" type="text" rows="3" id="${_transa.transactionId}_${register.name}" name="${_transa.transactionId}_${register.name}">${register.value}</textarea>
                                    <label class="mdl-textfield__label" for="sample5">${register.title}</label>
                                </div>
                            </c:when>
                            <c:when test="${register.type =='combo'}">

                                <div class="mdl-selectfield mdl-js-selectfield mdl-selectfield--floating-label">
                                    <select class="mdl-selectfield__select" id="${_transa.transactionId}_${register.name}" name="${_transa.transactionId}_${register.name}" value="${register.value}">
                                        <c:forEach items="${register.values}" var="value">
                                            <option value="${value.key}" ${register.value == value.key ?'selected':''} >${value.value}</option>
                                        </c:forEach>
                                    </select>
                                    <label class="mdl-selectfield__label" for="professsion2">Profession</label>
                                </div>
                            </c:when>
                            <c:when test="${register.type =='hidden'}">
                                <input type="hidden" id="${_transa.transactionId}_${register.name}" name="${_transa.transactionId}_${register.name}" value="${register.value}">
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </c:forEach>

            <div class="mdl-card__actions mdl-card--border">
                <c:forEach var="action" items="${_module.actions}">
                    <c:if test="${action.value.type == 'linkbutton'}">
                        <button  id="${action.value.id}" onclick="snm.goto('${action.value.url}')" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            ${action.value.title}
                        </button>
                    </c:if>
                    <c:if test="${action.value.type == 'button' }">
                        <button  id="${action.value.id}" onclick="snm.call('${action.value.func}', '${_transa.transactionId}', ${action.value.confirm})" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect">
                            ${action.value.title}
                        </button>
                    </c:if>
                </c:forEach>
            </div>

            <!--                        <div class="mdl-card__menu">
                                        <button class="mdl-button mdl-button--icon mdl-js-button mdl-js-ripple-effect">
                                            <i class="material-icons">share</i>
                                        </button>
                                    </div>-->
        </div>
    </div>
</main>