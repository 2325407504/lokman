<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Customers" />
    <jsp:param name="property" value="customer" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="customerSave" value="/customer/save" />
<form:form modelAttribute="customerAttribute" action="${customerSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <form:hidden path="authorized.id" />
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
                <legend>Firma Cari Bilgileri</legend>
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
                <legend>Firma Yetkilisi Bilgileri</legend>
                <div class="control-group">
                    <form:label path="authorized.username"><spring:message code="Username" /></form:label>
                    <form:input path="authorized.username" />
                    <form:errors cssClass="text-error" path="authorized.username" />
                </div>       
                <div class="control-group">
                    <form:label path="authorized.password"><spring:message code="Password" /></form:label>
                    <form:input type="password" path="authorized.password" />
                    <form:errors cssClass="text-error" path="authorized.password" />
                </div>       
                <div class="control-group">
                    <form:label path="authorized.email"><spring:message code="E-mail Address" /></form:label>
                    <form:input path="authorized.email" />
                    <form:errors cssClass="text-error" path="authorized.email" />
                </div>       
                <div class="control-group">
                    <form:label path="authorized.employee.firstName"><spring:message code="FirstName" /></form:label>
                    <form:input path="authorized.employee.firstName" />
                    <form:errors cssClass="text-error" path="authorized.employee.firstName" />
                </div>       
                <div class="control-group">
                    <form:label path="authorized.employee.lastName"><spring:message code="LastName" /></form:label>
                    <form:input path="authorized.employee.lastName" />
                    <form:errors cssClass="text-error" path="authorized.employee.lastName" />
                </div>
                <div class="control-group">
                    <form:label path="authorized.employee.phonenumber"><spring:message code="Phone Number" /></form:label>
                    <form:input path="authorized.employee.phonenumber" />
                    <form:errors cssClass="text-error" path="authorized.employee.phonenumber" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <legend>Firma Diger Bilgileri</legend>
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