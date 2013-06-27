<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/forwarding/list" />
<spring:url var="forwardingShow" value="/forwarding/show/${forwardingAttribute.id}" />
<spring:url var="forwardingEdit" value="/forwarding/edit/${forwardingAttribute.id}" />
<spring:url var="forwardingNew" value="/forwarding/new" />
<spring:url var="forwardingImport" value="/forwarding/import/xls" />
<spring:url var="forwardingExport" value="/forwarding/export/xls" />
<spring:url var="uatfExport" value="/uatf/export/xls" />
<spring:url var="forwardingSave" value="/forwarding/save" />
<spring:url var="uatfSave" value="/uatf/save/${forwardingAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${forwardingList}"><spring:message code="Forwardings" /></a></li>
        <c:choose>
            <c:when test="${ !empty forwardingAttribute.id }">
            <li class="active"><a href="${forwardingEdit}"><spring:message code="Entry No" />: ${forwardingAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${forwardingNew}"><spring:message code="New Entry" /></a></li>
            </c:otherwise>
        </c:choose>
    <li class=""><a href="${forwardingImport}"><spring:message code="Import" /></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${forwardingExport}"><spring:message code="Waybill" /></a></li>
            <li><a href="${uatfExport}"><spring:message code="UATF" /></a></li>
            </ul>
        </li>
    </ul>

    <div class="row-fluid">
        <div class="span6">
        <form:form modelAttribute="forwardingAttribute" action="${forwardingSave}" method="post">
            <form:errors path="*" cssClass="alert alert-error" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="control-group">
                    <form:checkbox path="submitted" /> <spring:message code="Submitted by user" />
                    <form:errors cssClass="text-error" path="submitted" />
                </div>
                <div class="control-group">
                    <form:label path="account"><spring:message code="Account" /></form:label>
                    <form:select multiple="false" path="account.id" items="${accounts}" itemLabel="client.fullname" itemValue="id" />
                    <form:errors cssClass="text-error" path="account" />
                </div>
                <div class="control-group">
                    <form:label path="waybillNo"><spring:message code="Waybill No" /></form:label>
                    <form:input path="waybillNo" />
                    <form:errors cssClass="text-error" path="waybillNo" />
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
                <div class="control-group">
                    <spring:message code="Starting Time" var="startingTime" />
                    <spring:message code="Ending Time" var="endingTime" />
                    <form:label path="startingTime">${startingTime} - ${endingTime}</form:label>
                    <form:input type="datetime" path="startingTime" cssClass="input-medium" placeholder="${startingTime}" />
                    <form:errors cssClass="text-error" path="startingTime" />
                    <form:input type="datetime" path="endingTime" cssClass="input-medium" placeholder="${endingTime}" />
                    <form:errors cssClass="text-error" path="endingTime" />
                </div>
                <div class="control-group">
                    <form:label path="endingPoint"><spring:message code="Ending Point" /></form:label>
                    <form:input path="endingPoint" />
                    <form:errors cssClass="text-error" path="endingPoint" />
                </div>
                <div class="control-group">
                    <form:label path="loadWeightInTonne"><spring:message code="Weight" /></form:label>
                    <form:input path="loadWeightInTonne" />
                    <form:errors cssClass="text-error" path="loadWeightInTonne" />
                </div>
                <div class="control-group">
                    <form:label path="shippingCost"><spring:message code="Shipping Cost" /></form:label>
                    <form:input path="shippingCost" />
                    <form:errors cssClass="text-error" path="shippingCost" />
                </div>
                <div class="control-group">
                    <form:label path="subcontractor"><spring:message code="Subcontractor" /></form:label>
                    <form:select path="subcontractor.id" multiple="false" items="${subcontractors}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="subcontractor" />
                </div>
                <div class="control-group">
                    <form:label path="quota"><spring:message code="Quota" /></form:label>
                    <form:select path="quota.id" multiple="false" items="${quotas}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="quota" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty forwardingAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${forwardingAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
                    </div>
                </fieldset>
        </form:form>

        <c:if test="${ !empty forwardingAttribute.id }">
            <spring:url var="deleteUrl" value="/forwarding/delete?id=${forwardingAttribute.id}" />
            <form:form id="form-${forwardingAttribute.id}" modelAttribute="forwardingAttribute" action="${deleteUrl}" method="delete">
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

        <c:if test="${forwardingAttribute.id != null}">
            <form:form modelAttribute="uatfAttribute" action="${uatfSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:input path="code" cssClass="input-small" placeholder="${Code}" />
                <form:input path="company" cssClass="input-small" placeholder="${Company}" />
                <form:input path="county" cssClass="input-small" placeholder="${County}" />
                <form:input path="city" cssClass="input-small" placeholder="${City}" />
                <form:input path="loadWeightInTonne" cssClass="input-mini" placeholder="${Weight}" />
                <button class="btn" type="submit"><i class="icon-ok"></i></button>
                </form:form>

            <hr>

            <aripd:datatables datasource="/uatf/get/${forwardingAttribute.id}" id="uatfs" dataUrlDelete="/uatf/delete" actionColumn="5">
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