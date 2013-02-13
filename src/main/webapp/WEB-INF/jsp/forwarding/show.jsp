<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/forwarding/list" var="forwarding_list" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li><a href="${forwarding_list}"><spring:message code="Forwarding Tracking"></spring:message></a> <span class="divider">/</span></li>
	<li class="active"><spring:message code="Entry No"></spring:message>: ${forwardingAttribute.id}</li>
	<li class="pull-right">
		<spring:url var="editUrl" value="/forwarding/edit/${forwardingAttribute.id}" />
		<a class="btn btn-mini" href="${editUrl}"><spring:message code="Edit"></spring:message></a>
	</li>
</ul>

<div class="row">
	<div class="span4">
		
		<ul class="unstyled">
			<li>
				<label class="label">
					<spring:message code="Id" text="Id"></spring:message>
				</label>
				${forwardingAttribute.id}
			</li>
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
	<div class="span6">
		<spring:url var="saveUrl" value="/uatf/save/${forwardingAttribute.id}" />
		
		<fmt:message key="Code" var="Code"/>
		<fmt:message key="Company" var="Company"/>
		<fmt:message key="County" var="County"/>
		<fmt:message key="City" var="City"/>
		
		<form:form modelAttribute="uatfAttribute" action="${saveUrl}" method="post" class="form-inline">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:input path="code" cssClass="input-small" placeholder="${Code}" />
			<form:input path="company" cssClass="input-small" placeholder="${Company}" />
			<form:input path="county" cssClass="input-small" placeholder="${County}" />
			<form:input path="city" cssClass="input-small" placeholder="${City}" />
			<button class="btn" type="submit"><i class="icon-ok"></i></button>
		</form:form>
		
		<hr>

		<aripd:datatables datasource="/uatf/get/${forwardingAttribute.id}" id="uatfs" dataUrlDelete="/uatf/delete" actionColumn="4">
			<aripd:column label="Code" field="code"/>
			<aripd:column label="Company" field="company"/>
			<aripd:column label="County" field="county"/>
			<aripd:column label="City" field="city"/>
			<aripd:column label="Action" field="id"/>
		</aripd:datatables>

	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>