<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="expenseList" value="/expense/list" />
<spring:url var="expenseNew" value="/expense/new" />
<spring:url var="expenseImport" value="/expense/import/xls" />
<spring:url var="expenseExport" value="/expense/export/xls" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${expenseList}"><spring:message code="Expenses"></spring:message></a></li>
	<li class=""><a href="${expenseNew}"><spring:message code="New Entry"></spring:message></a></li>
	<li class="active"><a href="${expenseImport}"><spring:message code="Import"></spring:message></a></li>
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

<div class="bs-docs-example" data-content="<spring:message code="Import"></spring:message>">
	<form:form modelAttribute="fileUploadBean" action="${expenseImport}" method="post" enctype="multipart/form-data">
		<form:errors cssClass="error-field" path="file" />
		<form:input path="file" type="file"/>
		<button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload"></spring:message></button>
	</form:form>
</div>
	
<div class="bs-docs-example" data-content="<spring:message code="Example"></spring:message>">
	<table class="table">
		<thead>
			<tr>
				<th>Username</th>
				<th>Document Date</th>
				<th>Company</th>
				<th>Description</th>
				<th>Amount</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>user1</td>
				<td>11.02.2013 21:03</td>
				<td>company1</td>
				<td>description1</td>
				<td>11</td>
			</tr>
			<tr>
				<td>user2</td>
				<td>01.03.2013 12:13</td>
				<td>company2</td>
				<td>description2</td>
				<td>121,34</td>
			</tr>
		</tbody>
	</table>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>