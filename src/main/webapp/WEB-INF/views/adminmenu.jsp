<%@include file="/WEB-INF/views/includes.jsp" %>

<c:set var="uri" value="${requestScope['javax.servlet.forward.request_uri']}" />

<spring:url value="/role/list" var="role_list" />
<c:if test="${uri == role_list}"><c:set var="role_class" value="active" /></c:if>
<spring:url value="/member/list" var="member_list" />
<c:if test="${uri == member_list}"><c:set var="member_class" value="active" /></c:if>
<spring:url value="/backup/list" var="backup_list" />
<c:if test="${uri == backup_list}"><c:set var="backup_class" value="active" /></c:if>
<spring:url value="/memberlog/list" var="memberlog_list" />
<c:if test="${uri == memberlog_list}"><c:set var="memberlog_class" value="active" /></c:if>

<spring:url value="/proc/list" var="proc_list" />
<c:if test="${uri == proc_list}"><c:set var="proc_class" value="active" /></c:if>
<spring:url value="/customer/list" var="customer_list" />
<c:if test="${uri == customer_list}"><c:set var="customer_class" value="active" /></c:if>
<spring:url value="/expensetype/list" var="expensetype_list" />
<c:if test="${uri == expensetype_list}"><c:set var="expensetype_class" value="active" /></c:if>
<spring:url value="/paymenttype/list" var="paymenttype_list" />
<c:if test="${uri == paymenttype_list}"><c:set var="paymenttype_class" value="active" /></c:if>
<spring:url value="/employeeleavetype/list" var="employeeleavetype_list" />
<c:if test="${uri == employeeleavetype_list}"><c:set var="employeeleavetype_class" value="active" /></c:if>
<spring:url value="/employeeworkinghourtype/list" var="employeeworkinghourtype_list" />
<c:if test="${uri == employeeworkinghourtype_list}"><c:set var="employeeworkinghourtype_class" value="active" /></c:if>
<spring:url value="/startingpoint/list" var="startingpoint_list" />
<c:if test="${uri == startingpoint_list}"><c:set var="startingpoint_class" value="active" /></c:if>
<spring:url value="/endingpoint/list" var="endingpoint_list" />
<c:if test="${uri == endingpoint_list}"><c:set var="endingpoint_class" value="active" /></c:if>

<spring:url value="/region/list" var="region_list" />
<c:if test="${uri == region_list}"><c:set var="region_class" value="active" /></c:if>
<spring:url value="/truck/list" var="truck_list" />
<c:if test="${uri == truck_list}"><c:set var="truck_class" value="active" /></c:if>
<spring:url value="/driver/list" var="driver_list" />
<c:if test="${uri == driver_list}"><c:set var="driver_class" value="active" /></c:if>
<spring:url value="/subcontractor/list" var="subcontractor_list" />
<c:if test="${uri == subcontractor_list}"><c:set var="subcontractor_class" value="active" /></c:if>
<spring:url value="/quota/list" var="quota_list" />
<c:if test="${uri == quota_list}"><c:set var="quota_class" value="active" /></c:if>

<spring:url value="/productgroup/list" var="productgroup_list" />
<c:if test="${uri == productgroup_list}"><c:set var="productgroup_class" value="active" /></c:if>
<spring:url value="/product/list" var="product_list" />
<c:if test="${uri == product_list}"><c:set var="product_class" value="active" /></c:if>
<spring:url value="/electricmeter/list" var="electricmeter_list" />
<c:if test="${uri == electricmeter_list}"><c:set var="electricmeter_class" value="active" /></c:if>
<spring:url value="/machine/list" var="machine_list" />
<c:if test="${uri == machine_list}"><c:set var="machine_class" value="active" /></c:if>

<spring:url value="/wastegroup/list" var="wastegroup_list" />
<c:if test="${uri == wastegroup_list}"><c:set var="wastegroup_class" value="active" /></c:if>
<spring:url value="/waste/list" var="waste_list" />
<c:if test="${uri == waste_list}"><c:set var="waste_class" value="active" /></c:if>

<spring:url value="/expense/list" var="expense_list" />
<c:if test="${uri == expense_list}"><c:set var="expense_class" value="active" /></c:if>
<spring:url value="/payment/list" var="payment_list" />
<c:if test="${uri == payment_list}"><c:set var="payment_class" value="active" /></c:if>
<spring:url value="/employee/list" var="employee_list" />
<c:if test="${uri == employee_list}"><c:set var="employee_class" value="active" /></c:if>
<spring:url value="/employeeleave/list" var="employeeleave_list" />
<c:if test="${uri == employeeleave_list}"><c:set var="employeeleave_class" value="active" /></c:if>
<spring:url value="/employeeworkinghour/list" var="employeeworkinghour_list" />
<c:if test="${uri == employeeworkinghour_list}"><c:set var="employeeworkinghour_class" value="active" /></c:if>
<spring:url value="/invoice/list" var="invoice_list" />
<c:if test="${uri == invoice_list}"><c:set var="invoice_class" value="active" /></c:if>
<spring:url value="/waybill/list" var="waybill_list" />
<c:if test="${uri == waybill_list}"><c:set var="waybill_class" value="active" /></c:if>

<spring:url value="/forwarding/list" var="forwarding_list" />
<c:if test="${uri == forwarding_list}"><c:set var="forwarding_class" value="active" /></c:if>

<spring:url value="/production/list" var="production_list" />
<c:if test="${uri == production_list}"><c:set var="production_class" value="active" /></c:if>

