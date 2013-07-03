<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Trips" />
    <jsp:param name="property" value="usertrip" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <spring:url var="save" value="/usertrip/save" />
        <form:form modelAttribute="usertripAttribute" action="${save}" method="post">
            <form:errors path="*" cssClass="alert alert-error" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="control-group">
                    <form:label path="truck"><spring:message code="Truck" /></form:label>
                    <form:select multiple="false" path="truck.id" items="${trucks}" itemLabel="plate" itemValue="id" />
                    <form:errors cssClass="text-error" path="truck" />
                </div>
                <div class="control-group">
                    <form:label path="driver"><spring:message code="Driver" /></form:label>
                    <form:select path="driver.id" multiple="false" items="${drivers}" itemLabel="name" itemValue="id" />
                    <form:errors cssClass="text-error" path="driver" />
                </div>
                <div class="control-group">
                    <form:label path="startingPoint"><spring:message code="Starting Point" /></form:label>
                    <form:input path="startingPoint" />
                    <form:errors cssClass="text-error" path="startingPoint" />
                </div>
                <div class="control-group">
                    <form:label path="startingKm"><spring:message code="Starting Km" /></form:label>
                    <form:input path="startingKm" />
                    <form:errors cssClass="text-error" path="startingKm" />
                </div>
                <div class="control-group">
                    <form:label path="startingTime"><spring:message code="Starting Time" /></form:label>
                    <form:input type="datetime" path="startingTime" />
                    <form:errors cssClass="text-error" path="startingTime" />
                </div>
                <div class="control-group">
                    <form:label path="endingPoint"><spring:message code="Ending Point" /></form:label>
                    <form:input path="endingPoint" />
                    <form:errors cssClass="text-error" path="endingPoint" />
                </div>
                <div class="control-group">
                    <form:label path="endingKm"><spring:message code="Ending Km" /></form:label>
                    <form:input path="endingKm" />
                    <form:errors cssClass="text-error" path="endingKm" />
                </div>
                <div class="control-group">
                    <form:label path="endingTime"><spring:message code="Ending Time" /></form:label>
                    <form:input type="datetime" path="endingTime" />
                    <form:errors cssClass="text-error" path="endingTime" />
                </div>
                <div class="control-group">
                    <form:label path="loadWeightInTonne"><spring:message code="Weight" /></form:label>
                    <form:input path="loadWeightInTonne" />
                    <form:errors cssClass="text-error" path="loadWeightInTonne" />
                </div>
                <div class="control-group">
                    <form:label path="remark" class="control-label"><spring:message code="Remark" /></form:label>
                    <div class="controls"><form:textarea path="remark" cssClass="span6" /></div>
                    <form:errors cssClass="text-error" path="remark" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty usertripAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${usertripAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
                </div>
            </fieldset>
        </form:form>
    </div>
</div>

<c:if test="${ !empty usertripAttribute.id }">
    <spring:url var="deleteUrl" value="/usertrip/delete?id=${usertripAttribute.id}" />
    <form:form id="form-${usertripAttribute.id}" modelAttribute="usertripAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<script type="text/javascript">
<!--
    var startDateTextBox = $('#startingTime');
    var endDateTextBox = $('#endingTime');

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
//-->
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />