<%@ include file="/WEB-INF/views/header.jsp" %>

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
    <li class=""><a href="${waybillList}"><spring:message code="Waybills"></spring:message></a></li>
        <c:choose>
            <c:when test="${ !empty waybillAttribute.id }">
            <li class="active"><a href="${waybillEdit}"><spring:message code="Entry No"></spring:message>: ${waybillAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${waybillNew}"><spring:message code="New Entry"></spring:message></a></li>
            </c:otherwise>
        </c:choose>
    <li class=""><a href="${waybillImport}"><spring:message code="Import"></spring:message></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export"></spring:message>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${waybillExport}"><spring:message code="Waybill"></spring:message></a></li>
            <li><a href="${outgoingExport}"><spring:message code="Outgoings"></spring:message></a></li>
            </ul>
        </li>
    </ul>

    <div class="row-fluid">
        <div class="span6">
        <form:form modelAttribute="waybillAttribute" action="${waybillSave}" method="post">
            <fieldset>
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:hidden path="id" />
                <div class="control-group">
                    <form:label path="account"><spring:message code="Account"></spring:message></form:label>
                    <form:select multiple="false" path="account.id" items="${accounts}" itemLabel="client.fullname" itemValue="id" />
                    <form:errors cssClass="text-error" path="account" />
                </div>
            </fieldset>
            <fieldset>
                <legend><spring:message code="Waybill"></spring:message></legend>

                    <div class="control-group">
                    <form:label path="documentDate"><spring:message code="Document Date"></spring:message></form:label>
                    <form:input type="datetime" path="documentDate" />
                    <form:errors cssClass="text-error" path="documentDate" />
                </div>
                <div class="control-group">
                    <form:label path="documentNo"><spring:message code="Document No"></spring:message></form:label>
                    <form:input path="documentNo" />
                    <form:errors cssClass="text-error" path="documentNo" />
                </div>
                <div class="control-group">
                    <form:label path="company"><spring:message code="Company"></spring:message></form:label>
                    <form:input path="company" />
                    <form:errors cssClass="text-error" path="company" />
                </div>
                <div class="control-group">
                    <form:label path="driver"><spring:message code="Driver"></spring:message></form:label>
                    <form:input path="driver" />
                    <form:errors cssClass="text-error" path="driver" />
                </div>
                <div class="control-group">
                    <form:label path="plate"><spring:message code="Plate"></spring:message></form:label>
                    <form:input path="plate" />
                    <form:errors cssClass="text-error" path="plate" />
                </div>
            </fieldset>
            <fieldset>
                <legend><spring:message code="Invoice"></spring:message></legend>
                    <div class="control-group">
                    <form:label path="invoice.customer"><spring:message code="Customer"></spring:message></form:label>
                    <form:select multiple="false" path="invoice.customer.id" items="${customers}" itemLabel="name" itemValue="id" />
                    <form:errors cssClass="text-error" path="invoice.customer" />
                </div>
                <div class="control-group">
                    <form:label path="invoice.documentNo"><spring:message code="Document No"></spring:message></form:label>
                    <form:input path="invoice.documentNo" />
                    <form:errors cssClass="text-error" path="invoice.documentNo" />
                </div>
                <div class="control-group">
                    <form:label path="invoice.documentDate"><spring:message code="Document Date"></spring:message></form:label>
                    <form:input type="datetime" path="invoice.documentDate" />
                    <form:errors cssClass="text-error" path="invoice.documentDate" />
                </div>
                <div class="control-group">
                    <form:label path="invoice.amount"><spring:message code="Amount"></spring:message></form:label>
                    <form:input path="invoice.amount" />
                    <form:errors cssClass="text-error" path="invoice.amount" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty waybillAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${waybillAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete"></spring:message></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
                    </div>
                </fieldset>
        </form:form>

        <c:if test="${ !empty waybillAttribute.id }">
            <spring:url var="deleteUrl" value="/waybill/delete?id=${waybillAttribute.id}" />
            <form:form id="form-${waybillAttribute.id}" modelAttribute="waybillAttribute" action="${deleteUrl}" method="delete">
                <form:hidden path="id" />
            </form:form>
        </c:if>
    </div>
    <div class="span6">
        <fmt:message key="Code" var="Code"/>
        <fmt:message key="Company" var="Company"/>
        <fmt:message key="County" var="County"/>
        <fmt:message key="City" var="City"/>
        <fmt:message key="Weight" var="Weight"/>

        <c:if test="${waybillAttribute.id != null}">
            <form:form modelAttribute="outgoingAttribute" action="${outgoingSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:input path="code" cssClass="input-small" placeholder="${Code}" />
                <form:input path="company" cssClass="input-small" placeholder="${Company}" />
                <form:input path="county" cssClass="input-small" placeholder="${County}" />
                <form:input path="city" cssClass="input-small" placeholder="${City}" />
                <form:input path="loadWeightInTonne" cssClass="input-mini" placeholder="${Weight}" />
                <button class="btn" type="submit"><i class="icon-ok"></i></button>
                </form:form>

            <hr>

            <aripd:datatables datasource="/outgoing/get/${waybillAttribute.id}" id="outgoings" dataUrlDelete="/outgoing/delete" actionColumn="5">
                <aripd:column label="Code" field="code"/>
                <aripd:column label="Company" field="company"/>
                <aripd:column label="County" field="county"/>
                <aripd:column label="City" field="city"/>
                <aripd:column label="Weight" field="loadWeightInTonne"/>
                <aripd:column label="Action" field="id"/>
            </aripd:datatables>
        </c:if>
    </div>
</div>

<script type="text/javascript">
<!--
    var startDateTextBox = $('#startingTime');
    var endDateTextBox = $('#endingTime');

    startDateTextBox.datetimepicker({
        onClose: function(dateText, inst) {
            if (endDateTextBox.val() != '') {
                var testStartDate = startDateTextBox.datetimepicker('getDate');
                var testEndDate = endDateTextBox.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    endDateTextBox.datetimepicker('setDate', testStartDate);
            }
            else {
                endDateTextBox.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            endDateTextBox.datetimepicker('option', 'minDate', startDateTextBox.datetimepicker('getDate'));
        }
    });
    endDateTextBox.datetimepicker({
        onClose: function(dateText, inst) {
            if (startDateTextBox.val() != '') {
                var testStartDate = startDateTextBox.datetimepicker('getDate');
                var testEndDate = endDateTextBox.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    startDateTextBox.datetimepicker('setDate', testEndDate);
            }
            else {
                startDateTextBox.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            startDateTextBox.datetimepicker('option', 'maxDate', endDateTextBox.datetimepicker('getDate'));
        }
    });
//-->
</script>

<%@ include file="/WEB-INF/views/footer.jsp" %>