<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<spring:url value="/" var="homeUrl" />
<spring:url value="/login" var="loginUrl" />
<spring:url value="/logout" var="logoutUrl" />
<spring:url value="/profile/show" var="profile_show" />
<spring:url value="/role/list" var="role_list" />
<spring:url value="/account/list" var="account_list" />
<spring:url value="/truck/list" var="truck_list" />
<spring:url value="/driver/list" var="driver_list" />
<spring:url value="/subcontractor/list" var="subcontractor_list" />
<spring:url value="/quota/list" var="quota_list" />

<spring:url value="/trip/list" var="trip_tracking_list" />
<spring:url value="/forwarding/list" var="forwarding_list" />

    
<div class="well">
	
	<div class="accordion" id="accordion2">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle nav-header" data-toggle="collapse" data-parent="#accordion2" href="#collapse1">
						<spring:message code="Settings"></spring:message>
					</a>
				</div>
				<div id="collapse1" class="accordion-body in collapse">
					<div class="accordion-inner">
						<ul class="unstyled">
							<sec:authorize access="hasRole('ROLE_SUPERADMIN')">
							<li><a href="${role_list}"><spring:message code="Roles"></spring:message></a></li>
							<li><a href="${account_list}"><spring:message code="Accounts"></spring:message></a></li>
							</sec:authorize>
							<li><a href="${truck_list}"><spring:message code="Trucks"></spring:message></a></li>
							<li><a href="${driver_list}"><spring:message code="Drivers"></spring:message></a></li>
							<li><a href="${subcontractor_list}"><spring:message code="Subcontractors"></spring:message></a></li>
							<li><a href="${quota_list}"><spring:message code="Quotas"></spring:message></a></li>
						</ul>
					</div>
				</div>
			</div>
		</sec:authorize>
		<sec:authorize access="hasRole('ROLE_USER')">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle nav-header" data-toggle="collapse" data-parent="#accordion2" href="#collapse2">
						Logistics
					</a>
				</div>
				<div id="collapse2" class="accordion-body in collapse">
					<div class="accordion-inner">
						<ul class="unstyled">
							<li><a href="${trip_tracking_list}"><spring:message code="Trip Tracking" text="Trip Tracking"></spring:message></a></li>
							<li><a href="${forwarding_list}"><spring:message code="Forwarding Tracking" text="Forwarding Tracking"></spring:message></a></li>
						</ul>
					</div>
				</div>
			</div>
		</sec:authorize>
	</div>
	
</div>