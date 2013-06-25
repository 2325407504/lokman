<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="waybillList" value="/waybill/list" />
<spring:url var="waybillShow" value="/waybill/show/${waybillAttribute.id}" />
<spring:url var="waybillEdit" value="/waybill/edit/${waybillAttribute.id}" />
<spring:url var="waybillSubmit" value="/waybill/submit/${waybillAttribute.id}" />
<spring:url var="waybillNew" value="/waybill/new" />
<spring:url var="waybillImport" value="/waybill/import/xls" />
<spring:url var="waybillExport" value="/waybill/export/xls" />
<spring:url var="outgoingExport" value="/outgoing/export/xls" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${waybillList}"><spring:message code="Waybills"></spring:message></a></li>
	<li class="active"><a href="${waybillShow}"><spring:message code="Entry No"></spring:message>: ${waybillAttribute.id}</a></li>
	<li class=""><a href="${waybillNew}"><spring:message code="New Entry"></spring:message></a></li>
	<li class=""><a href="${waybillImport}"><spring:message code="Import"></spring:message></a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="Export"></spring:message>
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li><a href="${waybillExport}"><spring:message code="Waybill"></spring:message></a></li>
			<li><a href="${outgoingExport}"><spring:message code="Outgoing"></spring:message></a></li>
		</ul>
	</li>
</ul>

<c:if test="${waybillAttribute.submitted}">
<div class="alert alert-error">
	<spring:message code="Submitted by user"></spring:message>
	<a href="${waybillEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
	<a href="${waybillSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back"></spring:message></a>
</div>
</c:if>
<c:if test="${!waybillAttribute.submitted}">
<div class="alert alert-info">
	<a href="${waybillEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
	<a href="${waybillSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit"></spring:message></a>
</div>
</c:if>

<div class="row-fluid">
	<div class="span4">
		<ul class="unstyled">
			<li>
				<label class="label">
					<spring:message code="Account"></spring:message>
				</label>
				${waybillAttribute.account.client.fullname}
			</li>
			<li>
				<label class="label">
					<spring:message code="Waybill No"></spring:message>
				</label>
				${waybillAttribute.waybillNo}
			</li>
			<li>
				<label class="label">
					<spring:message code="Driver"></spring:message>
				</label>
				${waybillAttribute.driver}
			</li>
			<li>
				<label class="label">
					<spring:message code="Plate"></spring:message>
				</label>
				${waybillAttribute.plate}
			</li>
			<li>
				<label class="label">
					<spring:message code="Starting Time"></spring:message>
				</label>
				<spring:eval expression="waybillAttribute.startingTime" />
			</li>
			<li>
				<label class="label">
					<spring:message code="Ending Time"></spring:message>
				</label>
				<spring:eval expression="waybillAttribute.endingTime" />
			</li>
			<li>
				<label class="label">
					<spring:message code="Ending Point"></spring:message>
				</label>
				${waybillAttribute.endingPoint}
			</li>
			<li>
				<label class="label">
					<spring:message code="Weight"></spring:message>
				</label>
				${waybillAttribute.loadWeightInTonne}
			</li>
			<li>
				<label class="label">
					<spring:message code="Shipping Cost"></spring:message>
				</label>
				${waybillAttribute.shippingCost}
			</li>
			<li>
				<label class="label">
					<spring:message code="Subcontractor"></spring:message>
				</label>
				${waybillAttribute.subcontractor.name}
			</li>
			<li>
				<label class="label">
					<spring:message code="Quota"></spring:message>
				</label>
				${waybillAttribute.quota.name}
			</li>
		</ul>
	</div>
	<div class="span8">
		<aripd:datatables datasource="/outgoing/get/${waybillAttribute.id}" id="outgoings">
			<aripd:column label="Code" field="code"/>
			<aripd:column label="Company" field="company"/>
			<aripd:column label="County" field="county"/>
      <aripd:column label="City" field="city"/>
      <aripd:column label="Weight" field="loadWeightInTonne"/>
		</aripd:datatables>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>