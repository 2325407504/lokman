<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/forwarding/list" />
<spring:url var="forwardingNew" value="/forwarding/new" />
<spring:url var="forwardingImportXLSX" value="/forwarding/import/xls" />
<spring:url var="uatfImportXLSX" value="/uatf/import/xls" />
<spring:url var="forwardingExport" value="/forwarding/export/xls" />
<spring:url var="uatfExport" value="/uatf/export/xls" />
<spring:url var="forwardingImportCSV" value="/forwarding/import/csv" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${forwardingList}"><spring:message code="Forwardings"></spring:message></a></li>
	<li class=""><a href="${forwardingNew}"><spring:message code="New Entry"></spring:message></a></li>
	<li class="active"><a href="${forwardingImportXLSX}"><spring:message code="Import"></spring:message></a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="Export"></spring:message>
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li><a href="${forwardingExport}"><spring:message code="Waybill"></spring:message></a></li>
			<li><a href="${uatfExport}"><spring:message code="UATF"></spring:message></a></li>
		</ul>
	</li>
</ul>

<div class="bs-docs-example" data-content="<spring:message code="XLSX"></spring:message> - <spring:message code="Waybill"></spring:message>">
	<form:form modelAttribute="fileUploadBean" action="${forwardingImportXLSX}" method="post" enctype="multipart/form-data">
		<form:errors cssClass="error-field" path="file" />
		<form:input path="file" type="file"/>
		<button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload"></spring:message></button>
	</form:form>
	<div class="bs-docs-example" data-content="<spring:message code="Example"></spring:message>">
		<table class="table">
			<thead>
				<tr>
					<th><spring:message code="Account"></spring:message></th>
					<th><spring:message code="Waybill"></spring:message></th>
					<th><spring:message code="Driver"></spring:message></th>
					<th><spring:message code="Plate"></spring:message></th>
					<th><spring:message code="Starting Time"></spring:message></th>
					<th><spring:message code="Ending Time"></spring:message></th>
					<th><spring:message code="Ending Point"></spring:message></th>
					<th><spring:message code="Weight"></spring:message></th>
					<th><spring:message code="Shipping Cost"></spring:message></th>
					<th><spring:message code="Subcontractor"></spring:message></th>
					<th><spring:message code="Quota"></spring:message></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>user1</td>
					<td>waybillno1</td>
					<td>driver1</td>
					<td>plate1</td>
					<td>11.02.2013 21:03</td>
					<td>11.02.2013 22:22</td>
					<td>ending point1</td>
					<td>5</td>
					<td>110,2</td>
					<td>UID1</td>
					<td>UID1</td>
				</tr>
				<tr>
					<td>user1</td>
					<td>waybillno1</td>
					<td>driver1</td>
					<td>plate1</td>
					<td>11.02.2013 21:03</td>
					<td>11.02.2013 22:22</td>
					<td>ending point1</td>
					<td>5</td>
					<td>24,54</td>
					<td>UID1</td>
					<td>UID1</td>
				</tr>
				<tr>
					<td>user2</td>
					<td>waybillno2</td>
					<td>driver2</td>
					<td>plate2</td>
					<td>09.02.2013 12:12</td>
					<td>10.02.2013 14:13</td>
					<td>ending point2</td>
					<td>7</td>
					<td>98,7</td>
					<td>UID2</td>
					<td>UID2</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div class="bs-docs-example" data-content="<spring:message code="XLSX"></spring:message> - <spring:message code="UATF"></spring:message>">
	<form:form modelAttribute="fileUploadBean" action="${uatfImportXLSX}" method="post" enctype="multipart/form-data">
		<form:errors cssClass="error-field" path="file" />
		<form:input path="file" type="file"/>
		<button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload"></spring:message></button>
	</form:form>
	<div class="bs-docs-example" data-content="<spring:message code="Example"></spring:message>">
		<table class="table">
			<thead>
				<tr>
					<th><spring:message code="Waybill"></spring:message></th>
					<th><spring:message code="UATF"></spring:message></th>
					<th><spring:message code="Company"></spring:message></th>
					<th><spring:message code="County"></spring:message></th>
          <th><spring:message code="City"></spring:message></th>
          <th><spring:message code="Weight"></spring:message></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>waybillno1</td>
					<td>uatf11</td>
					<td>company11</td>
					<td>county11</td>
          <td>city11</td>
          <td>7</td>
				</tr>
				<tr>
					<td>waybillno1</td>
					<td>uatf12</td>
					<td>company12</td>
					<td>county12</td>
					<td>city12</td>
          <td>11</td>
				</tr>
				<tr>
					<td>waybillno2</td>
					<td>uatf21</td>
					<td>company21</td>
					<td>county21</td>
					<td>city21</td>
          <td>12</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div class="bs-docs-example hide" data-content="<spring:message code="CSV"></spring:message> - <spring:message code="Waybill"></spring:message>">
	<form:form modelAttribute="csvImportBean" action="${forwardingImportCSV}" method="post" enctype="multipart/form-data">
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

<div class="bs-docs-example hide" data-content="<spring:message code="CSV"></spring:message> - <spring:message code="UATF"></spring:message>">
	<form:form modelAttribute="csvImportBean" action="${forwardingImportCSV}" method="post" enctype="multipart/form-data">
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