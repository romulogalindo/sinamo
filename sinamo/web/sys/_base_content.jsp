<%-- 
    Document   : _base_content
    Created on : 06-nov-2016, 9:37:08
    Author     : romulogalindo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${not empty _module}">
    <c:choose>
        <c:when test="${_module.type eq 'list'}">
            <%@include file="_base_content_list.jsp" %>
        </c:when>
        <c:when test="${_module.type eq 'form'}">
            <%@include file="_base_content_form.jsp" %>
        </c:when>
        <c:when test="${_module.type eq 'table'}">
            <%@include file="_base_content_table.jsp" %>
        </c:when>
    </c:choose>
</c:if>