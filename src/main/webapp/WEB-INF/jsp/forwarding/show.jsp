<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/forwarding/list" />
<spring:url var="forwardingShow" value="/forwarding/show/${forwardingAttribute.id}" />
<spring:url var="forwardingEdit" value="/forwarding/edit/${forwardingAttribute.id}" />
<spring:url var="forwardingNew" value="/forwarding/new" />
<spring:url var="forwardingExport" value="/forwarding/export/xls" />
<spring:url var="uatfExport" value="/uatf/export/xls" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${forwardingList}"><spring:message code="Forwarding Tracking" text="Forwarding Tracking"></spring:message></a></li>
	<li class="active"><a href="${forwardingShow}"><spring:message code="Entry No"></spring:message>: ${forwardingAttribute.id}</a></li>
	<li class=""><a href="${forwardingNew}"><spring:message code="New Entry"></spring:message></a></li>
	<li class="dropdown">
		<a class="dropdown-toggle" data-toggle="dropdown" href="#">
			<spring:message code="Export"></spring:message>
			<b class="caret"></b>
		</a>
		<ul class="dropdown-menu">
			<li><a href="${forwardingExport}"><spring:message code="Waybill" text="Waybill"></spring:message></a></li>
			<li><a href="${uatfExport}"><spring:message code="UATF" text="UATF"></spring:message></a></li>
		</ul>
	</li>
</ul>

<c:if test="${forwardingAttribute.submitted}">
<div class="alert alert-error"><spring:message code="You cannot edit this record anymore"></spring:message></div>
</c:if>
<c:if test="${!forwardingAttribute.submitted}">
<div class="alert alert-info"><a href="${forwardingEdit}"><i class="icon-pencil"></i> <spring:message code="Edit"></spring:message></a></div>
</c:if>

<div class="row-fluid">
	<div class="span4">
		<ul class="unstyled">
			<li>
				<label class="label">
					<spring:message code="Waybill No" text="Waybill No"></spring:message>
				</label>
				${forwardingAttribute.waybillNo}
			</li>
			<li>
				<label class="label">
					<spring:message code="Driver" text="Driver"></spring:message>
				</label>
				${forwardingAttribute.driver}
			</li>
			<li>
				<label class="label">
					<spring:message code="Plate" text="Plate"></spring:message>
				</label>
				${forwardingAttribute.plate}
			</li>
			<li>
				<label class="label">
					<spring:message code="Starting At" text="Starting At"></spring:message>
				</label>
				<spring:eval expression="forwardingAttribute.startingAt" />
			</li>
			<li>
				<label class="label">
					<spring:message code="Ending At" text="Ending At"></spring:message>
				</label>
				<spring:eval expression="forwardingAttribute.endingAt" />
			</li>
			<li>
				<label class="label">
					<spring:message code="Ending Point" text="Ending Point"></spring:message>
				</label>
				${forwardingAttribute.endingPoint}
			</li>
			<li>
				<label class="label">
					<spring:message code="loadWeightInTonne" text="loadWeightInTonne"></spring:message>
				</label>
				${forwardingAttribute.loadWeightInTonne}
			</li>
			<li>
				<label class="label">
					<spring:message code="Shipping Cost" text="Shipping Cost"></spring:message>
				</label>
				${forwardingAttribute.shippingCost}
			</li>
			<li>
				<label class="label">
					<spring:message code="Subcontractor" text="Subcontractor"></spring:message>
				</label>
				${forwardingAttribute.subcontractor.name}
			</li>
			<li>
				<label class="label">
					<spring:message code="Quota" text="Quota"></spring:message>
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
		</aripd:datatables>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>