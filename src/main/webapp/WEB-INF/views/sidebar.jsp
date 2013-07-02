<%@include file="/WEB-INF/views/includes.jsp" %>

<spring:url value="/role/list" var="role_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == role_list}"><c:set var="role_class" value="active" /></c:if>
<spring:url value="/account/list" var="account_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == account_list}"><c:set var="account_class" value="active" /></c:if>
<spring:url value="/backup/list" var="backup_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == backup_list}"><c:set var="backup_class" value="active" /></c:if>
<spring:url value="/region/list" var="region_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == region_list}"><c:set var="region_class" value="active" /></c:if>
<spring:url value="/productgroup/list" var="productgroup_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == productgroup_list}"><c:set var="productgroup_class" value="active" /></c:if>
<spring:url value="/product/list" var="product_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == product_list}"><c:set var="product_class" value="active" /></c:if>
<spring:url value="/wastegroup/list" var="wastegroup_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == wastegroup_list}"><c:set var="wastegroup_class" value="active" /></c:if>
<spring:url value="/waste/list" var="waste_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == waste_list}"><c:set var="waste_class" value="active" /></c:if>
<spring:url value="/electricmeter/list" var="electricmeter_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == electricmeter_list}"><c:set var="electricmeter_class" value="active" /></c:if>
<spring:url value="/machine/list" var="machine_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == machine_list}"><c:set var="machine_class" value="active" /></c:if>
<spring:url value="/expensetype/list" var="expensetype_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == expensetype_list}"><c:set var="expensetype_class" value="active" /></c:if>
<spring:url value="/truck/list" var="truck_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == truck_list}"><c:set var="truck_class" value="active" /></c:if>
<spring:url value="/driver/list" var="driver_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == driver_list}"><c:set var="driver_class" value="active" /></c:if>
<spring:url value="/subcontractor/list" var="subcontractor_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == subcontractor_list}"><c:set var="subcontractor_class" value="active" /></c:if>
<spring:url value="/quota/list" var="quota_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == quota_list}"><c:set var="quota_class" value="active" /></c:if>
<spring:url value="/customer/list" var="customer_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == customer_list}"><c:set var="customer_class" value="active" /></c:if>

<spring:url value="/expense/list" var="expense_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == expense_list}"><c:set var="expense_class" value="active" /></c:if>
<spring:url value="/trip/list" var="trip_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == trip_list}"><c:set var="trip_class" value="active" /></c:if>
<spring:url value="/forwarding/list" var="forwarding_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == forwarding_list}"><c:set var="forwarding_class" value="active" /></c:if>
<spring:url value="/production/list" var="production_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == production_list}"><c:set var="production_class" value="active" /></c:if>
<spring:url value="/waybill/list" var="waybill_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == waybill_list}"><c:set var="waybill_class" value="active" /></c:if>
<spring:url value="/weighbridge/list" var="weighbridge_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == weighbridge_list}"><c:set var="weighbridge_class" value="active" /></c:if>

<spring:url value="/user/expense/list" var="user_expense_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == user_expense_list}"><c:set var="user_expense_class" value="active" /></c:if>
<spring:url value="/user/trip/list" var="user_trip_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == user_trip_list}"><c:set var="user_trip_class" value="active" /></c:if>
<spring:url value="/user/forwarding/list" var="user_forwarding_list" />
<c:if test="${requestScope['javax.servlet.forward.request_uri'] == user_forwarding_list}"><c:set var="user_forwarding_class" value="active" /></c:if>


