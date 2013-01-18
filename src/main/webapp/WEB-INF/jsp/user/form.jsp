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
					<label for="firstname"><spring:message code="Firstname"></spring:message></label>
					<span><form:input path="firstname" /></span>
				</div>       
				<div class="form-row">
					<label for="lastname"><spring:message code="Lastname"></spring:message></label>
					<span><form:input path="lastname" /></span>
				</div>
				<div class="form-row">
					<label for="username"><spring:message code="Username"></spring:message></label>
					<span><form:input path="username" /></span>
					<form:errors cssClass="error-field" path="username" />
				</div>
				<div class="form-row">
					<label for="password"><spring:message code="Password"></spring:message></label>
					<span><form:password path="password" /></span>
					<form:errors cssClass="error-field" path="password" />
				</div>
				<div class="form-actions">
					<c:if test="${ !empty userAttribute.id }">
					<a class="btn btn-danger" href="javascript:$('#form-${userAttribute.id}').submit();"><spring:message code="button.delete"></spring:message></a>
					</c:if>
					<button class="btn btn-primary" type="submit"><spring:message code="button.save"></spring:message></button>
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