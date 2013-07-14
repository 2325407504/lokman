<%@include file="/WEB-INF/views/includes.jsp" %>

<c:set var="uri" value="${requestScope['javax.servlet.forward.request_uri']}" />

<spring:url value="/userexpense/list" var="userexpense_list" />
<c:if test="${uri == userexpense_list}"><c:set var="userexpense_class" value="active" /></c:if>
<spring:url value="/usertrip/list" var="usertrip_list" />
<c:if test="${uri == usertrip_list}"><c:set var="usertrip_class" value="active" /></c:if>
<spring:url value="/userforwarding/list" var="userforwarding_list" />
<c:if test="${uri == userforwarding_list}"><c:set var="userforwarding_class" value="active" /></c:if>


    <div class="well">

    <sec:authorize access="hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
        <ul class="nav nav-list">
            <li class="nav-header"><spring:message code="Personnel" /></li>
            <li class="${userexpense_class}"><a href="${userexpense_list}"><spring:message code="Expenses" /></a></li>
                <sec:authorize access="hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_USER') and hasRole('ROLE_OTL'))">
                <li class="nav-header"><spring:message code="Logistics" /></li>
                <li class="hide ${usertrip_class}"><a href="${usertrip_list}"><spring:message code="Trips" /></a></li>
                <li class="${userforwarding_class}"><a href="${userforwarding_list}"><spring:message code="Forwardings" /></a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_USER') and hasRole('ROLE_URE'))">
                <li class="nav-header"><spring:message code="Production" /></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_USER') and hasRole('ROLE_ATY'))">
                <li class="nav-header"><spring:message code="Waste" /></li>
                </sec:authorize>
        </ul>
    </sec:authorize>

</div>