<spring:url value="/weighbridge/list" var="weighbridge_list" />
<c:if test="${uri == weighbridge_list}"><c:set var="weighbridge_class" value="active" /></c:if>

    <div class="dropdown-menu pull-right">
        <table class="table">
            <caption></caption>
            <tbody>
                <tr>
                    <td>
                        <ul class="unstyled">
                        <sec:authorize access="hasRole('ROLE_SUPERADMIN')">
                            <li class="nav-header"><spring:message code="User Management" /></li>
                            <li class="${role_class}"><a href="${role_list}"><spring:message code="Roles" /></a></li>
                            <li class="${member_class}"><a href="${member_list}"><spring:message code="Members" /></a></li>
                            <li class="${backup_class}"><a href="${backup_list}"><spring:message code="Backup" /></a></li>
                            <li class="${memberlog_class}"><a href="${memberlog_list}"><spring:message code="Memberlogs" /></a></li>
                            </sec:authorize>
                    </ul>
                </td>
                <td>
                    <ul class="unstyled">
                        <li class="nav-header"><spring:message code="Settings" /></li>
                        <li class="${proc_class}"><a href="${proc_list}"><spring:message code="Procedures" /></a></li>
                        <li class="${customer_class}"><a href="${customer_list}"><spring:message code="Customers" /></a></li>
                        <li class="${expensetype_class}"><a href="${expensetype_list}"><spring:message code="Expensetypes" /></a></li>
                        <li class="${paymenttype_class}"><a href="${paymenttype_list}"><spring:message code="Paymenttypes" /></a></li>
                        <li class="${employeeleavetype_class}"><a href="${employeeleavetype_list}"><spring:message code="Employee Leave Types" /></a></li>
                        <li class="${employeeworkinghourtype_class}"><a href="${employeeworkinghourtype_list}"><spring:message code="Employee Working Hour Types" /></a></li>
                        <li class="${startingpoint_class}"><a href="${startingpoint_list}"><spring:message code="Starting Points" /></a></li>
                        <li class="${endingpoint_class}"><a href="${endingpoint_list}"><spring:message code="Ending Points" /></a></li>
                            <sec:authorize access="hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_OTL'))">
                            <li class="${region_class}"><a href="${region_list}"><spring:message code="Regions" /></a></li>
                            <li class="${truck_class}"><a href="${truck_list}"><spring:message code="Trucks" /></a></li>
                            <li class="${driver_class}"><a href="${driver_list}"><spring:message code="Drivers" /></a></li>
                            <li class="${subcontractor_class}"><a href="${subcontractor_list}"><spring:message code="Subcontractors" /></a></li>
                            <li class="${quota_class}"><a href="${quota_list}"><spring:message code="Quotas" /></a></li>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_URE'))">
                            <li class="${productgroup_class}"><a href="${productgroup_list}"><spring:message code="Product Groups" /></a></li>
                            <li class="${product_class}"><a href="${product_list}"><spring:message code="Products" /></a></li>
                            <li class="${electricmeter_class}"><a href="${electricmeter_list}"><spring:message code="Electricmeters" /></a></li>
                            <li class="${machine_class}"><a href="${machine_list}"><spring:message code="Machines" /></a></li>
                            </sec:authorize>
                            <sec:authorize access="hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_ATY'))">
                            <li class="${wastegroup_class}"><a href="${wastegroup_list}"><spring:message code="Waste Groups" /></a></li>
                            <li class="${waste_class}"><a href="${waste_list}"><spring:message code="Wastes" /></a></li>
                            </sec:authorize>
                    </ul>
                </td>
                <td>
                    <ul class="unstyled">
                        <sec:authorize access="hasRole('ROLE_SUPERADMIN') or hasRole('ROLE_ADMIN')">
                            <li class="nav-header"><spring:message code="Personnel" /></li>
                            <li class="${expense_class}"><a href="${expense_list}"><spring:message code="Expenses" /></a></li>
                            <li class="${payment_class}"><a href="${payment_list}"><spring:message code="Payments" /></a></li>
                            <li class="${employee_class}"><a href="${employee_list}"><spring:message code="Employees" /></a></li>
                            <li class="${employeeleave_class}"><a href="${employeeleave_list}"><spring:message code="Employee Leaves" /></a></li>
                            <li class="${employeeworkinghour_class}"><a href="${employeeworkinghour_list}"><spring:message code="Employee Working Hours" /></a></li>
                            <li class="nav-header"><spring:message code="Accounting" /></li>
                            <li class="${invoice_class}"><a href="${invoice_list}"><spring:message code="Invoices" /></a></li>
                            <li class="${waybill_class}"><a href="${waybill_list}"><spring:message code="Waybills" /></a></li>
                            </sec:authorize>
                    </ul>
                </td>
                <td>
                    <ul class="unstyled">
                        <sec:authorize access="hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_OTL'))">
                            <li class="nav-header"><spring:message code="Logistics" /></li>
                            <li class="${forwarding_class}"><a href="${forwarding_list}"><spring:message code="Forwardings" /></a></li>
                            </sec:authorize>
                    </ul>
                </td>
                <td>
                    <ul class="unstyled">
                        <sec:authorize access="hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_URE'))">
                            <li class="nav-header"><spring:message code="Production" /></li>
                            <li class="${production_class}"><a href="${production_list}"><spring:message code="Production" /></a></li>
                            </sec:authorize>
                    </ul>
                </td>
                <td>
                    <ul class="unstyled">
                        <sec:authorize access="hasRole('ROLE_SUPERADMIN') or (hasRole('ROLE_ADMIN') and hasRole('ROLE_ATY'))">
                            <li class="nav-header"><spring:message code="Waste" /></li>
                            <li class="${weighbridge_class}"><a href="${weighbridge_list}"><spring:message code="Weighbridges" /></a></li>
                            </sec:authorize>
                    </ul>
                </td>
            </tr>
        </tbody>
    </table>
</div>