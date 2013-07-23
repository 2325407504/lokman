<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Drivers" />
    <jsp:param name="property" value="driver" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="save" value="/driver/save" />
<form:form modelAttribute="driverAttribute" action="${save}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="region"><spring:message code="Region" /></form:label>
            <form:select multiple="false" path="region.id" items="${regions}" itemLabel="name" itemValue="id" />
            <form:errors cssClass="text-error" path="region" />
        </div>
        <div class="control-group">
            <form:label path="code"><spring:message code="Code" /></form:label>
            <form:input path="code" />
            <form:errors cssClass="text-error" path="code" />
        </div>
        <div class="control-group">
            <form:label path="name"><spring:message code="Fullname" /></form:label>
            <form:input path="name" />
            <form:errors cssClass="text-error" path="name" />
        </div>
        <div class="control-group">
            <form:label path="phonenumber"><spring:message code="Phone Number" /></form:label>
            <form:input path="phonenumber" />
            <form:errors cssClass="text-error" path="phonenumber" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty driverAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${driverAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
        </div>
    </fieldset>
</form:form>

<c:if test="${ !empty driverAttribute.id }">
    <spring:url var="delete" value="/driver/delete">
        <spring:param name="id" value="${driverAttribute.id}" />
    </spring:url>
    <form:form id="form-${driverAttribute.id}" modelAttribute="driverAttribute" action="${delete}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<script type="text/javascript">
    $('[name=code]')
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