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

        <script type="text/javascript" src="resources/redactor/redactor.js"></script>
        <link rel="stylesheet" href="resources/redactor/redactor.css" />

        <script type="text/javascript" src="resources/js/master.js"></script>
        <link rel="stylesheet" href="resources/css/master.css" />

    </head>
    <body>


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
                            <li>
                                <a href="#" data-toggle="popover" data-placement="bottom" data-content="Teknik: bilgi@aripd.com" title="" data-original-title="<spring:message code="Help" />"><spring:message code="Help" /></a>
                            </li>
                            <sec:authorize access="isAuthenticated()">
                                <sec:authorize access="hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')">
                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                            <spring:message code="Admin" text="Admin" /><b class="caret"></b>
                                        </a>
                                        <jsp:include page="/WEB-INF/views/adminmenu.jsp">
                                            <jsp:param name="uri" value="${requestScope['javax.servlet.forward.request_uri']}" />
                                        </jsp:include>
                                    </li>
                                </sec:authorize>
                                <spring:url value="/profile/show" var="profile_show" />
                                <c:if test="${requestScope['javax.servlet.forward.request_uri'] == profile_show}"><c:set var="profile_class" value="active" /></c:if>
                                <li class="${profile_class}">
                                    <a href="${profile_show}"><spring:message code="Profile" /></a>
                                </li>
                                <li>
                                    <spring:url value="/logout" var="logoutUrl" />
                                    <a href="${logoutUrl}"><spring:message code="Logout" /> (<%= request.getUserPrincipal().getName()%>)</a>
                                </li>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                            </sec:authorize>
                            <li class="dropdown">
                                <aripd:language title="Language" />
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
                        <jsp:include page="/WEB-INF/views/usermenu.jsp">
                            <jsp:param name="uri" value="${requestScope['javax.servlet.forward.request_uri']}" />
                        </jsp:include>
                    </div>
                    <div class="span9">
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <div class="span12">
                        </sec:authorize>