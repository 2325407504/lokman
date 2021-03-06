<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Expenses" />
    <jsp:param name="property" value="userexpense" />
    <jsp:param name="new" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <spring:url var="save" value="/userexpense/save" />
        <form:form modelAttribute="userexpenseAttribute" action="${save}" method="post">
            <form:errors path="*" cssClass="alert alert-error" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="control-group">
                    <form:label path="expensetype"><spring:message code="Expensetype" /></form:label>
                    <form:select path="expensetype.id" multiple="false" items="${expensetypes}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="member" />
                </div>
                <div class="control-group">
                    <form:label path="documentDate"><spring:message code="Date" /></form:label>
                    <form:input type="text" path="documentDate" />
                    <form:errors cssClass="text-error" path="documentDate" />
                </div>
                <div class="control-group">
                    <form:label path="company"><spring:message code="Company" /></form:label>
                    <form:input path="company" />
                    <form:errors cssClass="text-error" path="company" />
                </div>
                <div class="control-group">
                    <form:label path="description"><spring:message code="Description" /></form:label>
                    <form:input path="description" />
                    <form:errors cssClass="text-error" path="description" />
                </div>
                <div class="control-group">
                    <form:label path="amount"><spring:message code="Amount" /></form:label>
                    <form:input path="amount" />
                    <form:errors cssClass="text-error" path="amount" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty userexpenseAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${userexpenseAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
                </div>
            </fieldset>
        </form:form>

        <c:if test="${ !empty userexpenseAttribute.id }">
            <spring:url var="deleteUrl" value="/userexpense/delete?id=${userexpenseAttribute.id}" />
            <form:form id="form-${userexpenseAttribute.id}" modelAttribute="userexpenseAttribute" action="${deleteUrl}" method="delete">
                <form:hidden path="id" />
            </form:form>
        </c:if>
    </div>
</div>

<script>
    $(function() {
        $("[name=documentDate]").datepicker({
            maxDate: new Date()
        });
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />