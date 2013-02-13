<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/shift/list" var="shift_list" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li><a href="${shift_list}"><spring:message code="Shifts" text="Shifts"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="New Entry"></spring:message></li>
</ul>
<spring:url var="saveUrl" value="/shift/save" />
<form:form modelAttribute="shiftAttribute" action="${saveUrl}" method="post">
	<form:errors path="*" cssClass="error-block" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="form-row">
			<form:label path="startedAt"><spring:message code="Started at" text="Started at"></spring:message></form:label>
			<span><form:input type="datetime" path="startedAt" /></span>
			<form:errors cssClass="error-field" path="startedAt" />
		</div>
		<div class="form-row">
			<form:label path="breakCount"><spring:message code="Break Count" text="Break Count"></spring:message></form:label>
			<span><form:input path="breakCount" /></span>
			<form:errors cssClass="error-field" path="breakCount" />
		</div>       
		<div class="form-row">
			<form:label path="breakTime"><spring:message code="Break Time" text="Break Time"></spring:message></form:label>
			<span><form:input path="breakTime" /></span>
			<form:errors cssClass="error-field" path="breakTime" />
		</div>
		<div class="control-group">
			<form:label path="electrictyConsumptionStart" class="control-label"><spring:message code="electrictyConsumptionStart" text="electrictyConsumptionStart"></spring:message></form:label>
			<div class="controls"><form:input path="electrictyConsumptionStart" /></div>
			<form:errors cssClass="error-field" path="electrictyConsumptionStart" />
		</div>
		<div class="control-group">
			<form:label path="electrictyConsumptionEnd" class="control-label"><spring:message code="electrictyConsumptionEnd" text="electrictyConsumptionEnd"></spring:message></form:label>
			<div class="controls"><form:input path="electrictyConsumptionEnd" /></div>
			<form:errors cssClass="error-field" path="electrictyConsumptionEnd" />
		</div>
		<div class="control-group">
			<form:label path="productionInTonne" class="control-label"><spring:message code="productionInTonne" text="productionInTonne"></spring:message></form:label>
			<div class="controls"><form:input path="productionInTonne" /></div>
			<form:errors cssClass="error-field" path="productionInTonne" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty shiftAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${shiftAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty shiftAttribute.id }">
<spring:url var="deleteUrl" value="/shift/delete?id=${shiftAttribute.id}" />
<form:form id="form-${shiftAttribute.id}" modelAttribute="shiftAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>