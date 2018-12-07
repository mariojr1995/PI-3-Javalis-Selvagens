<%-- 
    Document   : login
    Created on : 06/12/2018, 01:01:58
    Author     : Rogerio Sobrinho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login - javalis Selvagens School</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    <body style="background: url(/JavaliSelvagens_PI3/background.jpg) no-repeat center center fixed; ">
        <div class="container">
            <div class="card card-container">
                <img id="profile-img" class="profile-img-card" src="/JavaliSelvagens_PI3/logo.png" />
                <p id="profile-name" class="profile-name-card">Javalis Selvagens<br/> TADS School</p>
                <form class="form-signin">
                    <span id="reauth-email" class="reauth-email"></span>
                    <input type="text" id="usuario" class="form-control" placeholder="Usuário" required autofocus>
                    <input type="password" id="Password" class="form-control" placeholder="Senha" required>
                    <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Entrar</button>
                </form><!-- /form -->
            </div>
        </div>
    </body>
</html>