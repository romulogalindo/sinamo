<%-- 
    Document   : _base_content
    Created on : 06-nov-2016, 9:37:08
    Author     : romulogalindo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Contenido genrico para el contenido -->
<!--<dialog id="snm_dialog_1A" class="mdl-dialog">
    <h4 class="mdl-dialog__title">Importante</h4>
    <div class="mdl-dialog__content">
        <p>
            Seguro de realizar esta acci&oacute;n?
        </p>
    </div>
    <div class="mdl-dialog__actions">
        <button type="button" class="mdl-button" id="snm_dialog_1A_SI">Si</button>
        <button type="button" class="mdl-button close">No</button>
    </div>
</dialog>-->




<c:if test="${not empty _module}">
    <c:choose>
        <c:when test="${_module.content.type eq 'list'}">
            <%@include file="_base_content_list.jsp" %>
        </c:when>
        <c:when test="${_module.content.type eq 'sectionform'}">
            <%@include file="_base_content_sectionform.jsp" %>
        </c:when>
        <c:when test="${_module.content.type eq 'table'}">
            <%@include file="_base_content_table.jsp" %>
        </c:when>
    </c:choose>
</c:if>

<script>
    var dialog1A = document.querySelector('#snm_dialog_1A');

    if (!dialog1A.showModal) {
        dialogPolyfill.registerDialog(dialog1A);
    }

    dialog1A.querySelector('.close').addEventListener('click', function () {
        dialog1A.close();
    });
</script>