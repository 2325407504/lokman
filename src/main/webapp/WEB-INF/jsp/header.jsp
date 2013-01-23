<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<link rel="stylesheet" href="//code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
		<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.1/css/bootstrap-combined.min.css" rel="stylesheet">
		<link rel="stylesheet" href="/gelibolu/resources/dataTables/css/demo_page.css" />
		<link rel="stylesheet" href="/gelibolu/resources/dataTables/css/demo_table.css" />
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
					<a href="?language=en_US">English</a>
					<a href="?language=tr_TR">Türkçe</a>
				</div>
			</div>
		</div>
	</div>