<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="truckList" value="/truck/list" />
<spring:url var="truckNew" value="/truck/new" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${truckList}"><spring:message code="Trucks" /></a></li>
    <li class=""><a href="${truckNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:datatables datasource="/truck/get" id="trucks" dataUrlShow="/truck/show" dataUrlEdit="/truck/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Region" field="region.name"/>
    <aripd:column label="Plate" field="plate"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>