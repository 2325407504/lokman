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
<spring:url value="/backup/list" var="backup_list" />
<spring:url value="/region/list" var="region_list" />
<spring:url value="/product/list" var="product_list" />
<spring:url value="/electricmeter/list" var="electricmeter_list" />
<spring:url value="/shift/list" var="shift_list" />
<spring:url value="/truck/list" var="truck_list" />
<spring:url value="/driver/list" var="driver_list" />
<spring:url value="/subcontractor/list" var="subcontractor_list" />
<spring:url value="/quota/list" var="quota_list" />

<spring:url value="/expense/list" var="expense_list" />
<spring:url value="/trip/list" var="trip_list" />
<spring:url value="/forwarding/list" var="forwarding_list" />
<spring:url value="/production/list" var="production_list" />

<spring:url value="/user/expense/list" var="user_expense_list" />
<spring:url value="/user/trip/list" var="user_trip_list" />
<spring:url value="/user/forwarding/list" var="user_forwarding_list" />

<div class="well">

    <div class="accordion" id="accordion2">
        <sec:authorize access="hasAnyRole('ROLE_SUPERADMIN')">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle nav-header" data-toggle="collapse" data-parent="#accordion2" href="#collapse1">
                        Süper admin
                    </a>
                </div>
                <div id="collapse1" class="accordion-body in collapse">
                    <div class="accordion-inner">
                        <ul class="nav nav-list">
                            <li class="nav-header"><spring:message code="User Management"></spring:message></li>
                            <li><a href="${role_list}"><spring:message code="Roles"></spring:message></a></li>
                            <li><a href="${account_list}"><spring:message code="Accounts"></spring:message></a></li>
                            <li><a href="${backup_list}"><spring:message code="Backup"></spring:message></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle nav-header" data-toggle="collapse" data-parent="#accordion2" href="#collapse2">
                        <spring:message code="Admin"></spring:message>
                        </a>
                    </div>
                    <div id="collapse2" class="accordion-body in collapse">
                        <div class="accordion-inner">
                            <ul class="nav nav-list">
                                <li class="nav-header"><spring:message code="Settings"></spring:message></li>
                            <li><a href="${region_list}"><spring:message code="Regions"></spring:message></a></li>
                            <li><a href="${product_list}"><spring:message code="Products"></spring:message></a></li>
                            <li><a href="${electricmeter_list}"><spring:message code="Electricmeters"></spring:message></a></li>
                            <li><a href="${shift_list}"><spring:message code="Shifts"></spring:message></a></li>
                            <li><a href="${truck_list}"><spring:message code="Trucks"></spring:message></a></li>
                            <li><a href="${driver_list}"><spring:message code="Drivers"></spring:message></a></li>
                            <li><a href="${subcontractor_list}"><spring:message code="Subcontractors"></spring:message></a></li>
                            <li><a href="${quota_list}"><spring:message code="Quotas"></spring:message></a></li>
                                <!-- 
                                                                              <li class="nav-header"><spring:message code="Personnel"></spring:message></li>
                                                                              <li><a href="${expense_list}"><spring:message code="Expenses"></spring:message></a></li>
                                -->
                                <li class="nav-header"><spring:message code="Logistics"></spring:message></li>
                            <li><a href="${trip_list}"><spring:message code="Trips"></spring:message></a></li>
                            <li><a href="${forwarding_list}"><spring:message code="Forwardings"></spring:message></a></li>
                            <li class="nav-header"><spring:message code="Production"></spring:message></li>
                            <li><a href="${production_list}"><spring:message code="Production"></spring:message></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_USER')">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle nav-header" data-toggle="collapse" data-parent="#accordion2" href="#collapse3">
                        <spring:message code="User"></spring:message>
                        </a>
                    </div>
                    <div id="collapse3" class="accordion-body in collapse">
                        <div class="accordion-inner">
                            <ul class="nav nav-list">
                                <!-- 
                                <li class="nav-header"><spring:message code="Personnel"></spring:message></li>
                                <li><a href="${user_expense_list}"><spring:message code="Expenses"></spring:message></a></li>
                                -->
                                <li class="nav-header"><spring:message code="Logistics"></spring:message></li>
                            <li><a href="${user_trip_list}"><spring:message code="Trips"></spring:message></a></li>
                            <li><a href="${user_forwarding_list}"><spring:message code="Forwardings"></spring:message></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
        </sec:authorize>
    </div>

</div>