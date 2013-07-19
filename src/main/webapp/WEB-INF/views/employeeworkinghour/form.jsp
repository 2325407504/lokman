<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Working Hours" />
    <jsp:param name="property" value="employeeworkinghour" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <spring:url var="employeeworkinghourSave" value="/employeeworkinghour/save" />
        <form:form modelAttribute="employeeworkinghourAttribute" action="${employeeworkinghourSave}" method="post">
            <form:errors path="*" cssClass="alert alert-error" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="control-group">
                    <form:checkbox path="submitted" /> <spring:message code="message.record.submitted" />
                    <form:errors cssClass="text-error" path="submitted" />
                </div>
                <div class="control-group">
                    <form:label path="account"><spring:message code="Account" /></form:label>
                    <form:select multiple="false" path="account.id" items="${accounts}" itemLabel="employee.fullname" itemValue="id" />
                    <form:errors cssClass="text-error" path="account" />
                </div>
                <div class="control-group">
                    <form:label path="employeeworkinghourtype"><spring:message code="Employee Working Hour Type" /></form:label>
                    <form:select path="employeeworkinghourtype.id" multiple="false" items="${employeeworkinghourtypes}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="employeeworkinghourtype" />
                </div>
                <div class="control-group">
                    <form:label path="startingDateTime"><spring:message code="Starting Time" /></form:label>
                    <form:input type="date" path="startingDateTime" />
                    <form:errors cssClass="text-error" path="startingDateTime" />
                </div>
                <div class="control-group">
                    <form:label path="endingDateTime"><spring:message code="Ending Time" /></form:label>
                    <form:input type="date" path="endingDateTime" />
                    <form:errors cssClass="text-error" path="endingDateTime" />
                </div>
                <div class="control-group">
                    <form:label path="remark"><spring:message code="Remark" /></form:label>
                    <form:textarea path="remark" />
                    <form:errors cssClass="text-error" path="remark" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty employeeworkinghourAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${employeeworkinghourAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
                </div>
            </fieldset>
        </form:form>

        <c:if test="${ !empty employeeworkinghourAttribute.id }">
            <spring:url var="deleteUrl" value="/employeeworkinghour/delete?id=${employeeworkinghourAttribute.id}" />
            <form:form id="form-${employeeworkinghourAttribute.id}" modelAttribute="employeeworkinghourAttribute" action="${deleteUrl}" method="delete">
                <form:hidden path="id" />
            </form:form>
        </c:if>
    </div>
</div>

<script type="text/javascript">
    var startDateTextBox = $('#startingDateTime');
    var endDateTextBox = $('#endingDateTime');

    startDateTextBox.datetimepicker({
        maxDate: new Date(),
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
        maxDate: new Date(),
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