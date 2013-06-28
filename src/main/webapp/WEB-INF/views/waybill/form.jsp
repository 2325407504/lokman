<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="waybillList" value="/waybill/list" />
<spring:url var="waybillShow" value="/waybill/show/${waybillAttribute.id}" />
<spring:url var="waybillEdit" value="/waybill/edit/${waybillAttribute.id}" />
<spring:url var="waybillNew" value="/waybill/new" />
<spring:url var="waybillImport" value="/waybill/import/xls" />
<spring:url var="waybillExport" value="/waybill/export/xls" />
<spring:url var="outgoingExport" value="/outgoing/export/xls" />
<spring:url var="waybillSave" value="/waybill/save" />
<spring:url var="outgoingSave" value="/outgoing/save/${waybillAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${waybillList}"><spring:message code="Waybills" /></a></li>
        <c:choose>
            <c:when test="${ !empty waybillAttribute.id }">
            <li class="active"><a href="${waybillEdit}"><spring:message code="Entry No" />: ${waybillAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${waybillNew}"><spring:message code="New Entry" /></a></li>
            </c:otherwise>
        </c:choose>
    <li class=""><a href="${waybillImport}"><spring:message code="Import" /></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${waybillExport}"><spring:message code="Waybill" /></a></li>
            <li><a href="${outgoingExport}"><spring:message code="Outgoings" /></a></li>
            </ul>
        </li>
    </ul>

<form:form modelAttribute="waybillAttribute" action="${waybillSave}" method="post">
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:hidden path="id" />
                <div class="control-group">
                    <form:checkbox path="submitted" /> <spring:message code="Submitted by user" />
                    <form:errors cssClass="text-error" path="submitted" />
                </div>
                <div class="control-group">
                    <form:label path="account"><spring:message code="Account" /></form:label>
                    <form:select multiple="false" path="account.id" items="${accounts}" itemLabel="client.fullname" itemValue="id" />
                    <form:errors cssClass="text-error" path="account" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <legend><spring:message code="Waybill" /></legend>
                <div class="control-group">
                    <form:label path="documentDate"><spring:message code="Document Date" /></form:label>
                    <form:input type="datetime" id="waybillDate" path="documentDate" />
                    <form:errors cssClass="text-error" path="documentDate" />
                </div>
                    <div class="control-group">
                    <form:label path="documentNo"><spring:message code="Document No" /></form:label>
                    <form:input path="documentNo" />
                    <form:errors cssClass="text-error" path="documentNo" />
                </div>
                <div class="control-group">
                    <form:label path="company"><spring:message code="Company" /></form:label>
                    <form:input path="company" />
                    <form:errors cssClass="text-error" path="company" />
                </div>
                <div class="control-group">
                    <form:label path="driver"><spring:message code="Driver" /></form:label>
                    <form:input path="driver" />
                    <form:errors cssClass="text-error" path="driver" />
                </div>
                <div class="control-group">
                    <form:label path="plate"><spring:message code="Plate" /></form:label>
                    <form:input path="plate" />
                    <form:errors cssClass="text-error" path="plate" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <legend><spring:message code="Invoice" /></legend>
                    <div class="control-group">
                    <form:label path="invoice.customer"><spring:message code="Customer" /></form:label>
                    <form:select multiple="false" path="invoice.customer.id" items="${customers}" itemLabel="name" itemValue="id" />
                    <form:errors cssClass="text-error" path="invoice.customer" />
                </div>
                <div class="control-group">
                    <form:label path="invoice.documentDate"><spring:message code="Document Date" /></form:label>
                    <form:input type="datetime" id="invoiceDate" path="invoice.documentDate" />
                    <form:errors cssClass="text-error" path="invoice.documentDate" />
                </div>
                <div class="control-group">
                    <form:label path="invoice.documentNo"><spring:message code="Document No" /></form:label>
                    <form:input path="invoice.documentNo" />
                    <form:errors cssClass="text-error" path="invoice.documentNo" />
                </div>
                <div class="control-group">
                    <form:label path="invoice.amount"><spring:message code="Amount" /></form:label>
                    <form:input path="invoice.amount" />
                    <form:errors cssClass="text-error" path="invoice.amount" />
                </div>
            </fieldset>
        </div>
    </div>
    <div class="form-actions">
        <c:if test="${ !empty waybillAttribute.id }">
            <a class="btn btn-danger" href="javascript:$('#form-${waybillAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
        </c:if>
        <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
        </div>
</form:form>

<c:if test="${ !empty waybillAttribute.id }">
    <spring:url var="deleteUrl" value="/waybill/delete?id=${waybillAttribute.id}" />
    <form:form id="form-${waybillAttribute.id}" modelAttribute="waybillAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<c:if test="${waybillAttribute.id != null}">
    <div class="row-fluid">
        <div class="span12">
            <fmt:message key="Product" var="Product"/>
            <fmt:message key="Weight" var="Weight"/>
            <fmt:message key="Remark" var="Remark"/>

            <form:form modelAttribute="outgoingAttribute" action="${outgoingSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:select multiple="false" path="product.id" items="${products}" itemLabel="name" itemValue="id" />
                <form:input path="weight" cssClass="input-mini" placeholder="${Weight}" />
                <form:input path="remark" cssClass="input-small" placeholder="${Remark}" />
                <button class="btn" type="submit"><i class="icon-ok"></i></button>
                </form:form>

            <hr>

            <aripd:datatables datasource="/outgoing/get/${waybillAttribute.id}" id="outgoings" dataUrlDelete="/outgoing/delete" actionColumn="4" caption="Outgoings">
                <aripd:column label="Product" field="product.name"/>
                <aripd:column label="Remark" field="remark"/>
                <aripd:column label="Weight" field="weight"/>
                <aripd:column label="Action" field="id"/>
            </aripd:datatables>
        </div>
    </div>
</c:if>

<script>
    $(function() {
        $("#waybillDate, #invoiceDate").datetimepicker({
            maxDate: new Date()
        });
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />