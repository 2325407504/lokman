<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Forwardings" />
    <jsp:param name="property" value="forwarding" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="save" value="/forwarding/save" />
<form:form modelAttribute="forwardingAttribute" action="${save}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <div class="row-fluid">
        <div class="span4">
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
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="waybillNo"><spring:message code="Document No" /></form:label>
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
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
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
            </fieldset>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="form-actions">
                <c:if test="${ !empty forwardingAttribute.id }">
                    <a class="btn btn-danger" href="javascript:$('#form-${forwardingAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                </c:if>
                <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </div>
    </div>
</form:form>

<c:if test="${ !empty forwardingAttribute.id }">
    <spring:url var="delete" value="/forwarding/delete">
        <spring:param name="id" value="${forwardingAttribute.id}" />
    </spring:url>
    <form:form id="form-${forwardingAttribute.id}" modelAttribute="forwardingAttribute" action="${delete}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<c:if test="${forwardingAttribute.id != null}">
    <div class="row-fluid">
        <div class="span12">
            <fmt:message key="Code" var="Code"/>
            <fmt:message key="Company" var="Company"/>
            <fmt:message key="County" var="County"/>
            <fmt:message key="City" var="City"/>
            <fmt:message key="Weight" var="Weight"/>

            <spring:url var="uatfSave" value="/uatf/save/${forwardingAttribute.id}" />
            <form:form modelAttribute="uatfAttribute" action="${uatfSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:input path="code" cssClass="input-small" placeholder="${Code}" />
                <form:input path="company" cssClass="input-small" placeholder="${Company}" />
                <form:input path="county" cssClass="input-small" placeholder="${County}" />
                <form:input path="city" cssClass="input-small" placeholder="${City}" />
                <form:input path="loadWeightInTonne" cssClass="input-mini" placeholder="${Weight}" />
                <button class="btn" type="submit">
                    <i class="icon-ok"></i>
                </button>
            </form:form>

            <hr>

            <aripd:datatables datasource="/uatf/get/${forwardingAttribute.id}" id="uatfs" dataUrlDelete="/uatf/delete" actionColumn="5" caption="Uatf">
                <aripd:column label="Code" field="code"/>
                <aripd:column label="Company" field="company"/>
                <aripd:column label="County" field="county"/>
                <aripd:column label="City" field="city"/>
                <aripd:column label="Weight" field="loadWeightInTonne"/>
                <aripd:column label="Action" field="id"/>
            </aripd:datatables>
        </div>
    </div>
</c:if>

<script type="text/javascript">
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
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />