<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="aripd" tagdir="/WEB-INF/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		
		<link rel="stylesheet" href="//code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.1/css/bootstrap-combined.min.css" />
		<link rel="stylesheet" href="/gelibolu/resources/css/master.css" />

		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
		<script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.2.2/bootstrap.min.js"></script>
		<script type="text/javascript" src="/gelibolu/resources/js/master.js"></script>
		
		<!-- jquery-ui-timepicker -->
		<link rel="stylesheet" href="/gelibolu/resources/jquery-ui-timepicker/jquery-ui-timepicker-addon.css" />
		<script type="text/javascript" src="/gelibolu/resources/jquery-ui-timepicker/jquery-ui-timepicker-addon.js"></script>
		<script type="text/javascript" src="/gelibolu/resources/jquery-ui-timepicker/jquery-ui-timepicker-tr.js"></script>
		<script type="text/javascript" src="/gelibolu/resources/js/ui.datepicker-tr.js"></script>
		
	</head>
	<body>
	
	<spring:url value="/" var="homeUrl" />
	<spring:url value="/login" var="loginUrl" />
	<spring:url value="/logout" var="logoutUrl" />
	<spring:url value="/profile/show" var="profile_show" />
	
	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<spring:url value="/" var="homeUrl" />
				<a class="brand" href="${homeUrl}"><img alt="" src="http://lgk.com.tr/deco/lokmanlogo.png"></a>
				<div class="nav-collapse collapse pull-right">
					<ul class="nav">
						<sec:authorize access="isAuthenticated()">
						<li><a href="${profile_show}"><spring:message code="Profile"></spring:message></a></li>
						<li><a href="${logoutUrl}"><spring:message code="Logout"></spring:message> (<%= request.getUserPrincipal().getName() %>)</a></li>
						</sec:authorize>
						<sec:authorize access="isAnonymous()">
						<li><a href="${loginUrl}"><spring:message code="Login"></spring:message></a></li>
						</sec:authorize>
						<li class="dropdown">
							<c:set var="localeCode" value="${pageContext.response.locale}" />
							<c:set var="availLanguages" value="tr_TR,en_US" />
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="Language" text="Language"></spring:message>: ${localeCode}<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<c:if test="${!fn:contains(availLanguages,localeCode)}">
									<c:set var="localeCode" value="tr_TR" />
								</c:if>
								<c:forEach items="${availLanguages}" var="lang">
									<li><a href="?language=${lang}">${lang} <c:if test="${fn:contains(localeCode, lang)}"><i class="icon-ok"></i></c:if></a></li>
								</c:forEach>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<sec:authorize access="isAuthenticated()">
			<div class="span3">
				<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
			</div>
			<div class="span9">
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
			<div class="span12">
			</sec:authorize>