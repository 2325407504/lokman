<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/forwarding/list" />
<spring:url var="forwardingNew" value="/forwarding/new" />
<spring:url var="forwardingImport" value="/forwarding/import/xls" />
<spring:url var="forwardingExport" value="/forwarding/export/xls" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${forwardingList}"><spring:message code="Forwardings"></spring:message></a></li>
	<li class=""><a href="${forwardingNew}"><spring:message code="New Entry"></spring:message></a></li>
	<li class="active"><a href="${forwardingImport}"><spring:message code="Import"></spring:message></a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="Export"></spring:message>
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li><a href="${forwardingExport}"><spring:message code="Forwardings"></spring:message></a></li>
		</ul>
	</li>
</ul>

<div class="bs-docs-example" data-content="<spring:message code="Import"></spring:message>">
	<form:form modelAttribute="fileUploadBean" action="${forwardingImport}" method="post" enctype="multipart/form-data">
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
				<th>Waybill No</th>
				<th>Uatf</th>
				<th>Driver</th>
				<th>Plate</th>
				<th>Starting Time</th>
				<th>Ending Time</th>
				<th>Ending Point</th>
				<th>loadWeightInTonne</th>
				<th>Shipping Cost</th>
				<th>Subcontractor</th>
				<th>Quota</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>user1</td>
				<td>waybillno1</td>
				<td>uatf11</td>
				<td>driver1</td>
				<td>plate1</td>
				<td>11.02.2013 21:03</td>
				<td>11.02.2013 22:22</td>
				<td>ending point1</td>
				<td>5</td>
				<td>110</td>
				<td>subcontractorCode1</td>
				<td>quotaCode1</td>
			</tr>
			<tr>
				<td>user1</td>
				<td>waybillno1</td>
				<td>uatf12</td>
				<td>driver1</td>
				<td>plate1</td>
				<td>11.02.2013 21:03</td>
				<td>11.02.2013 22:22</td>
				<td>ending point1</td>
				<td>5</td>
				<td>110</td>
				<td>subcontractorCode1</td>
				<td>quotaCode1</td>
			</tr>
			<tr>
				<td>user2</td>
				<td>waybillno2</td>
				<td>uatf21</td>
				<td>driver2</td>
				<td>plate2</td>
				<td>09.02.2013 12:12</td>
				<td>10.02.2013 14:13</td>
				<td>ending point2</td>
				<td>7</td>
				<td>80</td>
				<td>subcontractorCode2</td>
				<td>quotaCode2</td>
			</tr>
		</tbody>
	</table>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>