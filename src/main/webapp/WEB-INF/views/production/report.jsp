<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="subbar.jsp" >
    <jsp:param name="title" value="Production Report" />
</jsp:include>

<p class="lead"><spring:message code="Production" /></p>
<spring:url var="productionReport" value="/production/report" />
<form:form modelAttribute="productionFilterByIntervalForm" action="${productionReport}" method="post" class="form-inline">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:input path="startingTime" />
    <form:input path="endingTime" />
    <button class="btn" type="submit">
        <i class="icon-search"></i>
    </button>
</form:form>


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