<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="expenseList" value="/user/expense/list" />
<spring:url var="expenseShow" value="/user/expense/show/${expenseAttribute.id}" />
<spring:url var="expenseEdit" value="/user/expense/edit/${expenseAttribute.id}" />
<spring:url var="expenseNew" value="/user/expense/new" />
<spring:url var="expenseSubmit" value="/user/expense/submit/${expenseAttribute.id}" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${expenseList}"><spring:message code="Expenses"></spring:message></a></li>
	<li class="active"><a href="${expenseShow}"><spring:message code="Entry No"></spring:message>: ${expenseAttribute.id}</a></li>
	<li class=""><a href="${expenseNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<c:if test="${expenseAttribute.submitted}">
<div class="alert alert-error"><spring:message code="You cannot edit this record anymore"></spring:message></div>
</c:if>
<c:if test="${!expenseAttribute.submitted}">
<div class="alert alert-info">
	<a href="${expenseEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
	<a href="${expenseSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit"></spring:message></a>
</div>
</c:if>

<div class="row-fluid">
	<div class="span12">
		<ul class="unstyled">
			<li>
				<label class="label">
					<spring:message code="Date"></spring:message>
				</label>
				<spring:eval expression="expenseAttribute.documentDate" />
			</li>
			<li>
				<label class="label">
					<spring:message code="Company"></spring:message>
				</label>
				${expenseAttribute.company}
			</li>
			<li>
				<label class="label">
					<spring:message code="Description"></spring:message>
				</label>
				${expenseAttribute.description}
			</li>
			<li>
				<label class="label">
					<spring:message code="Amount"></spring:message>
				</label>
				${expenseAttribute.amount}
			</li>
		</ul>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>