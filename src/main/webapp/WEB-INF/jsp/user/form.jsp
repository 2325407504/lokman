<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
		  <li><a href="#"><spring:message code="label.user"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="button.new"></spring:message></li>
		</ul>
		<c:url var="saveUrl" value="/user/save" />
		<form:form modelAttribute="userAttribute" action="${saveUrl}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<form:label path="firstname"><spring:message code="Firstname"></spring:message></form:label>
					<span><form:input path="firstname" /></span>
					<form:errors cssClass="error-field" path="firstname" />
				</div>       
				<div class="form-row">
					<form:label path="lastname"><spring:message code="Lastname"></spring:message></form:label>
					<span><form:input path="lastname" /></span>
					<form:errors cssClass="error-field" path="lastname" />
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
					<form:label path="dateOfBirth"><spring:message code="Date of birth"></spring:message></form:label>
					<span><form:input type="date" path="dateOfBirth" /></span>
					<form:errors cssClass="error-field" path="dateOfBirth" />
				</div>
				<div class="form-actions">
					<c:if test="${ !empty userAttribute.id }">
					<a class="btn btn-danger" href="javascript:$('#form-${userAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
					</c:if>
					<button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>
		
		<c:if test="${ !empty userAttribute.id }">
		<c:url var="deleteUrl" value="/user/delete?id=${userAttribute.id}" />
		<form:form id="form-${userAttribute.id}" modelAttribute="userAttribute" action="${deleteUrl}" method="delete">
			<form:hidden path="id" />
		</form:form>
		</c:if>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>