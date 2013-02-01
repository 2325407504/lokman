<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li><a href="#"><spring:message code="Truck"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="New entry"></spring:message></li>
		</ul>
		<c:url var="saveUrl" value="/truck/save" />
		<form:form modelAttribute="truckAttribute" action="${saveUrl}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<form:label path="plate"><spring:message code="Plate"></spring:message></form:label>
					<span class="input"><form:input path="plate" /></span>
					<form:errors cssClass="error-field" path="plate" />
				</div>
				<div class="form-actions">
					<c:if test="${ !empty truckAttribute.id }">
					<a class="btn btn-danger" href="javascript:$('#form-${truckAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
					</c:if>
					<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>
		
		<c:if test="${ !empty truckAttribute.id }">
		<c:url var="deleteUrl" value="/truck/delete?id=${truckAttribute.id}" />
		<form:form id="form-${truckAttribute.id}" modelAttribute="truckAttribute" action="${deleteUrl}" method="delete">
			<form:hidden path="id" />
		</form:form>
		</c:if>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>