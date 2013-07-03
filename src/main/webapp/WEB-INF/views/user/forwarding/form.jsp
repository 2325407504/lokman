<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="forwardingList" value="/user/forwarding/list" />
<spring:url var="forwardingShow" value="/user/forwarding/show/${forwardingAttribute.id}" />
<spring:url var="forwardingEdit" value="/user/forwarding/edit/${forwardingAttribute.id}" />
<spring:url var="forwardingNew" value="/user/forwarding/new" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${forwardingList}"><spring:message code="Forwardings" /></a></li>
        <c:choose>
            <c:when test="${ !empty forwardingAttribute.id }">
            <li class="active"><a href="${forwardingEdit}"><spring:message code="Entry No" />: ${forwardingAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${forwardingNew}"><spring:message code="New Entry" /></a></li>
            </c:otherwise>
        </c:choose>
</ul>

<div class="row-fluid">
    <div class="span4">
        <spring:url var="save" value="/user/forwarding/save" />
        <form:form modelAttribute="forwardingAttribute" action="${save}" method="post">
            <form:errors path="*" cssClass="alert alert-error" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="control-group">
                    <form:label path="waybillNo"><spring:message code="Document No" /></form:label>
                    <form:input type="text" path="waybillNo" />
                    <form:errors cssClass="text-error" path="waybillNo" />
                </div>
                <div class="control-group">
                    <form:label path="driver"><spring:message code="Driver" /></form:label>
                    <form:input path="driver" />
                    <form:errors cssClass="text-error" path="driver" />
                </div>
                <div class="control-group">
                    <form:label path="plate"><spring:message code="Plate" /></form:label>
                    <form:input path="plate" />
                    <form:errors cssClass="text-error" path="plate" />
                </div>
                <div class="control-group">
                    <form:label path="startingTime"><spring:message code="Starting Time" /></form:label>
                    <form:input type="text" path="startingTime" />
                    <form:errors cssClass="text-error" path="startingTime" />
                </div>
                <div class="control-group">
                    <form:label path="endingTime"><spring:message code="Ending Time" /></form:label>
                    <form:input type="text" path="endingTime" />
                    <form:errors cssClass="text-error" path="endingTime" />
                </div>
                <div class="control-group">
                    <form:label path="endingPoint"><spring:message code="Ending Point" /></form:label>
                    <form:input path="endingPoint" />
                    <form:errors cssClass="text-error" path="endingPoint" />
                </div>
                <div class="control-group">
                    <form:label path="loadWeightInTonne"><spring:message code="Weight" /></form:label>
                    <form:input path="loadWeightInTonne" />
                    <form:errors cssClass="text-error" path="loadWeightInTonne" />
                </div>
                <div class="control-group">
                    <form:label path="shippingCost"><spring:message code="Shipping Cost" /></form:label>
                    <form:input path="shippingCost" />
                    <form:errors cssClass="text-error" path="shippingCost" />
                </div>
                <div class="control-group">
                    <form:label path="subcontractor"><spring:message code="Subcontractor" /></form:label>
                    <form:select path="subcontractor.id" multiple="false" items="${subcontractors}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="subcontractor" />
                </div>
                <div class="control-group">
                    <form:label path="quota"><spring:message code="Quota" /></form:label>
                    <form:select path="quota.id" multiple="false" items="${quotas}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="quota" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty forwardingAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${forwardingAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
                </div>
            </fieldset>
        </form:form>

        <c:if test="${ !empty forwardingAttribute.id }">
            <spring:url var="deleteUrl" value="/user/forwarding/delete?id=${forwardingAttribute.id}" />
            <form:form id="form-${forwardingAttribute.id}" modelAttribute="forwardingAttribute" action="${deleteUrl}" method="delete">
                <form:hidden path="id" />
            </form:form>
        </c:if>
    </div>
    <div class="span8">
        <fmt:message key="Code" var="Code"/>
        <fmt:message key="Company" var="Company"/>
        <fmt:message key="County" var="County"/>
        <fmt:message key="City" var="City"/>
        <fmt:message key="Weight" var="Weight"/>

        <c:if test="${forwardingAttribute.id != null}">
            <spring:url var="uatfSave" value="/user/uatf/save/${forwardingAttribute.id}" />
            <form:form modelAttribute="uatfAttribute" action="${uatfSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:input path="code" cssClass="input-small" placeholder="${Code}" />
                <form:input path="company" cssClass="input-small" placeholder="${Company}" />
                <form:input path="county" cssClass="input-small" placeholder="${County}" />
                <form:input path="city" cssClass="input-small" placeholder="${City}" />
                <form:input path="loadWeightInTonne" cssClass="input-mini" placeholder="${Weight}" />
                <button class="btn" type="submit"><i class="icon-ok"></i></button>
                </form:form>

            <hr>

            <aripd:datatables datasource="/user/uatf/get/${forwardingAttribute.id}" id="uatfs" dataUrlDelete="/user/uatf/delete" actionColumn="5">
                <aripd:datatablescolumn label="Code" field="code"/>
                <aripd:datatablescolumn label="Company" field="company"/>
                <aripd:datatablescolumn label="County" field="county"/>
                <aripd:datatablescolumn label="City" field="city"/>
                <aripd:datatablescolumn label="Weight" field="loadWeightInTonne"/>
                <aripd:datatablescolumn label="Action" field="id"/>
            </aripd:datatables>
        </c:if>
    </div>
</div>

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