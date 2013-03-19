<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/trip/list" />
<spring:url var="tripNew" value="/trip/new" />
<spring:url var="tripImport" value="/trip/import/xls" />
<spring:url var="tripExport" value="/trip/export/xls" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${tripList}"><spring:message code="Trips"></spring:message></a></li>
	<li class=""><a href="${tripNew}"><spring:message code="New Entry"></spring:message></a></li>
	<li class="active"><a href="${tripImport}"><spring:message code="Import"></spring:message></a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="Export"></spring:message>
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li><a href="${tripExport}"><spring:message code="Trips"></spring:message></a></li>
		</ul>
	</li>
</ul>

<div class="bs-docs-example" data-content="<spring:message code="Import"></spring:message>">
	<form:form modelAttribute="fileUploadBean" action="${tripImport}" method="post" enctype="multipart/form-data">
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
				<th>Truck Plate</th>
				<th>Driver Name</th>
				<th>Starting Point</th>
				<th>Starting Km</th>
				<th>Starting Time</th>
				<th>Ending Point</th>
				<th>Ending Km</th>
				<th>Ending Time</th>
				<th>loadWeightInTonne</th>
				<th>Remark</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>user1</td>
				<td>plate1</td>
				<td>driver1</td>
				<td>startingPoint1</td>
				<td>123</td>
				<td>11.02.2013 21:03</td>
				<td>endingPoint1</td>
				<td>125</td>
				<td>11.02.2013 22:15</td>
				<td>11</td>
				<td>remark1</td>
			</tr>
			<tr>
				<td>user2</td>
				<td>plate2</td>
				<td>driver2</td>
				<td>startingPoint2</td>
				<td>234</td>
				<td>10.03.2013 20:12</td>
				<td>endingPoint2</td>
				<td>245</td>
				<td>11.03.2013 02:21</td>
				<td>22</td>
				<td>remark2</td>
			</tr>
		</tbody>
	</table>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>