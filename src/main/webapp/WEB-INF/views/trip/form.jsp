<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/trip/list" />
<spring:url var="tripShow" value="/trip/show/{id}">
    <spring:param name="id" value="${tripAttribute.id}" />
</spring:url>
<spring:url var="tripEdit" value="/trip/edit/{id}">
    <spring:param name="id" value="${tripAttribute.id}" />
</spring:url>
<spring:url var="tripNew" value="/trip/new" />
<spring:url var="tripSave" value="/trip/save" />
<spring:url var="tripImport" value="/trip/import/xls" />
<spring:url var="tripExport" value="/trip/export/xls" />
<spring:url var="tripChart" value="/trip/chart" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${tripList}"><spring:message code="Trips"></spring:message></a></li>
        <c:choose>
            <c:when test="${ !empty tripAttribute.id }">
            <li class="active"><a href="${tripEdit}"><spring:message code="Entry No"></spring:message>: ${tripAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${tripNew}"><spring:message code="New Entry"></spring:message></a></li>
            </c:otherwise>
        </c:choose>
    <li class=""><a href="${tripImport}"><spring:message code="Import"></spring:message></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export"></spring:message>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${tripExport}"><spring:message code="Export"></spring:message></a></li>
            <li><a href="${tripChart}"><spring:message code="Chart"></spring:message></a></li>
            </ul>
        </li>
    </ul>

    <div class="row-fluid">
        <div class="span12">
        <form:form id="trip" modelAttribute="tripAttribute" action="${tripSave}" method="post">
            <form:errors path="*" cssClass="alert alert-error" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="control-group">
                    <form:checkbox path="submitted" /> <spring:message code="Submitted by user"></spring:message>
                    <form:errors cssClass="text-error" path="submitted" />
                </div>
                <div class="control-group">
                    <form:label path="account"><spring:message code="Account"></spring:message></form:label>
                    <form:select multiple="false" path="account.id" items="${accounts}" itemLabel="customer.fullname" itemValue="id" />
                    <form:errors cssClass="text-error" path="account" />
                </div>
                <div class="control-group">
                    <form:label path="truck"><spring:message code="Truck"></spring:message></form:label>
                    <c:if test="${ !empty tripAttribute.id }">
                        <form:select id="truck" multiple="false" path="truck.id" items="${trucks}" itemLabel="plate" itemValue="id" />
                    </c:if>
                    <c:if test="${ empty tripAttribute.id }">
                        <form:select id="truck" multiple="false" path="truck.id" items="${trucks}" itemLabel="plate" itemValue="id" onchange="getKilometer(this.form);" />
                    </c:if>
                    <form:errors cssClass="text-error" path="truck" />
                </div>
                <div class="control-group">
                    <form:label path="driver"><spring:message code="Driver"></spring:message></form:label>
                    <form:select path="driver.id" multiple="false" items="${drivers}" itemLabel="name" itemValue="id" />
                    <form:errors cssClass="text-error" path="driver" />
                </div>
                <div class="control-group">
                    <spring:message code="Starting Point" var="startingPoint"></spring:message>
                    <spring:message code="Ending Point" var="endingPoint"></spring:message>
                    <form:label path="startingPoint">${startingPoint} - ${endingPoint}</form:label>
                    <form:input path="startingPoint" cssClass="input-medium" placeholder="${startingPoint}" />
                    <form:errors cssClass="text-error" path="startingPoint" />
                    <form:input path="endingPoint" cssClass="input-medium" placeholder="${endingPoint}" />
                    <form:errors cssClass="text-error" path="endingPoint" />
                </div>
                <div class="control-group">
                    <spring:message code="Starting Km" var="startingKm"></spring:message>
                    <spring:message code="Ending Km" var="endingKm"></spring:message>
                    <form:label path="startingKm">${startingKm} - ${endingKm}</form:label>
                    <form:input path="startingKm" cssClass="input-medium" placeholder="${startingKm}" />
                    <form:errors cssClass="text-error" path="startingKm" />
                    <form:input path="endingKm" cssClass="input-medium" placeholder="${endingKm}" />
                    <form:errors cssClass="text-error" path="endingKm" />
                </div>
                <div class="control-group">
                    <spring:message code="Starting Time" var="startingTime"></spring:message>
                    <spring:message code="Ending Time" var="endingTime"></spring:message>
                    <form:label path="startingTime">${startingTime} - ${endingTime}</form:label>
                    <form:input type="datetime" path="startingTime" cssClass="input-medium" placeholder="${startingTime}" />
                    <form:errors cssClass="text-error" path="startingTime" />
                    <form:input type="datetime" path="endingTime" cssClass="input-medium" placeholder="${endingTime}" />
                    <form:errors cssClass="text-error" path="endingTime" />
                </div>
                <div class="control-group">
                    <form:label path="loadWeightInTonne"><spring:message code="Weight"></spring:message></form:label>
                    <form:input path="loadWeightInTonne" />
                    <form:errors cssClass="text-error" path="loadWeightInTonne" />
                </div>
                <div class="control-group">
                    <form:label path="remark" class="control-label"><spring:message code="Remark"></spring:message></form:label>
                    <form:textarea path="remark" />
                    <form:errors cssClass="text-error" path="remark" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty tripAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${tripAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete"></spring:message></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
                    </div>
                </fieldset>
        </form:form>
    </div>
</div>

<c:if test="${ !empty tripAttribute.id }">
    <spring:url var="deleteUrl" value="/trip/delete">
        <spring:param name="id" value="${tripAttribute.id}" />
    </spring:url>
    <form:form id="form-${tripAttribute.id}" modelAttribute="tripAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<c:if test="${ empty tripAttribute.id }">
    <script type="text/javascript">
        $(function() {
            getKilometer($('form#trip'));
        });

        function getKilometer(e) {
            var truck_id = $(e).find('#truck').val();
            $.ajax({
                type: "GET",
                url: "truck/get/" + truck_id + "/kilometer",
                //data: "name=" + name + "&education=" + education,
                success: function(response) {
                    $(e).find('#startingKm').val(response);
                },
                error: function(e) {
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