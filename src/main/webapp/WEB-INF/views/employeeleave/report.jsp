<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employeeeleaves" />
    <jsp:param name="property" value="employeeleave" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="report" />
</jsp:include>

<spring:message code="Starting" var="starting" />
<spring:message code="Ending" var="ending" />
<spring:message code="All" var="all" />

<p class="lead"><spring:message code="Employeeeleave" /></p>
<spring:url var="employeeleaveReport" value="/employeeleave/report" />
<form:form modelAttribute="employeeleaveFilterByIntervalForm" action="${employeeleaveReport}" method="post" class="form-inline">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:input path="starting" cssClass="input-medium" placeholder="${starting}" />
    <form:input path="ending" cssClass="input-medium" placeholder="${ending}" />
    <form:select multiple="false" path="account.id" cssClass="input-medium">
        <form:option value="" label="--- ${all}" />
        <form:options items="${accounts}" itemLabel="employee.fullname" itemValue="id" />
    </form:select>
    <button class="btn" type="submit">
        <i class="icon-download-alt"></i> <spring:message code="Download" />
    </button>
</form:form>


<script type="text/javascript">
    var start1 = $('#starting');
    var end1 = $('#ending');

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