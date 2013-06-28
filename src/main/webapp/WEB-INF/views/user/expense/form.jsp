<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="expenseList" value="/user/expense/list" />
<spring:url var="expenseShow" value="/user/expense/show/${expenseAttribute.id}" />
<spring:url var="expenseEdit" value="/user/expense/edit/${expenseAttribute.id}" />
<spring:url var="expenseNew" value="/user/expense/new" />
<spring:url var="expenseSave" value="/user/expense/save" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${expenseList}"><spring:message code="Expenses" /></a></li>
        <c:choose>
            <c:when test="${ !empty expenseAttribute.id }">
            <li class="active"><a href="${expenseEdit}"><spring:message code="Entry No" />: ${expenseAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${expenseNew}"><spring:message code="New Entry" /></a></li>
            </c:otherwise>
        </c:choose>
</ul>

<div class="row-fluid">
    <div class="span12">
        <form:form modelAttribute="expenseAttribute" action="${expenseSave}" method="post">
            <form:errors path="*" cssClass="alert alert-error" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="control-group">
                    <form:label path="expensetype"><spring:message code="Expensetype" /></form:label>
                    <form:select path="expensetype.id" multiple="false" items="${expensetypes}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="account" />
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
                    <c:if test="${ !empty expenseAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${expenseAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
                    </div>
                </fieldset>
        </form:form>

        <c:if test="${ !empty expenseAttribute.id }">
            <spring:url var="deleteUrl" value="/user/expense/delete?id=${expenseAttribute.id}" />
            <form:form id="form-${expenseAttribute.id}" modelAttribute="expenseAttribute" action="${deleteUrl}" method="delete">
                <form:hidden path="id" />
            </form:form>
        </c:if>
    </div>
</div>

<script>
    $(function() {
        $("#documentDate").datetimepicker();
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />