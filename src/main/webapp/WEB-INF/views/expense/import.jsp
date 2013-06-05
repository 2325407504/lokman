<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="expenseList" value="/expense/list" />
<spring:url var="expenseNew" value="/expense/new" />
<spring:url var="expenseImportXLSX" value="/expense/import/xls" />
<spring:url var="expenseImportCSV" value="/expense/import/csv" />
<spring:url var="expenseExport" value="/expense/export/xls" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${expenseList}"><spring:message code="Expenses"></spring:message></a></li>
	<li class=""><a href="${expenseNew}"><spring:message code="New Entry"></spring:message></a></li>
	<li class="active"><a href="${expenseImportXLSX}"><spring:message code="Import"></spring:message></a></li>
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

<div class="bs-docs-example" data-content="<spring:message code="XLSX"></spring:message>">
	<form:form modelAttribute="fileUploadBean" action="${expenseImportXLSX}" method="post" enctype="multipart/form-data">
		<form:errors cssClass="error-field" path="file" />
		<form:input path="file" type="file"/>
		<button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload"></spring:message></button>
	</form:form>
	<div class="bs-docs-example" data-content="<spring:message code="Example"></spring:message>">
		<table class="table">
			<thead>
				<tr>
					<th><spring:message code="Account"></spring:message></th>
					<th><spring:message code="Date"></spring:message></th>
					<th><spring:message code="Company"></spring:message></th>
					<th><spring:message code="Description"></spring:message></th>
					<th><spring:message code="Amount"></spring:message></th>
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
					<td>121.34</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div class="bs-docs-example hide" data-content="<spring:message code="CSV"></spring:message>">
	<form:form modelAttribute="csvImportBean" action="${expenseImportCSV}" method="post" enctype="multipart/form-data">
		<div class="row-fluid">
			<form:errors cssClass="error-field" path="content"/>
			<form:textarea path="content" cssClass="span12" rows="5"/>
			<button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload"></spring:message></button>
		</div>
	</form:form>
	<div class="bs-docs-example" data-content="<spring:message code="Example"></spring:message>">
		<pre>
user1,11.02.2013 21:03,company1,desc1,11
user2,21.02.2013 01:12,company2,desc2,12.1
user3,05.03.2013 14:34,company3,desc3,13.3
		</pre>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>