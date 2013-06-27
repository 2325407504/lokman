<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="shiftList" value="/shift/list" />
<spring:url var="shiftNew" value="/shift/new" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${shiftList}"><spring:message code="Shifts" /></a></li>
    <li class=""><a href="${shiftNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:datatables datasource="/shift/get" id="shifts" dataUrlShow="/shift/show" dataUrlEdit="/shift/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Code" field="code"/>
    <aripd:column label="Name" field="name"/>
</aripd:datatables>

<%@ include file="/WEB-INF/views/footer.jsp" %>