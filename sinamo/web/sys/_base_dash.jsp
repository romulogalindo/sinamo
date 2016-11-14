<%-- 
    Document   : _base_dash
    Created on : 06-nov-2016, 9:29:05
    Author     : romulogalindo
--%>

<%@page import="com.sinamo.bean.items.ModuleItem"%>
<%@page import="com.sinamo.bean.items.MenuItem"%>
<%@page import="java.util.List"%>
<%@page import="com.sinamo.kernel.SinamoFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<div class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">-->
<div class="mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
    <header class="demo-drawer-header">
        <img src="${pageContext.request.contextPath}/images/photo.jpg" class="demo-avatar">
        <div class="demo-avatar-dropdown">
            <span>hello@example.com</span>
            <div class="mdl-layout-spacer"></div>
            <button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
                <i class="material-icons" role="presentation">arrow_drop_down</i>
                <span class="visuallyhidden">Accounts</span>
            </button>
            <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
                <li class="mdl-menu__item">hello@example.com</li>
                <li class="mdl-menu__item">info@example.com</li>
                <li class="mdl-menu__item"><i class="material-icons">add</i>Add another account...</li>
            </ul>
        </div>
    </header>

    <nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
        <%
            for (MenuItem menuitem : SinamoFactory.getSimanoEngine().getSysmenu()) {
        %>
        <a class="mdl-navigation__link" href="">
            <i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">home</i>
            <%=menuitem.getTitle()%>
        </a>
        <%
            for (ModuleItem moduleitem : menuitem.getModuleitems()) {
        %>
        <a class="mdl-navigation__link" href="${pageContext.request.contextPath}/snm?ra=<%=moduleitem.getId()%>">
            <%=moduleitem.getTitle()%>
        </a>
        <%
                }
            }
        %>

        <!--a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">inbox</i>Inbox</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">delete</i>Trash</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">report</i>Spam</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">forum</i>Forums</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">flag</i>Updates</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">local_offer</i>Promos</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">shopping_cart</i>Purchases</a>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">people</i>Social</a>
        <div class="mdl-layout-spacer"></div>
        <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a-->
    </nav>
</div>