<div class="well">

    <div class="accordion" id="accordion2">
        <sec:authorize access="hasAnyRole('ROLE_SUPERADMIN')">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle nav-header" data-toggle="collapse" data-parent="#accordion2" href="#collapse1">
                        S�per admin
                    </a>
                </div>
                <div id="collapse1" class="accordion-body in collapse">
                    <div class="accordion-inner">
                        <ul class="nav nav-list">
                            <li class="nav-header"><spring:message code="User Management" /></li>
                            <li class="${role_class}"><a href="${role_list}"><spring:message code="Roles" /></a></li>
                            <li class="${account_class}"><a href="${account_list}"><spring:message code="Accounts" /></a></li>
                            <li class="${backup_class}"><a href="${backup_list}"><spring:message code="Backup" /></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle nav-header" data-toggle="collapse" data-parent="#accordion2" href="#collapse2">
                        <spring:message code="Admin" />
                    </a>
                </div>
                <div id="collapse2" class="accordion-body in collapse">
                    <div class="accordion-inner">
                        <ul class="nav nav-list">
                            <li class="nav-header"><spring:message code="Settings" /></li>
                            <li class="${region_class}"><a href="${region_list}"><spring:message code="Regions" /></a></li>
                            <li class="${productgroup_class}"><a href="${productgroup_list}"><spring:message code="Product Groups" /></a></li>
                            <li class="${product_class}"><a href="${product_list}"><spring:message code="Products" /></a></li>
                            <li class="${wastegroup_class}"><a href="${wastegroup_list}"><spring:message code="Waste Groups" /></a></li>
                            <li class="${waste_class}"><a href="${waste_list}"><spring:message code="Wastes" /></a></li>
                            <li class="${electricmeter_class}"><a href="${electricmeter_list}"><spring:message code="Electricmeters" /></a></li>
                            <li class="${machine_class}"><a href="${machine_list}"><spring:message code="Machines" /></a></li>
                            <li class="${expensetype_class}"><a href="${expensetype_list}"><spring:message code="Expensetypes" /></a></li>
                            <li class="${truck_class}"><a href="${truck_list}"><spring:message code="Trucks" /></a></li>
                            <li class="${driver_class}"><a href="${driver_list}"><spring:message code="Drivers" /></a></li>
                            <li class="${subcontractor_class}"><a href="${subcontractor_list}"><spring:message code="Subcontractors" /></a></li>
                            <li class="${quota_class}"><a href="${quota_list}"><spring:message code="Quotas" /></a></li>
                            <li class="${customer_class}"><a href="${customer_list}"><spring:message code="Customers" /></a></li>
                            <li class="nav-header"><spring:message code="Personnel" /></li>
                            <li class="${expense_class}"><a href="${expense_list}"><spring:message code="Expenses" /></a></li>
                            <li class="nav-header"><spring:message code="Logistics" /></li>
                            <li class="${trip_class}"><a href="${trip_list}"><spring:message code="Trips" /></a></li>
                            <li class="${forwarding_class}"><a href="${forwarding_list}"><spring:message code="Forwardings" /></a></li>
                            <li class="nav-header"><spring:message code="Production" /></li>
                            <li class="${production_class}"><a href="${production_list}"><spring:message code="Production" /></a></li>
                            <li class="${waybill_class}"><a href="${waybill_list}"><spring:message code="Waybills" /></a></li>
                            <li class="nav-header"><spring:message code="Waste" /></li>
                            <li class="${weighbridge_class}"><a href="${weighbridge_list}"><spring:message code="Weighbridges" /></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_USER')">
            <div class="accordion-group">
                <div class="accordion-heading">
                    <a class="accordion-toggle nav-header" data-toggle="collapse" data-parent="#accordion2" href="#collapse3">
                        <spring:message code="User" />
                    </a>
                </div>
                <div id="collapse3" class="accordion-body in collapse">
                    <div class="accordion-inner">
                        <ul class="nav nav-list">
                            <li class="nav-header"><spring:message code="Personnel" /></li>
                            <li class="${user_expense_class}"><a href="${user_expense_list}"><spring:message code="Expenses" /></a></li>
                            <li class="nav-header"><spring:message code="Logistics" /></li>
                            <li class="${user_trip_class}"><a href="${user_trip_list}"><spring:message code="Trips" /></a></li>
                            <li class="${user_forwarding_class}"><a href="${user_forwarding_list}"><spring:message code="Forwardings" /></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </sec:authorize>
    </div>

</div>