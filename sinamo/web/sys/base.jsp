<%-- 
    Document   : base
    Created on : 06-nov-2016, 8:37:31
    Author     : romulogalindo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
        <title>Sinamo ver. 1.0</title>

        <!-- Add to homescreen for Chrome on Android -->
        <meta name="mobile-web-app-capable" content="yes">
        <link rel="icon" sizes="192x192" href="images/android-desktop.png">

        <!-- Add to homescreen for Safari on iOS -->
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-title" content="Material Design Lite">
        <link rel="apple-touch-icon-precomposed" href="images/ios-desktop.png">

        <!-- Tile icon for Win8 (144x144 + tile color) -->
        <meta name="msapplication-TileImage" content="images/touch/ms-touch-icon-144x144-precomposed.png">
        <meta name="msapplication-TileColor" content="#3372DF">

        <link rel="shortcut icon" href="images/favicon.png">

        <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
        <!--
        <link rel="canonical" href="http://www.example.com/">
        -->

        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.2.1/material.blue-indigo.min.css" /> 
       <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/material.css">
        <style>
            .demo-avatar {
                width: 48px;
                height: 48px;
                border-radius: 24px;
            }

            .demo-drawer .mdl-menu .mdl-menu__item {
                display: -webkit-flex;
                display: -ms-flexbox;
                display: flex;
                -webkit-align-items: center;
                -ms-flex-align: center;
                align-items: center;
            }

            .demo-drawer-header {
                box-sizing: border-box;
                display: -webkit-flex;
                display: -ms-flexbox;
                display: flex;
                -webkit-flex-direction: column;
                -ms-flex-direction: column;
                flex-direction: column;
                -webkit-justify-content: flex-end;
                -ms-flex-pack: end;
                justify-content: flex-end;
                padding: 16px;
                height: 151px;
                background-image: url('${pageContext.request.contextPath}/images/1200_20100924-IMG-5794-HDR-Edit.jpg');
                background-size: cover;
            }

            .demo-avatar-dropdown {
                display: -webkit-flex;
                display: -ms-flexbox;
                display: flex;
                position: relative;
                -webkit-flex-direction: row;
                -ms-flex-direction: row;
                flex-direction: row;
                -webkit-align-items: center;
                -ms-flex-align: center;
                align-items: center;
                width: 100%;
            }
            .demo-navigation {
                -webkit-flex-grow: 1;
                -ms-flex-positive: 1;
                flex-grow: 1;
            }
            .demo-layout .demo-navigation .mdl-navigation__link {
                display: -webkit-flex !important;
                display: -ms-flexbox !important;
                display: flex !important;
                -webkit-flex-direction: row;
                -ms-flex-direction: row;
                flex-direction: row;
                -webkit-align-items: center;
                -ms-flex-align: center;
                align-items: center;
                color: rgba(255, 255, 255, 0.56);
                font-weight: 500;
            }
            .demo-layout .demo-navigation .mdl-navigation__link:hover {
                background-color: #00BCD4;
                color: #37474F;
            }
            .demo-navigation .mdl-navigation__link .material-icons {
                font-size: 24px;
                color: rgba(255, 255, 255, 0.56);
                margin-right: 32px;
            }

            .demo-content {
                max-width: 1080px;
            }

            /*DEMO*/
            .demo-card-wide.mdl-card {
                width: 512px;
            }

            .demo-card-wide > .mdl-card__title {
                color: #fff;
                background: #46B6AC;
            }

            .demo-card-wide > .mdl-card__menu {
                color: #fff;
            }

            .sinamo-card--center{
                text-align: center;
                line-height: 16px;
            }

            .sinamo-card--list--title{
                border-bottom: 1px solid rgba(0, 0, 0, 0.1);
                margin: 0px 16px;
                text-align: left;
                line-height: 16px;
            }

            .mdl-list__item--two-line {
                cursor: pointer;
                transition-duration: .28s;
                transition-timing-function: cubic-bezier(.4,0,.2,1);
                transition-property: background-color;
            }

            .mdl-list__item--two-line:hover {
                background-color: #eee;
            }
        </style>

        <script src="${pageContext.request.contextPath}/js/material.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery3.1.1.js"></script>
        <script src="${pageContext.request.contextPath}/js/sinamobasic.js"></script>
    </head>
    <body>
        <div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
            <!-- Header custom for each module -->
            <jsp:include page="_base_header.jsp"/>

            <!-- Dash lateral derecho -->
            <jsp:include page="_base_dash.jsp"/>

            <!-- Contenido -->
            <%@include file="_base_content.jsp" %>
        </div>
    </body>
</html>
