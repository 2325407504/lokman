<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url value="/account/list" var="account_list" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li><a href="${account_list}"><spring:message code="Accounts"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="New Entry"></spring:message></li>
</ul>
<spring:url var="saveUrl" value="/account/save" />
<form:form modelAttribute="accountAttribute" action="${saveUrl}" method="post">
	<form:errors path="*" cssClass="error-block" element="div" />
	<form:hidden path="id" />
	<fieldset>
		<div class="form-row">
			<form:label path="customer.firstName"><spring:message code="FirstName"></spring:message></form:label>
			<span><form:input path="customer.firstName" /></span>
			<form:errors cssClass="error-field" path="customer.firstName" />
		</div>       
		<div class="form-row">
			<form:label path="customer.lastName"><spring:message code="LastName"></spring:message></form:label>
			<span><form:input path="customer.lastName" /></span>
			<form:errors cssClass="error-field" path="customer.lastName" />
		</div>
		<div class="form-row">
			<form:label path="email"><spring:message code="E-mail Address"></spring:message></form:label>
			<span><form:input path="email" /></span>
			<form:errors cssClass="error-field" path="email" />
		</div>
		<div class="form-row">
			<form:label path="username"><spring:message code="Username"></spring:message></form:label>
			<span><form:input path="username" /></span>
			<form:errors cssClass="error-field" path="username" />
		</div>
		<div class="form-row">
			<form:label path="password"><spring:message code="Password"></spring:message></form:label>
			<span><form:password path="password" /></span>
			<form:errors cssClass="error-field" path="password" />
		</div>
		<div class="form-row">
			<form:label path="customer.dateOfBirth"><spring:message code="Date of birth"></spring:message></form:label>
			<span><form:input type="date" path="customer.dateOfBirth" /></span>
			<form:errors cssClass="error-field" path="customer.dateOfBirth" />
		</div>
		<div class="form-actions">
			<c:if test="${ !empty accountAttribute.id }">
			<a class="btn btn-danger" href="javascript:$('#form-${accountAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
			</c:if>
			<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
		</div>
	</fieldset>
</form:form>

<c:if test="${ !empty accountAttribute.id }">
<spring:url var="deleteUrl" value="/account/delete?id=${accountAttribute.id}" />
<form:form id="form-${accountAttribute.id}" modelAttribute="accountAttribute" action="${deleteUrl}" method="delete">
	<form:hidden path="id" />
</form:form>
</c:if>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>