<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Trips" />
    <jsp:param name="property" value="usertrip" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <spring:url var="save" value="/usertrip/save" />
        <form:form id="usertrip" modelAttribute="usertripAttribute" action="${save}" method="post">
            <form:errors path="*" cssClass="alert alert-error" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="control-group">
                    <form:label path="truck"><spring:message code="Truck" /></form:label>
                    <c:if test="${ !empty usertripAttribute.id }">
                        <form:select id="truck" multiple="false" path="truck.id" items="${trucks}" itemLabel="plate" itemValue="id" />
                    </c:if>
                    <c:if test="${ empty usertripAttribute.id }">
                        <form:select id="truck" multiple="false" path="truck.id" items="${trucks}" itemLabel="plate" itemValue="id" onchange="getKilometer(this.form);" />
                    </c:if>
                    <form:errors cssClass="text-error" path="truck" />
                </div>
                <div class="control-group">
                    <form:label path="driver"><spring:message code="Driver" /></form:label>
                    <form:select path="driver.id" multiple="false" items="${drivers}" itemLabel="name" itemValue="id" />
                    <form:errors cssClass="text-error" path="driver" />
                </div>
                <div class="control-group">
                    <spring:message code="Starting Point" var="startingPoint" />
                    <spring:message code="Ending Point" var="endingPoint" />
                    <form:label path="startingPoint">${startingPoint} - ${endingPoint}</form:label>
                    <form:input path="startingPoint" cssClass="input-medium" placeholder="${startingPoint}" />
                    <form:errors cssClass="text-error" path="startingPoint" />
                    <form:input path="endingPoint" cssClass="input-medium" placeholder="${endingPoint}" />
                    <form:errors cssClass="text-error" path="endingPoint" />
                </div>
                <div class="control-group">
                    <spring:message code="Starting Km" var="startingKm" />
                    <spring:message code="Ending Km" var="endingKm" />
                    <form:label path="startingKm">${startingKm} - ${endingKm}</form:label>
                    <form:input type="number" path="startingKm" cssClass="input-medium" placeholder="${startingKm}" />
                    <form:errors cssClass="text-error" path="startingKm" />
                    <form:input type="number" path="endingKm" cssClass="input-medium" placeholder="${endingKm}" />
                    <form:errors cssClass="text-error" path="endingKm" />
                </div>
                <div class="control-group">
                    <spring:message code="Starting Time" var="startingTime" />
                    <spring:message code="Ending Time" var="endingTime" />
                    <form:label path="startingTime">${startingTime} - ${endingTime}</form:label>
                    <form:input type="datetime" path="startingTime" cssClass="input-medium" placeholder="${startingTime}" />
                    <form:errors cssClass="text-error" path="startingTime" />
                    <form:input type="datetime" path="endingTime" cssClass="input-medium" placeholder="${endingTime}" />
                    <form:errors cssClass="text-error" path="endingTime" />
                </div>
                <div class="control-group">
                    <form:label path="loadWeightInTonne"><spring:message code="Weight" /></form:label>
                    <form:input path="loadWeightInTonne" />
                    <form:errors cssClass="text-error" path="loadWeightInTonne" />
                </div>
                <div class="control-group">
                    <form:label path="remark" class="control-label"><spring:message code="Remark" /></form:label>
                    <form:textarea path="remark" />
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
    <spring:url var="deleteUrl" value="/usertrip/delete">
        <spring:param name="id" value="${usertripAttribute.id}" />
    </spring:url>
    <form:form id="form-${usertripAttribute.id}" modelAttribute="usertripAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<c:if test="${ empty usertripAttribute.id }">
    <script type="text/javascript">
        $(function() {
            getKilometer($('form#usertrip'));
        });

        function getKilometer(e) {
            var truck_id = $(e).find('#truck').val();
            $.ajax({
                type: "GET",
                url: "truck/get/" + truck_id + "/kilometer",
                beforeSend: function() {
                },
                success: function(response) {
                    $(e).find('[name=startingKm]').val(response);
                    $(e).find('[name=startingKm]').attr('min', response);
                    $(e).find('[name=startingKm]').attr('max', response);
                    $(e).find('[name=endingKm]').val(response);
                    $(e).find('[name=endingKm]').attr('min', response);
                },
                error: function(e) {
                    console.log(e);
                    alert('Hata: ' + e);
                }
            });
        }
    </script>
</c:if>
<script type="text/javascript">
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
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />