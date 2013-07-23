<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Payments" />
    <jsp:param name="property" value="payment" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <spring:url var="paymentSave" value="/payment/save" />
        <form:form modelAttribute="paymentAttribute" action="${paymentSave}" method="post">
            <form:errors path="*" cssClass="alert alert-error" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="control-group">
                    <form:label path="employee"><spring:message code="Employee" /></form:label>
                    <form:select multiple="false" path="employee.id" items="${employees}" itemLabel="fullname" itemValue="id" />
                    <form:errors cssClass="text-error" path="employee" />
                </div>
                <div class="control-group">
                    <form:label path="paymenttype"><spring:message code="Paymenttype" /></form:label>
                    <form:select path="paymenttype.id" multiple="false" items="${paymenttypes}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="paymenttype" />
                </div>
                <div class="control-group">
                    <form:label path="documentDate"><spring:message code="Date" /></form:label>
                    <form:input type="datetime" path="documentDate" />
                    <form:errors cssClass="text-error" path="documentDate" />
                </div>
                <div class="control-group">
                    <form:label path="remark"><spring:message code="Remark" /></form:label>
                    <form:textarea path="remark" />
                    <form:errors cssClass="text-error" path="remark" />
                </div>
                <div class="control-group">
                    <form:label path="amount"><spring:message code="Amount" /></form:label>
                    <form:input path="amount" />
                    <form:errors cssClass="text-error" path="amount" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty paymentAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${paymentAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
                </div>
            </fieldset>
        </form:form>

        <c:if test="${ !empty paymentAttribute.id }">
            <spring:url var="deleteUrl" value="/payment/delete?id=${paymentAttribute.id}" />
            <form:form id="form-${paymentAttribute.id}" modelAttribute="paymentAttribute" action="${deleteUrl}" method="delete">
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