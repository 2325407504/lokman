<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Customers" />
    <jsp:param name="property" value="customer" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="save" value="/customer/save" />
<form:form modelAttribute="customerAttribute" action="${save}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
                <legend><spring:message code="Customer" /></legend>
                <div class="control-group">
                    <form:label path="taxNo"><spring:message code="Tax No" /></form:label>
                    <form:input path="taxNo" />
                    <form:errors cssClass="text-error" path="taxNo" />
                </div>
                <div class="control-group">
                    <form:label path="taxOffice"><spring:message code="Tax Office" /></form:label>
                    <form:input path="taxOffice" />
                    <form:errors cssClass="text-error" path="taxOffice" />
                </div>
                <div class="control-group">
                    <form:label path="name"><spring:message code="Company" /></form:label>
                    <form:input path="name" />
                    <form:errors cssClass="text-error" path="name" />
                </div>
                <div class="control-group">
                    <form:label path="address"><spring:message code="Postal Address" /></form:label>
                    <form:input path="address" />
                    <form:errors cssClass="text-error" path="address" />
                </div>
                <div class="control-group">
                    <form:label path="phonenumber"><spring:message code="Phone Number" /></form:label>
                    <form:input path="phonenumber" />
                    <form:errors cssClass="text-error" path="phonenumber" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <legend><spring:message code="Additional Information" /></legend>
                <div class="control-group">
                    <form:label path="active">Aktif mi?</form:label>
                    <form:checkbox path="active" />
                    <form:errors cssClass="text-error" path="active" />
                </div>
                <div class="control-group">
                    <form:label path="container">Konteyner mevcut mu?</form:label>
                    <form:checkbox path="container" />
                    <form:errors cssClass="text-error" path="container" />
                </div>
                <div class="control-group">
                    <form:label path="shippingcost"><spring:message code="Shipping Cost" /></form:label>
                    <form:input path="shippingcost" />
                    <form:errors cssClass="text-error" path="shippingcost" />
                </div>
                <div class="control-group">
                    <form:label path="disposalcost"><spring:message code="Disposal Cost" /></form:label>
                    <form:input path="disposalcost" />
                    <form:errors cssClass="text-error" path="disposalcost" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <legend><spring:message code="Member" /></legend>
                <div class="control-group">
                    <form:label path="member"><spring:message code="Member" /></form:label>
                    <form:select path="member.id" multiple="false">
                        <form:option value="" label="-----" />
                        <form:options items="${members}" itemLabel="username" itemValue="id" />
                    </form:select>
                    <form:errors cssClass="text-error" path="member" />
                </div>
            </fieldset>
        </div>
    </div>
    <div class="form-actions">
        <c:if test="${ !empty customerAttribute.id }">
            <a class="btn btn-danger" href="javascript:$('#form-${customerAttribute.id}').submit();"><spring:message code="Delete" /></a>
        </c:if>
        <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
    </div>
</form:form>

<c:if test="${ !empty customerAttribute.id }">
    <spring:url var="deleteUrl" value="/customer/delete?id=${customerAttribute.id}" />
    <form:form id="form-${customerAttribute.id}" modelAttribute="customerAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<jsp:include page="/WEB-INF/views/footer.jsp" />