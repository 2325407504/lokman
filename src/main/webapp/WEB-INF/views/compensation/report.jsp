<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="productionList" value="/production/list" />
<spring:url var="productionNew" value="/production/new" />
<spring:url var="productionImport" value="/production/import/xls" />
<spring:url var="compensationReport" value="/compensation/report" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${productionList}"><spring:message code="Productions" /></a></li>
    <li class=""><a href="${productionNew}"><spring:message code="New Entry" /></a></li>
    <li class=""><a href="${productionImport}"><spring:message code="Import" /></a></li>
    <li class="active dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Reports" />
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li><a href="${compensationReport}"><spring:message code="Compensation" /></a></li>
        </ul>
    </li>
</ul>

<p class="lead"><spring:message code="Compensation" /></p>
<spring:url var="compensationReport" value="/compensation/report" />
<form:form modelAttribute="compensationFilterByIntervalForm" action="${compensationReport}" method="post" class="form-inline">
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

<%@ include file="/WEB-INF/views/footer.jsp" %>