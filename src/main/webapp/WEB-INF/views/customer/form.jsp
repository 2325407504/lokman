<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="customerList" value="/customer/list" />
<spring:url var="customerEdit" value="/customer/edit/${customerAttribute.id}" />
<spring:url var="customerNew" value="/customer/new" />
<spring:url var="customerSave" value="/customer/save" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${customerList}"><spring:message code="Customers" /></a></li>
        <c:choose>
            <c:when test="${ !empty customerAttribute.id }">
            <li class="active"><a href="${customerEdit}"><spring:message code="Entry No" />: ${customerAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${customerNew}"><spring:message code="New Entry" /></a></li>
            </c:otherwise>
        </c:choose>
</ul>

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
                    <form:label path="authorized.client.firstName"><spring:message code="FirstName" /></form:label>
                    <form:input path="authorized.client.firstName" />
                    <form:errors cssClass="text-error" path="authorized.client.firstName" />
                </div>       
                <div class="control-group">
                    <form:label path="authorized.client.lastName"><spring:message code="LastName" /></form:label>
                    <form:input path="authorized.client.lastName" />
                    <form:errors cssClass="text-error" path="authorized.client.lastName" />
                </div>
                <div class="control-group">
                    <form:label path="authorized.client.phonenumber"><spring:message code="Phone Number" /></form:label>
                    <form:input path="authorized.client.phonenumber" />
                    <form:errors cssClass="text-error" path="authorized.client.phonenumber" />
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

<%@ include file="/WEB-INF/views/footer.jsp" %>