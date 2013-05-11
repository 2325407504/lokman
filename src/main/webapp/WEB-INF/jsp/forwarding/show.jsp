<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/forwarding/list" />
<spring:url var="forwardingShow" value="/forwarding/show/${forwardingAttribute.id}" />
<spring:url var="forwardingEdit" value="/forwarding/edit/${forwardingAttribute.id}" />
<spring:url var="forwardingSubmit" value="/forwarding/submit/${forwardingAttribute.id}" />
<spring:url var="forwardingNew" value="/forwarding/new" />
<spring:url var="forwardingImport" value="/forwarding/import/xls" />
<spring:url var="forwardingExport" value="/forwarding/export/xls" />
<spring:url var="uatfExport" value="/uatf/export/xls" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${forwardingList}"><spring:message code="Forwardings"></spring:message></a></li>
	<li class="active"><a href="${forwardingShow}"><spring:message code="Entry No"></spring:message>: ${forwardingAttribute.id}</a></li>
	<li class=""><a href="${forwardingNew}"><spring:message code="New Entry"></spring:message></a></li>
	<li class=""><a href="${forwardingImport}"><spring:message code="Import"></spring:message></a></li>
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

<c:if test="${forwardingAttribute.submitted}">
<div class="alert alert-error">
	<spring:message code="Submitted by user"></spring:message>
	<a href="${forwardingEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
	<a href="${forwardingSubmit}"><i class="icon-envelope"></i> <spring:message code="Draw Back"></spring:message></a>
</div>
</c:if>
<c:if test="${!forwardingAttribute.submitted}">
<div class="alert alert-info">
	<a href="${forwardingEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a>
	<a href="${forwardingSubmit}"><i class="icon-envelope"></i> <spring:message code="Submit"></spring:message></a>
</div>
</c:if>

<div class="row-fluid">
	<div class="span4">
		<ul class="unstyled">
			<li>
				<label class="label">
					<spring:message code="Account"></spring:message>
				</label>
				${forwardingAttribute.account.customer.fullname}
			</li>
			<li>
				<label class="label">
					<spring:message code="Waybill No"></spring:message>
				</label>
				${forwardingAttribute.waybillNo}
			</li>
			<li>
				<label class="label">
					<spring:message code="Driver"></spring:message>
				</label>
				${forwardingAttribute.driver}
			</li>
			<li>
				<label class="label">
					<spring:message code="Plate"></spring:message>
				</label>
				${forwardingAttribute.plate}
			</li>
			<li>
				<label class="label">
					<spring:message code="Starting Time"></spring:message>
				</label>
				<spring:eval expression="forwardingAttribute.startingTime" />
			</li>
			<li>
				<label class="label">
					<spring:message code="Ending Time"></spring:message>
				</label>
				<spring:eval expression="forwardingAttribute.endingTime" />
			</li>
			<li>
				<label class="label">
					<spring:message code="Ending Point"></spring:message>
				</label>
				${forwardingAttribute.endingPoint}
			</li>
			<li>
				<label class="label">
					<spring:message code="Weight"></spring:message>
				</label>
				${forwardingAttribute.loadWeightInTonne}
			</li>
			<li>
				<label class="label">
					<spring:message code="Shipping Cost"></spring:message>
				</label>
				${forwardingAttribute.shippingCost}
			</li>
			<li>
				<label class="label">
					<spring:message code="Subcontractor"></spring:message>
				</label>
				${forwardingAttribute.subcontractor.name}
			</li>
			<li>
				<label class="label">
					<spring:message code="Quota"></spring:message>
				</label>
				${forwardingAttribute.quota.name}
			</li>
		</ul>
	</div>
	<div class="span8">
		<aripd:datatables datasource="/uatf/get/${forwardingAttribute.id}" id="uatfs">
			<aripd:column label="Code" field="code"/>
			<aripd:column label="Company" field="company"/>
			<aripd:column label="County" field="county"/>
      <aripd:column label="City" field="city"/>
      <aripd:column label="Weight" field="loadWeightInTonne"/>
		</aripd:datatables>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>