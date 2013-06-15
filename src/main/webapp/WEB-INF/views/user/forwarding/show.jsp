<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/user/forwarding/list" />
<spring:url var="forwardingShow" value="/user/forwarding/show/${forwardingAttribute.id}" />
<spring:url var="forwardingEdit" value="/user/forwarding/edit/${forwardingAttribute.id}" />
<spring:url var="forwardingNew" value="/user/forwarding/new" />
<spring:url var="forwardingSubmit" value="/user/forwarding/submit/${forwardingAttribute.id}" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${forwardingList}"><spring:message code="Forwardings"></spring:message></a></li>
	<li class="active"><a href="${forwardingShow}"><spring:message code="Entry No"></spring:message>: ${forwardingAttribute.id}</a></li>
	<li class=""><a href="${forwardingNew}"><spring:message code="New Entry"></spring:message></a></li>
</ul>

<c:if test="${forwardingAttribute.submitted}">
<div class="alert alert-error"><spring:message code="You cannot edit this record anymore"></spring:message></div>
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
		<aripd:datatables datasource="/user/uatf/get/${forwardingAttribute.id}" id="uatfs">
			<aripd:column label="Code" field="code"/>
			<aripd:column label="Company" field="company"/>
			<aripd:column label="County" field="county"/>
      <aripd:column label="City" field="city"/>
      <aripd:column label="Weight" field="loadWeightInTonne"/>
		</aripd:datatables>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>