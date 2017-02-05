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

        <!--        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
                <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
                <link rel="stylesheet" href="https://code.getmdl.io/1.2.1/material.blue-indigo.min.css" /> -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
        <!--<link rel="stylesheet prefetch" href="https://cdnjs.cloudflare.com/ajax/libs/dialog-polyfill/0.4.2/dialog-polyfill.min.css">-->

        <!--<script src="https://cdnjs.cloudflare.com/ajax/libs/dialog-polyfill/0.4.2/dialog-polyfill.min.js"></script>-->
        <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dialog-polyfill.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/material.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/getmdl-select.min.css">
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

            .loading{
                position: absolute;
                top: 50%;
                left: 50%;
                margin-top: -50px;
                margin-left: -50px;
                width: 100px;
                height: 100px;
                z-index: 200002;
                display: none;
            }
        </style>

        <script src="${pageContext.request.contextPath}/js/material.js"></script>
        <script src="${pageContext.request.contextPath}/js/getmdl-select.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery3.1.1.js"></script>
        <script src="${pageContext.request.contextPath}/js/sinamobasic.js?a=2"></script>
        <script src="${pageContext.request.contextPath}/js/dialog-polyfill.js"></script>
    </head>
    <body>
        <dialog id="snm_dialog_1A" class="mdl-dialog">
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
        </dialog>
        
        <div id="snm_loading" class="loading">
            <h4 class="mdl-dialog__title" style="text-align: center;">
                <div class="mdl-spinner mdl-js-spinner is-active"></div>
            </h4>
            <div class="mdl-dialog__content" style="padding-top: 0px;padding-right: 0px;padding-left: 0px;text-align: center;">
                <p style="">
                    Cargando
                </p>
            </div>
        </div>
        <div id="snm_bg_loading" class="backdrop" style="z-index: 200001;position: fixed;top: 0;right: 0;bottom: 0;left: 0;background: rgba(0,0,0,0.1);display: none;"></div>

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
