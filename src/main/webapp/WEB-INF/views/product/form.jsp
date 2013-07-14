<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Products" />
    <jsp:param name="property" value="product" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="productSave" value="/product/save" />
<form:form modelAttribute="productAttribute" action="${productSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <div class="control-group">
            <form:label path="productgroup"><spring:message code="Product Group" /></form:label>
            <form:select path="productgroup.id" multiple="false" items="${productgroups}" itemLabel="name" itemValue="id"/>
            <form:errors cssClass="text-error" path="productgroup" />
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
            <c:if test="${ !empty productAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${productAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </fieldset>
</form:form>

<c:if test="${ !empty productAttribute.id }">
    <spring:url var="deleteUrl" value="/product/delete?id=${productAttribute.id}" />
    <form:form id="form-${productAttribute.id}" modelAttribute="productAttribute" action="${deleteUrl}" method="delete">
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