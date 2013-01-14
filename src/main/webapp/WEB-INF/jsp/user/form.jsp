<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="span12">
		<ul class="breadcrumb">
		  <li><a href="#"><spring:message code="label.home"></spring:message></a> <span class="divider">/</span></li>
		  <li><a href="#"><spring:message code="label.user"></spring:message></a> <span class="divider">/</span></li>
		  <li class="active"><spring:message code="button.new"></spring:message></li>
		</ul>

<div id="messages">
    <c:if test="${not empty statusMessageKey}">
       <p><fmt:message key="${statusMessageKey}"/></p>
    </c:if>

    <spring:hasBindErrors name="person">
        <h2>Errors</h2>
        <div class="formerror">
            <ul>
            <c:forEach var="error" items="${errors.allErrors}">
                <li>${error.defaultMessage}</li>
            </c:forEach>
            </ul>
        </div>
    </spring:hasBindErrors>
</div>

		<c:url var="saveUrl" value="/user/save" />
		<form:form modelAttribute="userAttribute" action="${saveUrl}" method="post">
			<form:hidden path="id" />
			<fieldset>
				<div class="form-row">
					<label for="role"><spring:message code="label.roles"></spring:message></label>
					<form:select path="role" multiple="true">
						<form:options items="${user.role}" itemLabel="role" itemValue="id" />
					</form:select>
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
				</div>
				<div class="form-row">
					<label for="password"><spring:message code="label.password"></spring:message></label>
					<span><form:password path="password" /></span>
				</div>
				<div class="form-actions">
					<button class="btn btn-primary" type="submit"><spring:message code="button.save"></spring:message></button>
				</div>
			</fieldset>
		</form:form>

<c:if test="${not empty person.id}">
<div style="clear: both;float:left;">
<div>
<a href="${flowExecutionUrl}&_eventId=addAddress" ><fmt:message key="address.form.button.add"/></a>
</div>
</c:if>

<c:if test="${empty person.addresses}">
    <p>&nbsp;</p>
</c:if>

<c:if test="${not empty person.addresses}">
<table class="search">
    <tr>
        <th><fmt:message key="address.form.address"/></th>
        <th><fmt:message key="address.form.city"/></th>
        <th><fmt:message key="address.form.state"/></th>
        <th><fmt:message key="address.form.zipPostal"/></th>
        <th><fmt:message key="address.form.country"/></th>
    </tr>
<c:forEach var="address" items="${person.addresses}">
    <tr>
            <td>${address.address}</td>
            <td>${address.city}</td>
            <td>${address.state}</td>
            <td>${address.zipPostal}</td>
            <td>${address.country}</td> 
            <td>
                <a href="${flowExecutionUrl}&_eventId=editAddress&addressId=${address.id}" ><fmt:message key="button.edit"/></a>
                <sec:authorize ifAllGranted="ROLE_ADMIN">
                    <a href="${flowExecutionUrl}&_eventId=deleteAddress&addressId=${address.id}" ><fmt:message key="button.delete"/></a>
                </sec:authorize>
            </td>
    </tr>
</c:forEach>
</table>
</c:if>

</div>
	
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>