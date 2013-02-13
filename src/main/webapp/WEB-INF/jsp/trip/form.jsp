<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/trip/list" var="trip_list" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li><a href="${trip_list}"><spring:message code="Trip Tracking"></spring:message></a> <span class="divider">/</span></li>
	<c:choose>
		<c:when test="${ !empty tripAttribute.id }">
			<li class="active"><spring:message code="Entry No"></spring:message>: ${tripAttribute.id}</li>
		</c:when>
		<c:otherwise>
			<li class="active"><spring:message code="New Entry"></spring:message></li>
		</c:otherwise>
	</c:choose>
</ul>
<spring:url var="saveUrl" value="/trip/save" />
<form:form modelAttribute="tripAttribute" action="${saveUrl}" method="post">
	<form:errors path="*" cssClass="error-block" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="form-row">
			<form:label path="truck"><spring:message code="Truck" text="Truck"></spring:message></form:label>
			<form:select path="truck.id" multiple="false" items="${trucks}" itemLabel="plate" itemValue="id"/>
			<form:errors cssClass="error-field" path="truck" />
		</div>
		<div class="form-row">
			<form:label path="driver"><spring:message code="Driver" text="Driver"></spring:message></form:label>
			<form:select path="driver.id" multiple="false" items="${drivers}" itemLabel="fullname" itemValue="id"/>
			<form:errors cssClass="error-field" path="driver" />
		</div>
		<div class="form-row">
			<form:label path="startingPoint"><spring:message code="Starting Point" text="Starting Point"></spring:message></form:label>
			<span><form:input path="startingPoint" /></span>
			<form:errors cssClass="error-field" path="startingPoint" />
		</div>
		<div class="form-row">
			<form:label path="startingKm"><spring:message code="Starting km" text="Starting km"></spring:message></form:label>
			<span><form:input path="startingKm" /></span>
			<form:errors cssClass="error-field" path="startingKm" />
		</div>
		<div class="form-row">
			<form:label path="startingTime"><spring:message code="Starting Time" text="Starting Time"></spring:message></form:label>
			<span><form:input type="datetime" path="startingTime" /></span>
			<form:errors cssClass="error-field" path="startingTime" />
		</div>
		<div class="form-row">
			<form:label path="endingPoint"><spring:message code="Ending Point" text="Ending Point"></spring:message></form:label>
			<span><form:input path="endingPoint" /></span>
			<form:errors cssClass="error-field" path="endingPoint" />
		</div>
		<div class="form-row">
			<form:label path="endingKm"><spring:message code="Ending km" text="Ending km"></spring:message></form:label>
			<span><form:input path="endingKm" /></span>
			<form:errors cssClass="error-field" path="endingKm" />
		</div>
		<div class="form-row">
			<form:label path="endingTime"><spring:message code="Ending Time" text="Ending Time"></spring:message></form:label>
			<span><form:input type="datetime" path="endingTime" /></span>
			<form:errors cssClass="error-field" path="endingTime" />
		</div>
		<aripd:input path="loadWeightInTonne" label="loadWeightInTonne"/>
		<div class="control-group">
			<form:label path="remark" class="control-label"><spring:message code="Remark" text="Remark"></spring:message></form:label>
			<div class="controls"><form:textarea path="remark" /></div>
			<form:errors cssClass="error-field" path="remark" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty tripAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${tripAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty tripAttribute.id }">
<spring:url var="deleteUrl" value="/trip/delete?id=${tripAttribute.id}" />
<form:form id="form-${tripAttribute.id}" modelAttribute="tripAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

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