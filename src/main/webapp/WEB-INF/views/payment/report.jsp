<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Payments" />
    <jsp:param name="property" value="payment" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="active" value="report" />
</jsp:include>

<spring:message code="Starting Date" var="startingDate" />
<spring:message code="Ending Date" var="endingDate" />
<spring:message code="All" var="all" />

<p class="lead"><spring:message code="Payment" /></p>
<spring:url var="paymentReport" value="/payment/report" />
<form:form modelAttribute="paymentFilterByIntervalForm" action="${paymentReport}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <div class="row-fluid">
        <div class="span12">
            <div class="control-group">
                <form:label path="startingDate">${startingDate} - ${endingDate}</form:label>
                <form:input id="start1" path="startingDate" cssClass="input-medium" placeholder="${startingDate}" />
                <form:errors cssClass="text-error" path="startingDate" />
                <form:input id="end1" path="endingDate" cssClass="input-medium" placeholder="${endingDate}" />
                <form:errors cssClass="text-error" path="endingDate" />
            </div>
            <div class="control-group">
                <form:label path="employee"><spring:message code="Employee" /></form:label>
                <form:select multiple="false" path="employee.id" cssClass="input-medium">
                    <form:option value="" label="--- ${all}" />
                    <form:options items="${employees}" itemLabel="fullname" itemValue="id" />
                </form:select>
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
    var start1 = $('[name=startingDate]');
    var end1 = $('[name=endingDate]');

    start1.datepicker({
        maxDate: new Date(),
        onClose: function(dateText, inst) {
            if (end1.val() != '') {
                var testStartDate = start1.datepicker('getDate');
                var testEndDate = end1.datepicker('getDate');
                if (testStartDate > testEndDate)
                    end1.datepicker('setDate', testStartDate);
            }
            else {
                end1.val(dateText);
            }
        },
        onSelect: function(selectedDate) {
            end1.datepicker('option', 'minDate', start1.datepicker('getDate'));
        }
    });
    end1.datepicker({
        maxDate: new Date(),
        onClose: function(dateText, inst) {
            if (start1.val() != '') {
                var testStartDate = start1.datepicker('getDate');
                var testEndDate = end1.datepicker('getDate');
                if (testStartDate > testEndDate)
                    start1.datepicker('setDate', testEndDate);
            }
            else {
                start1.val(dateText);
            }
        },
        onSelect: function(selectedDate) {
            start1.datepicker('option', 'maxDate', end1.datepicker('getDate'));
        }
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />