<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:url value="/" var="homeUrl" />
<c:url value="/login" var="loginUrl" />
<c:url value="/logout" var="logoutUrl" />
<c:url value="/profile/show" var="profile_show" />
<c:url value="/role/list" var="role_list" />
<c:url value="/user/list" var="user_list" />
<c:url value="/truck/list" var="truck_list" />
<c:url value="/driver/list" var="driver_list" />

<div class="nav-collapse collapse">
	<ul class="nav">
		<li class="active"><a href="${homeUrl}"><i class="icon-home"></i> <spring:message code="Home"></spring:message></a></li>

		<sec:authorize access="isAuthenticated()">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
		<li><a href="${role_list}"><spring:message code="label.roles"></spring:message></a></li>
		<li><a href="${user_list}"><spring:message code="label.users"></spring:message></a></li>
		<li><a href="${truck_list}"><spring:message code="Trucks"></spring:message></a></li>
		<li><a href="${driver_list}"><spring:message code="Drivers"></spring:message></a></li>
		</sec:authorize>
		<li><a href="${profile_show}"><spring:message code="Profile"></spring:message></a></li>
		<li><a href="${logoutUrl}"><spring:message code="button.logout"></spring:message> (<%= request.getUserPrincipal().getName() %>)</a></li>
		</sec:authorize>

		<sec:authorize access="isAnonymous()">
		<li><a href="${loginUrl}"><spring:message code="button.login"></spring:message></a></li>
		</sec:authorize>
	</ul>
</div>