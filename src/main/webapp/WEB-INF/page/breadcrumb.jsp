<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<nav aria-label="breadcrumb">
  <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="/JavaliSelvagens_PI3"><i class="fas fa-home mr-2"></i>Home</a></li>
    <li class="breadcrumb-item active">${param.title1}</li>
    <li class="breadcrumb-item active" aria-current="page">${param.title2}</li>
  </ol>
</nav>