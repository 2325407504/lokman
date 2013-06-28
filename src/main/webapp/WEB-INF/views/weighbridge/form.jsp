<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="weighbridgeList" value="/weighbridge/list" />
<spring:url var="weighbridgeShow" value="/weighbridge/show/${weighbridgeAttribute.id}" />
<spring:url var="weighbridgeEdit" value="/weighbridge/edit/${weighbridgeAttribute.id}" />
<spring:url var="weighbridgeNew" value="/weighbridge/new" />
<spring:url var="weighbridgeImport" value="/weighbridge/import/xls" />
<spring:url var="weighbridgeExport" value="/weighbridge/export/xls" />
<spring:url var="weighbridgeSave" value="/weighbridge/save" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${weighbridgeList}"><spring:message code="Weighbridges" /></a></li>
        <c:choose>
            <c:when test="${ !empty weighbridgeAttribute.id }">
            <li class="active"><a href="${weighbridgeEdit}"><spring:message code="Entry No" />: ${weighbridgeAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${weighbridgeNew}"><spring:message code="New Entry" /></a></li>
            </c:otherwise>
        </c:choose>
    <li class=""><a href="${weighbridgeImport}"><spring:message code="Import" /></a></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li><a href="${weighbridgeExport}"><spring:message code="Weighbridges" /></a></li>
        </ul>
    </li>
</ul>

<form:form modelAttribute="weighbridgeAttribute" action="${weighbridgeSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
                <legend>Kantar Fisi</legend>
                <div class="control-group">
                    <form:checkbox path="submitted" /> <spring:message code="Submitted by user" />
                    <form:errors cssClass="text-error" path="submitted" />
                </div>
                <div class="control-group">
                    <form:label path="account"><spring:message code="Account" /></form:label>
                    <form:select multiple="false" path="account.id" items="${accounts}" itemLabel="client.fullname" itemValue="id" />
                    <form:errors cssClass="text-error" path="account" />
                </div>
                <div class="control-group">
                    <form:label path="clerk"><spring:message code="Clerk" text="Clerk" /></form:label>
                    <form:input path="clerk" />
                    <form:errors cssClass="text-error" path="clerk" />
                </div>
                <div class="control-group">
                    <form:label path="plate"><spring:message code="Plate" /></form:label>
                    <form:input path="plate" />
                    <form:errors cssClass="text-error" path="plate" />
                </div>
                <div class="control-group">
                    <form:label path="driver"><spring:message code="Driver" /></form:label>
                    <form:input path="driver" />
                    <form:errors cssClass="text-error" path="driver" />
                </div>
                <div class="control-group">
                    <form:label path="locationFrom"><spring:message code="Location From" text="Location From" /></form:label>
                    <form:input path="locationFrom" />
                    <form:errors cssClass="text-error" path="locationFrom" />
                </div>
                <div class="control-group">
                    <form:label path="locationTo"><spring:message code="Location To" text="Location To" /></form:label>
                    <form:input path="locationTo" />
                    <form:errors cssClass="text-error" path="locationTo" />
                </div>
                <div class="control-group">
                    <form:label path="checkin"><spring:message code="Check-in" text="Check-in" /></form:label>
                    <form:input id="checkin" type="datetime" path="checkin" />
                    <form:errors cssClass="text-error" path="checkin" />
                </div>
                <div class="control-group">
                    <form:label path="checkout"><spring:message code="Check-out" text="Check-out" /></form:label>
                    <form:input id="checkout" type="datetime" path="checkout" />
                    <form:errors cssClass="text-error" path="checkout" />
                </div>
                <div class="control-group">
                    <form:label path="goodtype"><spring:message code="Good Type" text="Good Type" /></form:label>
                    <form:input path="goodtype" />
                    <form:errors cssClass="text-error" path="goodtype" />
                </div>
                <div class="control-group">
                    <form:label path="customer"><spring:message code="Customer" /></form:label>
                    <form:input path="customer" />
                    <form:errors cssClass="text-error" path="customer" />
                </div>
                <div class="control-group">
                    <form:label path="firstWeighing"><spring:message code="First Weighing" /></form:label>
                    <form:input path="firstWeighing" />
                    <form:errors cssClass="text-error" path="firstWeighing" />
                </div>
                <div class="control-group">
                    <form:label path="lastWeighing"><spring:message code="Last Weighing" /></form:label>
                    <form:input path="lastWeighing" />
                    <form:errors cssClass="text-error" path="lastWeighing" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <table class="table">
                <caption><spring:message code="Extrications" /></caption>
                <thead>
                    <tr>
                        <th><spring:message code="Waste" /></th>
                        <th><spring:message code="Value" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${wastes}" var="waste" varStatus="status">
                        <tr>
                            <td>${waste.name}</td>
                            <td>
                                <input type="hidden" class="input-mini" name="extrications[${status.index}].id" value="${weighbridgeAttribute.extrications[status.index].id}"/>
                                <input type="hidden" class="input-mini" name="extrications[${status.index}].waste.id" value="${waste.id}"/>
                                <input class="input-mini" name="extrications[${status.index}].val" value="${weighbridgeAttribute.extrications[status.index].val}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="form-actions">
                <c:if test="${ !empty weighbridgeAttribute.id }">
                    <a class="btn btn-danger" href="javascript:$('#form-${weighbridgeAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                </c:if>
                <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </div>
    </div>
</form:form>

<c:if test="${ !empty weighbridgeAttribute.id }">
    <spring:url var="deleteUrl" value="/weighbridge/delete?id=${weighbridgeAttribute.id}" />
    <form:form id="form-${weighbridgeAttribute.id}" modelAttribute="weighbridgeAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<script>
    $(function() {
        $("#checkin, #checkout").datetimepicker({
            maxDate: new Date()
        });
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />