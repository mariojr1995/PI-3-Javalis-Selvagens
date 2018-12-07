<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>${param.title}</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=320, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Javalis Selvagens Ltda - Project School Tads" />
    <meta name="author" content="Javalis Selvagens Ltda" />
    <meta name="keywords" content="School, Tads, Javalis Selvagens" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.0/normalize.min.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/flatly/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" >
    <link rel="stylesheet" href="/JavaliSelvagens_PI3/main.css" >
    <link rel="apple-touch-icon" sizes="57x57" href="/JavaliSelvagens_PI3/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="/JavaliSelvagens_PI3/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="/JavaliSelvagens_PI3/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="/JavaliSelvagens_PI3/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="/JavaliSelvagens_PI3/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="/JavaliSelvagens_PI3/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="/JavaliSelvagens_PI3/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="/JavaliSelvagens_PI3/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="/JavaliSelvagens_PI3/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192"  href="/JavaliSelvagens_PI3/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="/JavaliSelvagens_PI3/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="/JavaliSelvagens_PI3/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/JavaliSelvagens_PI3/favicon-16x16.png">
    <link rel="manifest" href="/JavaliSelvagens_PI3/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="/JavaliSelvagens_PI3/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
</head>
<body>
        <div class="wrapper">
            <jsp:include page="/WEB-INF/page/menu.jsp"/>
             <div id="content">
                <jsp:include page="/WEB-INF/page/header.jsp"/>
                <jsp:include page="/WEB-INF/${param.content}.jsp"/>
            </div>
        </div>
    
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#sidebarCollapse').on('click', function () {
                    $('#sidebar').toggleClass('active');
                    $(this).toggleClass('active');
                });
            });
        </script>
</body>
</html>