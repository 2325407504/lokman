<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="driverList" value="/driver/list" />
<spring:url var="driverNew" value="/driver/new" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${driverList}"><spring:message code="Drivers" /></a></li>
    <li class=""><a href="${driverNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:datatables datasource="/driver/get" id="drivers" dataUrlShow="/driver/show" dataUrlEdit="/driver/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Region" field="region.name"/>
    <aripd:column label="Code" field="code"/>
    <aripd:column label="Fullname" field="name"/>
    <aripd:column label="Phone Number" field="phonenumber"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />