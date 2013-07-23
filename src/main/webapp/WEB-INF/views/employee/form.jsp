<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employees" />
    <jsp:param name="property" value="employee" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <spring:url var="save" value="/employee/save" />
        <form:form modelAttribute="employeeAttribute" action="${save}" method="post">
            <form:errors path="*" cssClass="alert alert-error" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="control-group">
                    <form:label path="tckimlik"><spring:message code="TC Kimlik" /></form:label>
                    <form:input path="tckimlik" />
                    <form:errors cssClass="text-error" path="tckimlik" />
                </div>
                <div class="control-group">
                    <form:label path="firstName"><spring:message code="FirstName" /></form:label>
                    <form:input path="firstName" />
                    <form:errors cssClass="text-error" path="firstName" />
                </div>
                <div class="control-group">
                    <form:label path="lastName"><spring:message code="LastName" /></form:label>
                    <form:input path="lastName" />
                    <form:errors cssClass="text-error" path="lastName" />
                </div>
                <div class="control-group">
                    <form:label path="address"><spring:message code="Postal Address" /></form:label>
                    <form:input path="address" />
                    <form:errors cssClass="text-error" path="address" />
                </div>
                <div class="control-group">
                    <form:label path="phonenumber"><spring:message code="Phone Number" /></form:label>
                    <form:input path="phonenumber" />
                    <form:errors cssClass="text-error" path="phonenumber" />
                </div>
                <div class="control-group">
                    <form:label path="birthdate"><spring:message code="Date of birth" /></form:label>
                    <form:input type="datetime" path="birthdate" />
                    <form:errors cssClass="text-error" path="birthdate" />
                </div>
                <div class="control-group">
                    <form:label path="employmentDate"><spring:message code="Date of starting the job" /></form:label>
                    <form:input type="datetime" path="employmentDate" />
                    <form:errors cssClass="text-error" path="employmentDate" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty employeeAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${employeeAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
                </div>
            </fieldset>
        </form:form>

        <c:if test="${ !empty employeeAttribute.id }">
            <spring:url var="deleteUrl" value="/employee/delete?id=${employeeAttribute.id}" />
            <form:form id="form-${employeeAttribute.id}" modelAttribute="employeeAttribute" action="${deleteUrl}" method="delete">
                <form:hidden path="id" />
            </form:form>
        </c:if>
    </div>
</div>

<script>
    $(function() {
        $("[name=birthdate], [name=employmentDate]").datepicker({
            maxDate: new Date()
        });
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />