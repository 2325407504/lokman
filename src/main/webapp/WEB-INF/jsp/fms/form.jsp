<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li><a href="#"><spring:message code="Field Management System"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="New entry"></spring:message></li>
		</ul>
		<c:url var="saveUrl" value="/fms/save" />
		<form:form modelAttribute="fmsAttribute" action="${saveUrl}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<form:label path="publishedAt"><spring:message code="Published at" text="Published at"></spring:message></form:label>
					<span><form:input type="text" path="publishedAt" /></span>
					<form:errors cssClass="error-field" path="publishedAt" />
				</div>       
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
					<form:label path="startingKm"><spring:message code="Starting km" text="Starting km"></spring:message></form:label>
					<span><form:input path="startingKm" /></span>
					<form:errors cssClass="error-field" path="startingKm" />
				</div>       
				<div class="form-row">
					<form:label path="endingKm"><spring:message code="Ending km" text="Ending km"></spring:message></form:label>
					<span><form:input path="endingKm" /></span>
					<form:errors cssClass="error-field" path="endingKm" />
				</div>
				<div class="control-group">
					<form:label path="loadTon" class="control-label"><spring:message code="Load in tonne" text="Load in tonne"></spring:message></form:label>
					<div class="controls"><form:input path="loadTon" /></div>
					<form:errors cssClass="error-field" path="loadTon" />
				</div>
				<aripd:input path="fuelTL" label="Fuel consumption in TL"/>
				<aripd:input path="fuelLiter" label="Fuel consumption in liter"/>
				<div class="control-group">
					<form:label path="periodicMaintenance" class="control-label"><spring:message code="Periodic maintenance" text="Periodic maintenance"></spring:message></form:label>
					<div class="controls"><form:input path="periodicMaintenance" /></div>
					<form:errors cssClass="error-field" path="periodicMaintenance" />
				</div>
				<div class="control-group">
					<form:label path="breakDownKm" class="control-label"><spring:message code="Break down in km" text="Break down in km"></spring:message></form:label>
					<div class="controls"><form:input path="breakDownKm" /></div>
					<form:errors cssClass="error-field" path="breakDownKm" />
				</div>
				<div class="control-group">
					<form:label path="remark" class="control-label"><spring:message code="Remark" text="Remark"></spring:message></form:label>
					<div class="controls"><form:textarea path="remark" /></div>
					<form:errors cssClass="error-field" path="remark" />
				</div>
				<div class="form-actions">
					<c:if test="${ !empty fmsAttribute.id }">
					<a class="btn btn-danger" href="javascript:$('#form-${fmsAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
					</c:if>
					<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>
		
		<c:if test="${ !empty fmsAttribute.id }">
		<c:url var="deleteUrl" value="/fms/delete?id=${fmsAttribute.id}" />
		<form:form id="form-${fmsAttribute.id}" modelAttribute="fmsAttribute" action="${deleteUrl}" method="delete">
			<form:hidden path="id" />
		</form:form>
		</c:if>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>