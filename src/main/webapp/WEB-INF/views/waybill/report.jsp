<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Waybills" />
    <jsp:param name="property" value="waybill" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="report" />
</jsp:include>

<p class="lead"><spring:message code="Waybill" /></p>
<spring:url var="waybillReport" value="/waybill/report" />
<form:form modelAttribute="waybillFilterByIntervalForm" action="${waybillReport}" method="post" class="form-inline">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:input id="start1" path="startingTime" />
    <form:input id="end1" path="endingTime" />
    <button class="btn" type="submit">
        <i class="icon-download-alt"></i> <spring:message code="Download" />
    </button>
</form:form>

<p class="lead"><spring:message code="Outgoing" /></p>
<spring:url var="outgoingReport" value="/outgoing/report" />
<form:form modelAttribute="outgoingFilterByIntervalForm" action="${outgoingReport}" method="post" class="form-inline">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:input id="start2" path="startingTime" />
    <form:input id="end2" path="endingTime" />
    <button class="btn" type="submit">
        <i class="icon-download-alt"></i> <spring:message code="Download" />
    </button>
</form:form>

<script type="text/javascript">
    var start1 = $('#start1');
    var end1 = $('#end1');

    start1.datetimepicker({
        maxDate: new Date(),
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
        maxDate: new Date(),
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
        maxDate: new Date(),
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
        maxDate: new Date(),
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

</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />