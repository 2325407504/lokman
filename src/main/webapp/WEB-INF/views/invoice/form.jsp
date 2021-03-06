<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Invoices" />
    <jsp:param name="property" value="invoice" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="save" value="/invoice/save" />
<form:form modelAttribute="invoiceAttribute" action="${save}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:checkbox path="submitted" /> <spring:message code="message.record.submitted" />
                    <form:errors cssClass="text-error" path="submitted" />
                </div>
                <div class="control-group">
                    <form:label path="member"><spring:message code="Member" /></form:label>
                    <form:select multiple="false" path="member.id" items="${members}" itemLabel="username" itemValue="id" />
                    <form:errors cssClass="text-error" path="member" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="documentDate"><spring:message code="Document Date" /></form:label>
                    <form:input type="datetime" path="documentDate" />
                    <form:errors cssClass="text-error" path="documentDate" />
                </div>
                <div class="control-group">
                    <form:label path="documentNo"><spring:message code="Document No" /></form:label>
                    <form:input path="documentNo" />
                    <form:errors cssClass="text-error" path="documentNo" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="customer"><spring:message code="Customer" /></form:label>
                    <form:select multiple="false" path="customer.id" items="${customers}" itemLabel="name" itemValue="id" />
                    <form:errors cssClass="text-error" path="customer" />
                </div>
                <div class="control-group">
                    <form:label path="amount"><spring:message code="Amount" /></form:label>
                    <form:input path="amount" />
                    <form:errors cssClass="text-error" path="amount" />
                </div>
            </fieldset>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="form-actions">
                <c:if test="${ !empty invoiceAttribute.id }">
                    <a class="btn btn-danger" href="javascript:$('#form-${invoiceAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                </c:if>
                <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </div>
    </div>
</form:form>

<c:if test="${ !empty invoiceAttribute.id }">
    <spring:url var="delete" value="/invoice/delete">
        <spring:param name="id" value="${forwardingAttribute.id}" />
    </spring:url>
    <form:form id="form-${invoiceAttribute.id}" modelAttribute="invoiceAttribute" action="${delete}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<c:if test="${invoiceAttribute.id != null}">
    <div class="row-fluid">
        <div class="span12">
            <spring:message code="Document No" var="documentNo" />

            <spring:url var="waybillSave" value="/invoice/waybill/add/${invoiceAttribute.id}" />
            <form:form modelAttribute="waybillFilterByDocumentNoForm" action="${waybillSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:input id="autocomplete" path="documentNo" placeholder="${documentNo}" />
                <button class="btn" type="submit">
                    <i class="icon-ok"></i>
                </button>
            </form:form>

            <hr>

            <aripd:datatables datasource="/invoice/waybill/get/${invoiceAttribute.id}" id="waybills" dataUrlDelete="/invoice/waybill/remove" actionColumn="5" caption="Waybills">
                <aripd:datatablescolumn label="Document No" field="documentNo"/>
                <aripd:datatablescolumn label="Document Date" field="documentDate"/>
                <aripd:datatablescolumn label="Company" field="company"/>
                <aripd:datatablescolumn label="Driver" field="driver"/>
                <aripd:datatablescolumn label="Plate" field="plate"/>
                <aripd:datatablescolumn label="Action" field="id"/>
            </aripd:datatables>
        </div>
    </div>
</c:if>

<spring:url var="autocompleteSource" value="/waybill/autocomplete" />

<script>
    $(function() {
        $("[name=documentDate]").datepicker({
            maxDate: new Date()
        });

        $("#autocomplete").autocomplete({
            source: "${autocompleteSource}",
            minLength: 2
        });
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />