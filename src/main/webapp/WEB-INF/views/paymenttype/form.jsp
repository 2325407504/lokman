<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Paymenttypes" />
    <jsp:param name="property" value="paymenttype" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="paymenttypeSave" value="/paymenttype/save" />
<form:form modelAttribute="paymenttypeAttribute" action="${paymenttypeSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
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
            <c:if test="${ !empty paymenttypeAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${paymenttypeAttribute.id}').submit();"><spring:message code="Delete" /></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
        </div>
    </fieldset>
</form:form>

<c:if test="${ !empty paymenttypeAttribute.id }">
    <spring:url var="deleteUrl" value="/paymenttype/delete?id=${paymenttypeAttribute.id}" />
    <form:form id="form-${paymenttypeAttribute.id}" modelAttribute="paymenttypeAttribute" action="${deleteUrl}" method="delete">
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