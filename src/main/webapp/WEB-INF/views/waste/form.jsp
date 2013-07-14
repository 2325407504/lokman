<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Wastes" />
    <jsp:param name="property" value="waste" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="wasteSave" value="/waste/save" />
<form:form modelAttribute="wasteAttribute" action="${wasteSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="wastegroup"><spring:message code="Waste Group" /></form:label>
            <form:select path="wastegroup.id" multiple="false" items="${wastegroups}" itemLabel="name" itemValue="id"/>
            <form:errors cssClass="text-error" path="wastegroup" />
        </div>
        <div class="control-group">
            <form:label path="code"><spring:message code="Code" /></form:label>
            <form:input path="code" />
            <form:errors cssClass="text-error" path="code" />
        </div>
        <div class="control-group">
            <form:label path="name"><spring:message code="Name" /></form:label>
            <form:input path="name" />
            <form:errors cssClass="text-error" path="name" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty wasteAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${wasteAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
        </div>
    </fieldset>
</form:form>

<c:if test="${ !empty wasteAttribute.id }">
    <spring:url var="deleteUrl" value="/waste/delete?id=${wasteAttribute.id}" />
    <form:form id="form-${wasteAttribute.id}" modelAttribute="wasteAttribute" action="${deleteUrl}" method="delete">
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