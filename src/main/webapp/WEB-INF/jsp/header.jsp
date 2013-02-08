<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/modernizr/2.6.2/modernizr.min.js"></script>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.2.2/bootstrap.min.js"></script>
		<script type="text/javascript" src="/gelibolu/resources/dt/jquery.dataTables.js"></script>
		<script type="text/javascript" src="/gelibolu/resources/dt/DT_bootstrap.js"></script>
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
		<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.1/css/bootstrap-combined.min.css" rel="stylesheet">
		<link rel="stylesheet" href="/gelibolu/resources/dt/DT_bootstrap.css" />

		<style>
		body { padding-top: 60px; }
		.error { color: red; }
		</style>

	</head>
	<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<c:url value="/" var="homeUrl" />
				<a class="brand" href="${homeUrl}">Project</a>
				<%@ include file="/WEB-INF/jsp/menu.jsp" %>
				<div class="pull-right">
					<c:set var="localeCode" value="${pageContext.response.locale}" />
					<c:set var="availLanguages" value="tr_TR,en_US" />
					<c:if test="${!fn:contains(availLanguages,localeCode)}">
						<c:set var="localeCode" value="tr_TR" />
					</c:if>
					<c:forEach items="${availLanguages}" var="lang">
						<a href="?language=${lang}" class="label <c:if test="${fn:contains(localeCode, lang)}">label-info</c:if>">${lang}</a>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>