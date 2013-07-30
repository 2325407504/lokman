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
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="waybillSave" value="/waybill/save" />
<form:form modelAttribute="waybillAttribute" action="${waybillSave}" method="post">
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:hidden path="id" />
                <div class="control-group">
                    <form:checkbox path="submitted" /> <spring:message code="message.record.submitted" />
                    <form:errors cssClass="text-error" path="submitted" />
                </div>
                <div class="control-group">
                    <form:label path="member"><spring:message code="Member" /></form:label>
                    <form:select multiple="false" path="member.id" items="${members}" itemLabel="employee.fullname" itemValue="id" />
                    <form:errors cssClass="text-error" path="member" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <legend><spring:message code="Waybill" /></legend>
                <div class="control-group">
                    <form:label path="documentDate"><spring:message code="Document Date" /></form:label>
                    <form:input type="datetime" path="documentDate" />
                    <form:errors cssClass="text-error" path="documentDate" />
                </div>
                <div class="control-group">
                    <form:label path="documentNo"><spring:message code="Document No" /></form:label>
                    <form:input path="documentNo" />
                    <form:errors cssClass="text-error" path="documentNo" />
                </div>
                <div class="control-group">
                    <form:label path="company"><spring:message code="Company" /></form:label>
                    <form:input path="company" />
                    <form:errors cssClass="text-error" path="company" />
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
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
            </fieldset>
        </div>
    </div>
    <div class="form-actions">
        <c:if test="${ !empty waybillAttribute.id }">
            <a class="btn btn-danger" href="javascript:$('#form-${waybillAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
        </c:if>
        <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
    </div>
</form:form>

<c:if test="${ !empty waybillAttribute.id }">
    <spring:url var="deleteUrl" value="/waybill/delete?id=${waybillAttribute.id}" />
    <form:form id="form-${waybillAttribute.id}" modelAttribute="waybillAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<c:if test="${waybillAttribute.id != null}">
    <div class="row-fluid">
        <div class="span12">
            <spring:message code="Weight" var="weight" />
            <spring:message code="Remark" var="remark" />

            <spring:url var="outgoingSave" value="/outgoing/save/${waybillAttribute.id}" />
            <form:form modelAttribute="outgoingAttribute" action="${outgoingSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:select multiple="false" path="product.id" items="${products}" itemLabel="name" itemValue="id" />
                <form:input path="weight" cssClass="input-mini" placeholder="${weight}" />
                <form:input path="remark" cssClass="input-small" placeholder="${remark}" />
                <button class="btn" type="submit"><i class="icon-ok"></i></button>
                </form:form>

            <hr>

            <aripd:datatables datasource="/outgoing/get/${waybillAttribute.id}" id="outgoings" dataUrlDelete="/outgoing/delete" actionColumn="3" caption="Outgoings">
                <aripd:datatablescolumn label="Product" field="product.name"/>
                <aripd:datatablescolumn label="Weight" field="weight"/>
                <aripd:datatablescolumn label="Remark" field="remark"/>
                <aripd:datatablescolumn label="Action" field="id"/>
            </aripd:datatables>
        </div>
    </div>
</c:if>

<script>
    $(function() {
        $("[name=documentDate]").datepicker({
            maxDate: new Date()
        });
    });

    $('[name=plate]')
            .attr('autocomplete', 'off')
            .on('keyup', function(ev) {

        ev.stopPropagation();
        ev.preventDefault();

        var self = $(this);
        var newval = self.val().replace(/\s/g, "").toUpperCase();
        self.val(newval);
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />