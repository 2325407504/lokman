<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="customerList" value="/customer/list" />
<spring:url var="customerEdit" value="/customer/edit/${customerAttribute.id}" />
<spring:url var="customerNew" value="/customer/new" />
<spring:url var="customerSave" value="/customer/save" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${customerList}"><spring:message code="Customers"></spring:message></a></li>
        <c:choose>
            <c:when test="${ !empty customerAttribute.id }">
            <li class="active"><a href="${customerEdit}"><spring:message code="Entry No"></spring:message>: ${customerAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${customerNew}"><spring:message code="New Entry"></spring:message></a></li>
            </c:otherwise>
        </c:choose>
</ul>

<form:form modelAttribute="customerAttribute" action="${customerSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <fieldset>
        <legend>Firma Cari Bilgileri</legend>
        <div class="control-group">
            <form:label path="active">Aktif mi?</form:label>
            <form:checkbox path="active" />
            <form:errors cssClass="text-error" path="active" />
        </div>
        <div class="control-group">
            <form:label path="taxNo"><spring:message code="Tax No"></spring:message></form:label>
            <form:input path="taxNo" />
            <form:errors cssClass="text-error" path="taxNo" />
        </div>
        <div class="control-group">
            <form:label path="taxOffice"><spring:message code="Tax Office"></spring:message></form:label>
            <form:input path="taxOffice" />
            <form:errors cssClass="text-error" path="taxOffice" />
        </div>
        <div class="control-group">
            <form:label path="name"><spring:message code="Company"></spring:message></form:label>
            <form:input path="name" />
            <form:errors cssClass="text-error" path="name" />
        </div>
        <div class="control-group">
            <form:label path="address"><spring:message code="Postal Address"></spring:message></form:label>
            <form:input path="address" />
            <form:errors cssClass="text-error" path="address" />
        </div>
        <div class="control-group">
            <form:label path="phonenumber"><spring:message code="Phone Number"></spring:message></form:label>
            <form:input path="phonenumber" />
            <form:errors cssClass="text-error" path="phonenumber" />
        </div>
    </fieldset>
    <fieldset>
        <legend>Firma Yetkilisi Bilgileri</legend>
        <div class="control-group">
            <form:label path="authorized.username"><spring:message code="Username"></spring:message></form:label>
            <form:input path="authorized.username" />
            <form:errors cssClass="text-error" path="authorized.username" />
        </div>       
        <div class="control-group">
            <form:label path="authorized.password"><spring:message code="Password"></spring:message></form:label>
            <form:input path="authorized.password" />
            <form:errors cssClass="text-error" path="authorized.password" />
        </div>       
        <div class="control-group">
            <form:label path="authorized.email"><spring:message code="E-mail Address"></spring:message></form:label>
            <form:input path="authorized.email" />
            <form:errors cssClass="text-error" path="authorized.email" />
        </div>       
        <div class="control-group">
            <form:label path="authorized.client.firstName"><spring:message code="FirstName"></spring:message></form:label>
            <form:input path="authorized.client.firstName" />
            <form:errors cssClass="text-error" path="authorized.client.firstName" />
        </div>       
        <div class="control-group">
            <form:label path="authorized.client.lastName"><spring:message code="LastName"></spring:message></form:label>
            <form:input path="authorized.client.lastName" />
            <form:errors cssClass="text-error" path="authorized.client.lastName" />
        </div>
        <div class="control-group">
            <form:label path="authorized.client.phonenumber"><spring:message code="Phone Number"></spring:message></form:label>
            <form:input path="authorized.client.phonenumber" />
            <form:errors cssClass="text-error" path="authorized.client.phonenumber" />
        </div>
    </fieldset>
    <fieldset>
        <legend>Firma Diger Bilgileri</legend>
        <div class="control-group">
            <form:label path="container">Konteyner mevcut mu?</form:label>
            <form:checkbox path="container" />
            <form:errors cssClass="text-error" path="container" />
        </div>
        <div class="form-actions">
            <c:if test="${ !empty customerAttribute.id }">
                <a class="btn btn-danger" href="javascript:$('#form-${customerAttribute.id}').submit();"><spring:message code="Delete"></spring:message></a>
            </c:if>
            <button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
            </div>
        </fieldset>
</form:form>

<c:if test="${ !empty customerAttribute.id }">
    <spring:url var="deleteUrl" value="/customer/delete?id=${customerAttribute.id}" />
    <form:form id="form-${customerAttribute.id}" modelAttribute="customerAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<%@ include file="/WEB-INF/views/footer.jsp" %>