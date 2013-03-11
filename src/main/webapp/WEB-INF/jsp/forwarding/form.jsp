<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/forwarding/list" />
<spring:url var="forwardingShow" value="/forwarding/show/${forwardingAttribute.id}" />
<spring:url var="forwardingEdit" value="/forwarding/edit/${forwardingAttribute.id}" />
<spring:url var="forwardingNew" value="/forwarding/new" />
<spring:url var="forwardingExport" value="/forwarding/export/xls" />
<spring:url var="uatfExport" value="/uatf/export/xls" />
<spring:url var="forwardingSave" value="/forwarding/save" />
<spring:url var="uatfSave" value="/uatf/save/${forwardingAttribute.id}" />

<ul class="nav nav-tabs">
	<li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
	<li class=""><a href="${forwardingList}"><spring:message code="Forwarding Tracking" text="Forwarding Tracking"></spring:message></a></li>
	<c:choose>
		<c:when test="${ !empty forwardingAttribute.id }">
			<li class="active"><a href="${forwardingEdit}"><spring:message code="Entry No"></spring:message>: ${forwardingAttribute.id}</a></li>
		</c:when>
		<c:otherwise>
			<li class="active"><a href="${forwardingNew}"><spring:message code="New Entry"></spring:message></a></li>
		</c:otherwise>
	</c:choose>
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

<div class="row-fluid">
	<div class="span4">
		<form:form modelAttribute="forwardingAttribute" action="${forwardingSave}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<form:label path="submitted"><spring:message code="Submitted" text="Submitted"></spring:message></form:label>
					<span><form:checkbox path="submitted" /></span>
					<form:errors cssClass="error-field" path="submitted" />
				</div>
				<div class="form-row">
					<form:label path="account"><spring:message code="Account" text="Account"></spring:message></form:label>
					<form:select multiple="false" path="account.id" items="${accounts}" itemLabel="customer.fullname" itemValue="id" />
					<form:errors cssClass="error-field" path="account" />
				</div>
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
					<form:label path="startingTime"><spring:message code="Starting Time"></spring:message></form:label>
					<span><form:input type="text" path="startingTime" /></span>
					<form:errors cssClass="error-field" path="startingTime" />
				</div>
				<div class="form-row">
					<form:label path="endingTime"><spring:message code="Ending Time"></spring:message></form:label>
					<span><form:input type="text" path="endingTime" /></span>
					<form:errors cssClass="error-field" path="endingTime" />
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
					<a class="btn btn-danger" href="javascript:$('#form-${forwardingAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete"></spring:message></a>
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
	</div>
	<div class="span8">
		<fmt:message key="Code" var="Code"/>
		<fmt:message key="Company" var="Company"/>
		<fmt:message key="County" var="County"/>
		<fmt:message key="City" var="City"/>

		<c:if test="${forwardingAttribute.id != null}">
		<form:form modelAttribute="uatfAttribute" action="${uatfSave}" method="post" class="form-inline">
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
		</c:if>
	</div>
</div>

<script type="text/javascript">
<!--
var startDateTextBox = $('#startingTime');
var endDateTextBox = $('#endingTime');

startDateTextBox.datetimepicker({ 
	onClose: function(dateText, inst) {
		if (endDateTextBox.val() != '') {
			var testStartDate = startDateTextBox.datetimepicker('getDate');
			var testEndDate = endDateTextBox.datetimepicker('getDate');
			if (testStartDate > testEndDate)
				endDateTextBox.datetimepicker('setDate', testStartDate);
		}
		else {
			endDateTextBox.val(dateText);
		}
	},
	onSelect: function (selectedDateTime){
		endDateTextBox.datetimepicker('option', 'minDate', startDateTextBox.datetimepicker('getDate') );
	}
});
endDateTextBox.datetimepicker({ 
	onClose: function(dateText, inst) {
		if (startDateTextBox.val() != '') {
			var testStartDate = startDateTextBox.datetimepicker('getDate');
			var testEndDate = endDateTextBox.datetimepicker('getDate');
			if (testStartDate > testEndDate)
				startDateTextBox.datetimepicker('setDate', testEndDate);
		}
		else {
			startDateTextBox.val(dateText);
		}
	},
	onSelect: function (selectedDateTime){
		startDateTextBox.datetimepicker('option', 'maxDate', endDateTextBox.datetimepicker('getDate') );
	}
});
//-->
</script>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>