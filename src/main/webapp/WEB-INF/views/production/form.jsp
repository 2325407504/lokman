<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="productionList" value="/production/list" />
<spring:url var="productionShow" value="/production/show/${productionAttribute.id}" />
<spring:url var="productionEdit" value="/production/edit/${productionAttribute.id}" />
<spring:url var="productionNew" value="/production/new" />
<spring:url var="productionImport" value="/production/import/xls" />
<spring:url var="productionExport" value="/production/export/xls" />
<spring:url var="bigbagExport" value="/bigbag/export/xls" />
<spring:url var="productionSave" value="/production/save" />
<spring:url var="bigbagSave" value="/bigbag/save/${productionAttribute.id}" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="">
        <a href="${productionList}"><spring:message code="Productions" /></a>
    </li>
    <c:choose>
        <c:when test="${ !empty productionAttribute.id }">
            <li class="active">
                <a href="${productionEdit}"><spring:message code="Entry No" />: ${productionAttribute.id}</a>
            </li>
        </c:when>
        <c:otherwise>
            <li class="active">
                <a href="${productionNew}"><spring:message code="New Entry" /></a>
            </li>
        </c:otherwise>
    </c:choose>
    <li class=""><a href="${productionImport}"><spring:message code="Import" /></a></li>
    <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
            <li><a href="${productionExport}"><spring:message code="Production" /></a></li>
            <li><a href="${bigbagExport}"><spring:message code="BigBag" /></a></li>
        </ul>
    </li>
</ul>

<form:form id="production" modelAttribute="productionAttribute" action="${productionSave}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
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
                    <form:label path="shiftdate"><spring:message code="Date" /></form:label>
                    <form:input id="shiftdate" type="text" path="shiftdate" />
                    <form:errors cssClass="text-error" path="shiftdate" />
                </div>
                <div class="control-group">
                    <form:label path="shift"><spring:message code="Shift" /></form:label>
                    <form:select id="shiftId" path="shift.id" multiple="false" items="${shifts}" itemLabel="name" itemValue="id" />
                    <form:errors cssClass="text-error" path="shift" />
                </div>
                <div class="control-group">
                    <form:label path="feed"><spring:message code="Feed" text="Feed" /></form:label>
                    <form:input path="feed" />
                    <form:errors cssClass="text-error" path="feed" />
                </div>
                <div class="control-group">
                    <form:label path="remark"><spring:message code="Remark" /></form:label>
                    <form:textarea path="remark" />
                    <form:errors cssClass="text-error" path="remark" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <table class="table">
                <caption><spring:message code="Machine Times" /></caption>
                <thead>
                    <tr>
                        <th><spring:message code="Machine" /></th>
                        <th><spring:message code="Value" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${machines}" var="machine" varStatus="status">
                        <tr>
                            <td>${machine.name}</td>
                            <td>
                                <input type="hidden" class="input-mini" name="machinetimes[${status.index}].id" value="${productionAttribute.machinetimes[status.index].id}"/>
                                <input type="hidden" class="input-mini" name="machinetimes[${status.index}].machine.id" value="${machine.id}"/>
                                <input class="input-mini" name="machinetimes[${status.index}].val" value="${productionAttribute.machinetimes[status.index].val}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="span4">
            <table class="table">
                <caption><spring:message code="Compensations" /></caption>
                <thead>
                    <tr>
                        <th><spring:message code="Electricmeter" /></th>
                        <th><spring:message code="Value" /></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${electricmeters}" var="electricmeter" varStatus="status">
                        <tr>
                            <td>${electricmeter.name}</td>
                            <td>
                                <input type="hidden" class="input-mini" name="compensations[${status.index}].id" value="${productionAttribute.compensations[status.index].id}"/>
                                <input type="hidden" class="input-mini" name="compensations[${status.index}].electricmeter.id" value="${electricmeter.id}"/>
                                <input class="input-mini" name="compensations[${status.index}].val" value="${productionAttribute.compensations[status.index].val}"/>
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
                <c:if test="${ !empty productionAttribute.id }">
                    <a class="btn btn-danger" href="javascript:$('#form-${productionAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                </c:if>
                <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </div>
    </div>
</form:form>


<c:if test="${ !empty productionAttribute.id }">
    <spring:url var="deleteUrl" value="/production/delete?id=${productionAttribute.id}" />
    <form:form id="form-${productionAttribute.id}" modelAttribute="productionAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<div class="row-fluid">
    <div class="span12">
        <fmt:message key="Product" var="Product"/>
        <fmt:message key="Weight" var="Weight"/>

        <c:if test="${productionAttribute.id != null}">
            <form:form modelAttribute="bigbagAttribute" action="${bigbagSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:select path="product.id" multiple="false">
                    <form:option value="-" label="---Ürün Seçiniz"/>
                    <form:options items="${products}" itemLabel="name" itemValue="id"/>
                </form:select>
                <form:input path="weight" cssClass="input-small" placeholder="${Weight}" />
                <button class="btn btn-primary" type="submit">
                    <i class="icon-ok icon-white"></i>
                </button>
            </form:form>

            <hr>

            <aripd:datatables datasource="/bigbag/get/${productionAttribute.id}" id="bigbags" caption="Production Amounts" dataUrlDelete="/bigbag/delete" actionColumn="2">
                <aripd:column label="Product" field="product.name"/>
                <aripd:column label="Weight" field="weight"/>
                <aripd:column label="Action" field="id"/>
            </aripd:datatables>
        </c:if>
    </div>
</div>

<script>
    $(function() {
        $("#shiftdate").datetimepicker({
            maxDate: new Date()
        });
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />