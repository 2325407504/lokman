<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/forwarding/list" var="forwarding_list" />

<ul class="breadcrumb">
	<li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
	<li><a href="${forwarding_list}"><spring:message code="Forwarding Tracking"></spring:message></a> <span class="divider">/</span></li>
	<c:choose>
		<c:when test="${ !empty forwardingAttribute.id }">
			<li class="active"><spring:message code="Entry No"></spring:message>: ${forwardingAttribute.id}</li>
		</c:when>
		<c:otherwise>
			<li class="active"><spring:message code="New Entry"></spring:message></li>
		</c:otherwise>
	</c:choose>
</ul>
<spring:url var="saveUrl" value="/forwarding/save" />
<form:form modelAttribute="forwardingAttribute" action="${saveUrl}" method="post">
	<form:errors path="*" cssClass="error-block" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="form-row">
			<form:label path="waybillNo"><spring:message code="Waybill No" text="Waybill No"></spring:message></form:label>
			<span><form:input type="text" path="waybillNo" /></span>
			<form:errors cssClass="error-field" path="waybillNo" />
		</div>
		<div class="form-row">
			<form:label path="driver"><spring:message code="Driver" text="Driver"></spring:message></form:label>
			<span><form:input path="driver" /></span>
			<form:errors cssClass="error-field" path="driver" />
		</div>
		<div class="form-row">
			<form:label path="plate"><spring:message code="Plate" text="Plate"></spring:message></form:label>
			<span><form:input path="plate" /></span>
			<form:errors cssClass="error-field" path="plate" />
		</div>
		<div class="form-row">
			<form:label path="startingAt"><spring:message code="Starting At" text="Starting At"></spring:message></form:label>
			<span><form:input type="text" path="startingAt" /></span>
			<form:errors cssClass="error-field" path="startingAt" />
		</div>
		<div class="form-row">
			<form:label path="endingAt"><spring:message code="Ending At" text="Ending At"></spring:message></form:label>
			<span><form:input type="text" path="endingAt" /></span>
			<form:errors cssClass="error-field" path="endingAt" />
		</div>
		<div class="form-row">
			<form:label path="endingPoint"><spring:message code="Ending Point" text="Ending Point"></spring:message></form:label>
			<span><form:input path="endingPoint" /></span>
			<form:errors cssClass="error-field" path="endingPoint" />
		</div>
		<div class="form-row">
			<form:label path="loadWeightInTonne"><spring:message code="loadWeightInTonne" text="loadWeightInTonne"></spring:message></form:label>
			<span><form:input path="loadWeightInTonne" /></span>
			<form:errors cssClass="error-field" path="loadWeightInTonne" />
		</div>
		<div class="form-row">
			<form:label path="shippingCost"><spring:message code="Shipping Cost" text="Shipping Cost"></spring:message></form:label>
			<span><form:input path="shippingCost" /></span>
			<form:errors cssClass="error-field" path="shippingCost" />
		</div>
		<div class="form-row">
			<form:label path="subcontractor"><spring:message code="Subcontractor" text="Subcontractor"></spring:message></form:label>
			<form:select path="subcontractor.id" multiple="false" items="${subcontractors}" itemLabel="name" itemValue="id"/>
			<form:errors cssClass="error-field" path="subcontractor" />
		</div>
		<div class="form-row">
			<form:label path="quota"><spring:message code="Quota" text="Quota"></spring:message></form:label>
			<form:select path="quota.id" multiple="false" items="${quotas}" itemLabel="name" itemValue="id"/>
			<form:errors cssClass="error-field" path="quota" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty forwardingAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${forwardingAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty forwardingAttribute.id }">
<spring:url var="deleteUrl" value="/forwarding/delete?id=${forwardingAttribute.id}" />
<form:form id="form-${forwardingAttribute.id}" modelAttribute="forwardingAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>