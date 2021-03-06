<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Productions" />
    <jsp:param name="property" value="userproduction" />
    <jsp:param name="new" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="save" value="/userproduction/save" />
<form:form modelAttribute="userproductionAttribute" action="${save}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="shiftdate"><spring:message code="Shift" /></form:label>
                    <form:input type="text" path="shiftdate" />
                    <form:errors cssClass="text-error" path="shiftdate" />
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
                                <input type="hidden" class="input-mini" name="machinetimes[${status.index}].id" value="${userproductionAttribute.machinetimes[status.index].id}"/>
                                <input type="hidden" class="input-mini" name="machinetimes[${status.index}].machine.id" value="${machine.id}"/>
                                <input class="input-mini" name="machinetimes[${status.index}].val" value="${userproductionAttribute.machinetimes[status.index].val}"/>
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
                                <input type="hidden" class="input-mini" name="compensations[${status.index}].id" value="${userproductionAttribute.compensations[status.index].id}"/>
                                <input type="hidden" class="input-mini" name="compensations[${status.index}].electricmeter.id" value="${electricmeter.id}"/>
                                <input class="input-mini" name="compensations[${status.index}].val" value="${userproductionAttribute.compensations[status.index].val}"/>
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
                <c:if test="${ !empty userproductionAttribute.id }">
                    <a class="btn btn-danger" href="javascript:$('#form-${userproductionAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                </c:if>
                <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </div>
    </div>
</form:form>


<c:if test="${ !empty userproductionAttribute.id }">
    <spring:url var="deleteUrl" value="/userproduction/delete">
        <spring:param name="id" value="${userproductionAttribute.id}" />
    </spring:url>
    <form:form id="form-${userproductionAttribute.id}" modelAttribute="userproductionAttribute" action="${deleteUrl}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<div class="row-fluid">
    <div class="span12">
        <spring:message code="Weight" var="weight"/>

        <c:if test="${userproductionAttribute.id != null}">
            <spring:url var="bigbagSave" value="/bigbag/save/{id}">
                <spring:param name="id" value="${userproductionAttribute.id}" />
            </spring:url>
            <form:form modelAttribute="bigbagAttribute" action="${bigbagSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:select path="product.id" multiple="false">
                    <form:option value="-" label="---�r�n Se�iniz"/>
                    <form:options items="${products}" itemLabel="name" itemValue="id"/>
                </form:select>
                <form:input path="weight" cssClass="input-small" placeholder="${weight}" />
                <button class="btn btn-primary" type="submit">
                    <i class="icon-ok icon-white"></i>
                </button>
            </form:form>

            <hr>

            <aripd:datatables datasource="/bigbag/get/${userproductionAttribute.id}" id="bigbags" caption="Production Amounts" dataUrlDelete="/bigbag/delete" actionColumn="2">
                <aripd:datatablescolumn label="Product" field="product.name"/>
                <aripd:datatablescolumn label="Weight" field="weight"/>
                <aripd:datatablescolumn label="Action" field="id"/>
            </aripd:datatables>
        </c:if>
    </div>
</div>

<script>
    $(function() {
        $("[name=shiftdate]").datetimepicker({
            showButtonPanel: false,
            maxDate: new Date(),
            showMinute: false,
            hourGrid: 8,
            stepHour: 8,
            hourText: "Vardiya",
            onClose: function(e) {
                console.log(e);
            }
        });
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />