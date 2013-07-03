<%@include file="/WEB-INF/views/includes.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="${param.title}" text="${param.title}" /></title>

        <base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

        <script type="text/javascript" src="resources/js/jquery.min.js"></script>

        <link rel="stylesheet" href="resources/css/jquery-ui.min.css" />
        <script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>

        <link rel="stylesheet" href="resources/css/bootstrap.min.css" />
        <link rel="stylesheet" href="resources/css/bootstrap-responsive.min.css" />
        <script src="resources/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="resources/jquery-ui-timepicker/jquery-ui-timepicker-addon.css" />
        <script type="text/javascript" src="resources/jquery-ui-timepicker/jquery-ui-timepicker-addon.js"></script>
        <script type="text/javascript" src="resources/jquery-ui-timepicker/jquery-ui-timepicker-tr.js"></script>
        <script type="text/javascript" src="resources/js/ui.datepicker-tr.js"></script>

        <link rel="stylesheet" href="resources/css/master.css" />

    </head>
    <body>

        <spring:url value="/" var="homeUrl" />
        <spring:url value="/login" var="loginUrl" />
        <spring:url value="/logout" var="logoutUrl" />
        <spring:url value="/profile/show" var="profile_show" />
        <c:if test="${requestScope['javax.servlet.forward.request_uri'] == profile_show}"><c:set var="profile_class" value="active" /></c:if>

            <div class="navbar navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container">
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                    <spring:url value="/" var="homeUrl" />
                    <a class="brand" href="${homeUrl}"><img alt="" src="resources/img/logo.png"></a>
                    <div class="nav-collapse collapse pull-right">
                        <ul class="nav nav-pills">
                            <sec:authorize access="isAuthenticated()">
                                <li class="${profile_class}"><a href="${profile_show}"><spring:message code="Profile" /></a></li>
                                <li>
                                    <a href="${logoutUrl}"><spring:message code="Logout" /> (<%= request.getUserPrincipal().getName()%>)</a>
                                </li>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                            </sec:authorize>
                            <li class="dropdown hide">
                                <c:set var="localeCode" value="${pageContext.response.locale}" />
                                <c:set var="availLanguages" value="tr_TR,en_US" />
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><spring:message code="Language" />: ${localeCode}<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${!fn:contains(availLanguages,localeCode)}">
                                        <c:set var="localeCode" value="tr_TR" />
                                    </c:if>
                                    <c:forEach items="${availLanguages}" var="lang">
                                        <li>
                                            <a href="?language=${lang}">
                                                ${lang}
                                                <c:if test="${fn:contains(localeCode, lang)}">
                                                    <i class="icon-ok"></i>
                                                </c:if>
                                            </a>
                                        </li>
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
                        <jsp:include page="/WEB-INF/views/sidebar.jsp">
                            <jsp:param name="uri" value="${requestScope['javax.servlet.forward.request_uri']}" />
                        </jsp:include>
                    </div>
                    <div class="span9">
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <div class="span12">
                        </sec:authorize>