<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Productions" />
    <jsp:param name="property" value="production" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="report" />
</jsp:include>

<p class="lead"><spring:message code="Production" /></p>
<spring:url var="productionReport" value="/production/report" />
<form:form modelAttribute="productionFilterByIntervalForm" action="${productionReport}" method="post" class="form-inline">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:input id="start1" path="startingTime" />
    <form:input id="end1" path="endingTime" />
    <button class="btn" type="submit">
        <i class="icon-search"></i>
    </button>
</form:form>

<p class="lead"><spring:message code="Compensation" /></p>
<spring:url var="compensationReport" value="/compensation/report" />
<form:form modelAttribute="compensationFilterByIntervalForm" action="${compensationReport}" method="post" class="form-inline">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:input id="start2" path="startingTime" />
    <form:input id="end2" path="endingTime" />
    <button class="btn" type="submit">
        <i class="icon-search"></i>
    </button>
</form:form>

<p class="lead"><spring:message code="Machine Time" /></p>
<spring:url var="machinetimeReport" value="/machinetime/report" />
<form:form modelAttribute="machinetimeFilterByIntervalForm" action="${machinetimeReport}" method="post" class="form-inline">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:input id="start3" path="startingTime" />
    <form:input id="end3" path="endingTime" />
    <button class="btn" type="submit">
        <i class="icon-search"></i>
    </button>
</form:form>

<script type="text/javascript">
    var start1 = $('#start1');
    var end1 = $('#end1');

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

    var start2 = $('#start2');
    var end2 = $('#end2');

    start2.datetimepicker({
        showButtonPanel: false,
        maxDate: new Date(),
        showMinute: false,
        hourGrid: 8,
        stepHour: 8,
        hourText: "Vardiya",
        onClose: function(dateText, inst) {
            if (end2.val() != '') {
                var testStartDate = start2.datetimepicker('getDate');
                var testEndDate = end2.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    end2.datetimepicker('setDate', testStartDate);
            }
            else {
                end2.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            end2.datetimepicker('option', 'minDate', start2.datetimepicker('getDate'));
        }
    });
    end2.datetimepicker({
        showButtonPanel: false,
        maxDate: new Date(),
        showMinute: false,
        hourGrid: 8,
        stepHour: 8,
        hourText: "Vardiya",
        onClose: function(dateText, inst) {
            if (start2.val() != '') {
                var testStartDate = start2.datetimepicker('getDate');
                var testEndDate = end2.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    start2.datetimepicker('setDate', testEndDate);
            }
            else {
                start2.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            start2.datetimepicker('option', 'maxDate', end2.datetimepicker('getDate'));
        }
    });

    var start3 = $('#start3');
    var end3 = $('#end3');

    start3.datetimepicker({
        showButtonPanel: false,
        maxDate: new Date(),
        showMinute: false,
        hourGrid: 8,
        stepHour: 8,
        hourText: "Vardiya",
        onClose: function(dateText, inst) {
            if (end3.val() != '') {
                var testStartDate = start3.datetimepicker('getDate');
                var testEndDate = end3.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    end3.datetimepicker('setDate', testStartDate);
            }
            else {
                end3.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            end3.datetimepicker('option', 'minDate', start3.datetimepicker('getDate'));
        }
    });
    end3.datetimepicker({
        showButtonPanel: false,
        maxDate: new Date(),
        showMinute: false,
        hourGrid: 8,
        stepHour: 8,
        hourText: "Vardiya",
        onClose: function(dateText, inst) {
            if (start3.val() != '') {
                var testStartDate = start3.datetimepicker('getDate');
                var testEndDate = end3.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    start3.datetimepicker('setDate', testEndDate);
            }
            else {
                start3.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            start3.datetimepicker('option', 'maxDate', end3.datetimepicker('getDate'));
        }
    });

</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />