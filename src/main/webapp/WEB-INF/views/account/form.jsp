<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Accounts" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Accounts" />
    <jsp:param name="property" value="account" />
    <jsp:param name="active" value="form" />
</jsp:include>


<spring:url var="save" value="/account/save" />
<form:form modelAttribute="accountAttribute" action="${save}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="active">Aktif mi?</form:label>
                    <form:checkbox path="active" />
                    <form:errors cssClass="text-error" path="active" />
                </div>
                <div class="control-group">
                    <form:label path="region"><spring:message code="Region" /></form:label>
                    <form:select multiple="false" path="region.id" items="${regions}" itemLabel="name" itemValue="id" />
                    <form:errors cssClass="text-error" path="region" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="employee"><spring:message code="Employee" /></form:label>
                    <form:select multiple="false" path="employee.id" items="${employees}" itemLabel="fullname" itemValue="id" />
                    <form:errors cssClass="text-error" path="employee" />
                </div>
                <div class="control-group">
                    <form:label path="roles"><spring:message code="Roles" /></form:label>
                    <form:select multiple="true" size="6" path="roles" items="${roles}" itemLabel="name" itemValue="id" />
                    <form:errors cssClass="text-error" path="roles" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="email"><spring:message code="E-mail Address" /></form:label>
                    <form:input path="email" />
                    <form:errors cssClass="text-error" path="email" />
                </div>
                <div class="control-group">
                    <form:label path="username"><spring:message code="Username" /></form:label>
                    <form:input path="username" />
                    <form:errors cssClass="text-error" path="username" />
                </div>
                <div class="control-group">
                    <form:label path="password"><spring:message code="Password" /></form:label>
                    <form:password path="password" />
                    <form:errors cssClass="text-error" path="password" />
                </div>
            </fieldset>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="form-actions">
                <c:if test="${ !empty accountAttribute.id }">
                    <a class="btn btn-danger" href="javascript:$('#form-${accountAttribute.id}').submit();"><spring:message code="Delete" /></a>
                </c:if>
                <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </div>
    </div>
</form:form>

<c:if test="${ !empty accountAttribute.id }">
    <spring:url var="deleteUrl" value="/account/delete?id=${accountAttribute.id}" />
    <form:form id="form-${accountAttribute.id}" modelAttribute="accountAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>



<c:forEach var="role" items="${accountAttribute.roles}">
    <span class="label label-success">${role.name}</span>&nbsp;
</c:forEach>

<jsp:include page="/WEB-INF/views/footer.jsp" />