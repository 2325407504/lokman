<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Memberlogs" />
    <jsp:param name="property" value="memberlog" />
    <jsp:param name="report" value="true" />
    <jsp:param name="active" value="report" />
</jsp:include>

<spring:message code="Starting Time" var="startingTime" />
<spring:message code="Ending Time" var="endingTime" />

<p class="lead"><spring:message code="Memberlog" /></p>
<spring:url var="memberlogReport" value="/memberlog/report" />
<form:form modelAttribute="memberlogFilterByIntervalForm" action="${memberlogReport}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <div class="row-fluid">
        <div class="span12">
            <div class="control-group">
                <form:label path="startingTime">${startingTime} - ${endingTime}</form:label>
                <form:input id="start1" path="startingTime" cssClass="input-medium" placeholder="${startingTime}" />
                <form:errors cssClass="text-error" path="startingTime" />
                <form:input id="end1" path="endingTime" cssClass="input-medium" placeholder="${endingTime}" />
                <form:errors cssClass="text-error" path="endingTime" />
            </div>
            <div class="control-group">
                <form:label path="account"><spring:message code="Account" /></form:label>
                <form:select multiple="false" path="account.id" cssClass="input-medium" items="${accounts}" itemLabel="employee.fullname" itemValue="id" />
            </div>
            <div class="control-group">
                <button class="btn" type="submit">
                    <i class="icon-download-alt"></i> <spring:message code="Download" />
                </button>
            </div>
        </div>
    </div>
</form:form>

<script type="text/javascript">
    var start1 = $('[name=startingTime]');
    var end1 = $('[name=endingTime]');

    start1.datetimepicker({
        showButtonPanel: false,
        maxDate: new Date(),
        showMinute: false,
        hourGrid: 8,
        stepHour: 8,
        hourText: "Vardiya",
        onClose: function(dateText, inst) {
            if (end1.val() != '') {
                var testStartDate = start1.datetimepicker('getDate');
                var testEndDate = end1.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    end1.datetimepicker('setDate', testStartDate);
            }
            else {
                end1.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            end1.datetimepicker('option', 'minDate', start1.datetimepicker('getDate'));
        }
    });
    end1.datetimepicker({
        showButtonPanel: false,
        maxDate: new Date(),
        showMinute: false,
        hourGrid: 8,
        stepHour: 8,
        hourText: "Vardiya",
        onClose: function(dateText, inst) {
            if (start1.val() != '') {
                var testStartDate = start1.datetimepicker('getDate');
                var testEndDate = end1.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    start1.datetimepicker('setDate', testEndDate);
            }
            else {
                start1.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            start1.datetimepicker('option', 'maxDate', end1.datetimepicker('getDate'));
        }
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />