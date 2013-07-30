<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Members" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Members" />
    <jsp:param name="property" value="member" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>


<spring:url var="save" value="/member/save" />
<form:form modelAttribute="memberAttribute" action="${save}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="active">Aktif mi?</form:label>
                    <form:checkbox path="active" />
                    <form:errors cssClass="text-error" path="active" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="roles"><spring:message code="Roles" /></form:label>
                    <form:select multiple="true" size="6" path="roles" items="${roles}" itemLabel="name" itemValue="id" />
                    <form:errors cssClass="text-error" path="roles" />
                </div>
                <c:forEach var="role" items="${memberAttribute.roles}">
                    <span class="label label-success">${role.name}</span>&nbsp;
                </c:forEach>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="email"><spring:message code="E-mail Address" /></form:label>
                    <form:input path="email" />
                    <form:errors cssClass="text-error" path="email" />
                </div>
                <div class="control-group">
                    <form:label path="username"><spring:message code="Username" /></form:label>
                    <form:input path="username" />
                    <form:errors cssClass="text-error" path="username" />
                </div>
                <div class="control-group">
                    <form:label path="password"><spring:message code="Password" /></form:label>
                    <form:password path="password" />
                    <form:errors cssClass="text-error" path="password" />
                </div>
            </fieldset>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="form-actions">
                <c:if test="${ !empty memberAttribute.id }">
                    <a class="btn btn-danger" href="javascript:$('#form-${memberAttribute.id}').submit();"><spring:message code="Delete" /></a>
                </c:if>
                <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </div>
    </div>
</form:form>

<c:if test="${ !empty memberAttribute.id }">
    <spring:url var="deleteUrl" value="/member/delete?id=${memberAttribute.id}" />
    <form:form id="form-${memberAttribute.id}" modelAttribute="memberAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<script type="text/javascript">
    $('[name=username], [name=email]')
            .attr('autocomplete', 'off')
            .on('keyup', function(ev) {

        ev.stopPropagation();
        ev.preventDefault();

        var self = $(this);
        var newval = self.val().replace(/\s/g, "").toLowerCase();
        self.val(newval);
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />