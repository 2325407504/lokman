<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="label.home"></spring:message></a> <span class="divider">/</span></li>
		  <li><a href="#"><spring:message code="label.user"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="button.new"></spring:message></li>
		</ul>
		<c:url var="saveUrl" value="/user/save" />
		<form:form modelAttribute="userAttribute" action="${saveUrl}" method="post">
			<form:errors path="*" cssClass="error-block" element="div" />
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<label for="roles"><spring:message code="label.roles"></spring:message></label>
					<form:select path="roles" multiple="false">
						<form:options items="${user.roles}" itemLabel="name" itemValue="id" />
					</form:select>
					<form:errors cssClass="error-field" path="roles" />
				</div>
				<div class="form-row">
					<label for="firstName"><spring:message code="label.firstname"></spring:message></label>
					<span><form:input path="firstName" /></span>
				</div>       
				<div class="form-row">
					<label for="lastName"><spring:message code="label.lastname"></spring:message></label>
					<span><form:input path="lastName" /></span>
				</div>
				<div class="form-row">
					<label for="username"><spring:message code="label.username"></spring:message></label>
					<span><form:input path="username" /></span>
					<form:errors cssClass="error-field" path="username" />
				</div>
				<div class="form-row">
					<label for="password"><spring:message code="label.password"></spring:message></label>
					<span><form:password path="password" /></span>
					<form:errors cssClass="error-field" path="password" />
				</div>
				<div class="form-actions">
					<button class="btn btn-primary" type="submit"><spring:message code="button.save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>