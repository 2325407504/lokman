<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="expenseList" value="/expense/list" />
<spring:url var="expenseShow" value="/expense/show/${expenseAttribute.id}" />
<spring:url var="expenseEdit" value="/expense/edit/${expenseAttribute.id}" />
<spring:url var="expenseNew" value="/expense/new" />
<spring:url var="expenseImport" value="/expense/import/xls" />
<spring:url var="expenseExport" value="/expense/export/xls" />
<spring:url var="expenseSave" value="/expense/save" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${expenseList}"><spring:message code="Expenses"></spring:message></a></li>
	<c:choose>
		<c:when test="${ !empty expenseAttribute.id }">
			<li class="active"><a href="${expenseEdit}"><spring:message code="Entry No"></spring:message>: ${expenseAttribute.id}</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a href="${expenseNew}"><spring:message code="New Entry"></spring:message></a></li>
		</c:otherwise>
	</c:choose>
	<li class=""><a href="${expenseImport}"><spring:message code="Import"></spring:message></a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="Export"></spring:message>
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li><a href="${expenseExport}"><spring:message code="Expenses"></spring:message></a></li>
		</ul>
	</li>
</ul>

<div class="row-fluid">
	<div class="span12">
		<form:form modelAttribute="expenseAttribute" action="${expenseSave}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<span><form:checkbox path="submitted" /> <spring:message code="Submitted by user"></spring:message></span>
					<form:errors cssClass="error-field" path="submitted" />
				</div>
				<div class="form-row">
					<form:label path="account"><spring:message code="Account"></spring:message></form:label>
					<form:select multiple="false" path="account.id" items="${accounts}" itemLabel="customer.fullname" itemValue="id" />
					<form:errors cssClass="error-field" path="account" />
				</div>
				<div class="form-row">
					<form:label path="documentDate"><spring:message code="Date"></spring:message></form:label>
					<span><form:input type="text" path="documentDate" /></span>
					<form:errors cssClass="error-field" path="documentDate" />
				</div>
				<div class="form-row">
					<form:label path="company"><spring:message code="Company"></spring:message></form:label>
					<span><form:input path="company" /></span>
					<form:errors cssClass="error-field" path="company" />
				</div>
				<div class="form-row">
					<form:label path="description"><spring:message code="Description"></spring:message></form:label>
					<span><form:input path="description" /></span>
					<form:errors cssClass="error-field" path="description" />
				</div>
				<div class="form-row">
					<form:label path="amount"><spring:message code="Amount"></spring:message></form:label>
					<span><form:input path="amount" /></span>
					<form:errors cssClass="error-field" path="amount" />
				</div>
				<div class="form-actions">
					<c:if test="${ !empty expenseAttribute.id }">
					<a class="btn btn-danger" href="javascript:$('#form-${expenseAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete"></spring:message></a>
					</c:if>
					<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>
		
		<c:if test="${ !empty expenseAttribute.id }">
		<spring:url var="deleteUrl" value="/expense/delete?id=${expenseAttribute.id}" />
		<form:form id="form-${expenseAttribute.id}" modelAttribute="expenseAttribute" action="${deleteUrl}" method="delete">
			<form:hidden path="id" />
		</form:form>
		</c:if>
	</div>
</div>

<script>
$(function() {
	$("#documentDate").datetimepicker();
});
</script>

<%@ include file="/WEB-INF/views/footer.jsp" %>