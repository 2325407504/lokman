<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Leaves" />
    <jsp:param name="property" value="employeeleave" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <spring:url var="employeeleaveSave" value="/employeeleave/save" />
        <form:form modelAttribute="employeeleaveAttribute" action="${employeeleaveSave}" method="post">
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
                    <form:label path="employeeleavetype"><spring:message code="Employee Leave Type" /></form:label>
                    <form:select path="employeeleavetype.id" multiple="false" items="${employeeleavetypes}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="employeeleavetype" />
                </div>
                <div class="control-group">
                    <form:label path="startingDate"><spring:message code="Starting Date" /></form:label>
                    <form:input type="date" path="startingDate" />
                    <form:errors cssClass="text-error" path="startingDate" />
                </div>
                <div class="control-group">
                    <form:label path="endingDate"><spring:message code="Ending Date" /></form:label>
                    <form:input type="date" path="endingDate" />
                    <form:errors cssClass="text-error" path="endingDate" />
                </div>
                <div class="control-group">
                    <form:label path="remark"><spring:message code="Remark" /></form:label>
                    <form:textarea path="remark" />
                    <form:errors cssClass="text-error" path="remark" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty employeeleaveAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${employeeleaveAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
                </div>
            </fieldset>
        </form:form>

        <c:if test="${ !empty employeeleaveAttribute.id }">
            <spring:url var="deleteUrl" value="/employeeleave/delete?id=${employeeleaveAttribute.id}" />
            <form:form id="form-${employeeleaveAttribute.id}" modelAttribute="employeeleaveAttribute" action="${deleteUrl}" method="delete">
                <form:hidden path="id" />
            </form:form>
        </c:if>
    </div>
</div>

<script type="text/javascript">
    var startDateTextBox = $('[name=startingDate]');
    var endDateTextBox = $('[name=endingDate]');

    startDateTextBox.datepicker({
        maxDate: new Date(),
        onClose: function(dateText, inst) {
            if (endDateTextBox.val() != '') {
                var testStartDate = startDateTextBox.datepicker('getDate');
                var testEndDate = endDateTextBox.datepicker('getDate');
                if (testStartDate > testEndDate)
                    endDateTextBox.datepicker('setDate', testStartDate);
            }
            else {
                endDateTextBox.val(dateText);
            }
        },
        onSelect: function(selectedDate) {
            endDateTextBox.datepicker('option', 'minDate', startDateTextBox.datepicker('getDate'));
        }
    });
    endDateTextBox.datepicker({
        maxDate: new Date(),
        onClose: function(dateText, inst) {
            if (startDateTextBox.val() != '') {
                var testStartDate = startDateTextBox.datepicker('getDate');
                var testEndDate = endDateTextBox.datepicker('getDate');
                if (testStartDate > testEndDate)
                    startDateTextBox.datepicker('setDate', testEndDate);
            }
            else {
                startDateTextBox.val(dateText);
            }
        },
        onSelect: function(selectedDate) {
            startDateTextBox.datepicker('option', 'maxDate', endDateTextBox.datepicker('getDate'));
        }
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />