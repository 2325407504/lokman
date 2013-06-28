<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="customerList" value="/customer/list" />
<spring:url var="customerNew" value="/customer/new" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${customerList}"><spring:message code="Customers" /></a></li>
    <li class=""><a href="${customerNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:datatables datasource="/customer/get" id="customers" dataUrlShow="/customer/show" dataUrlEdit="/customer/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Tax No" field="taxNo"/>
    <aripd:column label="Fullname" field="name"/>
    <aripd:column label="Phone Number" field="phonenumber"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />