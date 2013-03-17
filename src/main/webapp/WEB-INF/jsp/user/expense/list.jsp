<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="expenseList" value="/user/expense/list" />
<spring:url var="expenseNew" value="/user/expense/new" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class="active"><a href="${expenseList}"><spring:message code="Expenses"></spring:message></a></li>
	<li class=""><a href="${expenseNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<aripd:datatables datasource="/user/expense/get" id="expenses" dataUrlShow="/user/expense/show" dataUrlEdit="/user/expense/edit">
	<aripd:column label="Action" field="id"/>
	<aripd:column label="Date" field="documentDate"/>
	<aripd:column label="Company" field="company"/>
	<aripd:column label="Description" field="description"/>
	<aripd:column label="Amount" field="amount"/>
</aripd:datatables>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>