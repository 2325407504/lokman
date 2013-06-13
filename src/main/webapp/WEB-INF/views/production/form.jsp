<%@ include file="/WEB-INF/views/header.jsp" %>

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
    <li class=""><a href="${productionList}"><spring:message code="Productions"></spring:message></a></li>
        <c:choose>
            <c:when test="${ !empty productionAttribute.id }">
            <li class="active"><a href="${productionEdit}"><spring:message code="Entry No"></spring:message>: ${productionAttribute.id}</a></li>
            </c:when>
            <c:otherwise>
            <li class="active"><a href="${productionNew}"><spring:message code="New Entry"></spring:message></a></li>
            </c:otherwise>
        </c:choose>
    <li class=""><a href="${productionImport}"><spring:message code="Import"></spring:message></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export"></spring:message>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${productionExport}"><spring:message code="Waybill"></spring:message></a></li>
            <li><a href="${bigbagExport}"><spring:message code="UATF"></spring:message></a></li>
            </ul>
        </li>
    </ul>

    <div class="row-fluid">
        <div class="span4">
        <form:form modelAttribute="productionAttribute" action="${productionSave}" method="post">
            <form:errors path="*" cssClass="error-block" element="div" />
            <form:hidden path="id" />
            <fieldset>
                <div class="form-row">
                    <form:checkbox path="submitted" /> <spring:message code="Submitted by user"></spring:message>
                    <form:errors cssClass="error-field" path="submitted" />
                </div>
                <div class="form-row">
                    <form:label path="account"><spring:message code="Account"></spring:message></form:label>
                    <form:select multiple="false" path="account.id" items="${accounts}" itemLabel="customer.fullname" itemValue="id" />
                    <form:errors cssClass="error-field" path="account" />
                </div>
                <div class="form-row">
                    <form:label path="shiftdate"><spring:message code="Date"></spring:message></form:label>
                    <form:input type="text" path="shiftdate" />
                    <form:errors cssClass="error-field" path="shiftdate" />
                </div>
                <div class="form-row">
                    <form:label path="shift"><spring:message code="Shift"></spring:message></form:label>
                    <form:select path="shift.id" multiple="false" items="${shifts}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="error-field" path="shift" />
                </div>
                <div class="form-row">
                    <form:label path="feed"><spring:message code="Feed" text="Feed"></spring:message></form:label>
                    <form:input path="feed" />
                    <form:errors cssClass="error-field" path="feed" />
                </div>
                <div class="form-row">
                    <form:label path="remark"><spring:message code="Remark"></spring:message></form:label>
                    <form:textarea path="remark" />
                    <form:errors cssClass="error-field" path="remark" />
                </div>
                <div class="form-actions">
                    <c:if test="${ !empty productionAttribute.id }">
                        <a class="btn btn-danger" href="javascript:$('#form-${productionAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete"></spring:message></a>
                    </c:if>
                    <button class="btn btn-primary" type="submit"><spring:message code="Save"></spring:message></button>
                    </div>
                </fieldset>
        </form:form>

        <c:if test="${ !empty productionAttribute.id }">
            <spring:url var="deleteUrl" value="/production/delete?id=${productionAttribute.id}" />
            <form:form id="form-${productionAttribute.id}" modelAttribute="productionAttribute" action="${deleteUrl}" method="delete">
                <form:hidden path="id" />
            </form:form>
        </c:if>
    </div>
    <div class="span8">
        <fmt:message key="Product" var="Product"/>
        <fmt:message key="Weight" var="Weight"/>

        <c:if test="${productionAttribute.id != null}">
            <form:form modelAttribute="bigbagAttribute" action="${bigbagSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="error-block" element="div" />
                <form:select path="product.id" multiple="false">
                    <form:option value="-" label="---Ürün Seçiniz"/>
                    <form:options items="${products}" itemLabel="name" itemValue="id"/>
                </form:select>
                <form:input path="weight" cssClass="input-small" placeholder="${Weight}" />
                <button class="btn" type="submit"><i class="icon-ok"></i></button>
                </form:form>

            <hr>

            <aripd:datatables datasource="/bigbag/get/${productionAttribute.id}" id="bigbags" dataUrlDelete="/bigbag/delete" actionColumn="2">
                <aripd:column label="Product" field="product.name"/>
                <aripd:column label="Weight" field="weight"/>
                <aripd:column label="Action" field="id"/>
            </aripd:datatables>
        </c:if>
    </div>
</div>

<script>
    $(function() {
        $("#shiftdate").datetimepicker();
    });
</script>

<script type="text/javascript">
<!--
    var startDateTextBox = $('#startingTime');
    var endDateTextBox = $('#endingTime');

    startDateTextBox.datetimepicker({
        onClose: function(dateText, inst) {
            if (endDateTextBox.val() != '') {
                var testStartDate = startDateTextBox.datetimepicker('getDate');
                var testEndDate = endDateTextBox.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    endDateTextBox.datetimepicker('setDate', testStartDate);
            }
            else {
                endDateTextBox.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            endDateTextBox.datetimepicker('option', 'minDate', startDateTextBox.datetimepicker('getDate'));
        }
    });
    endDateTextBox.datetimepicker({
        onClose: function(dateText, inst) {
            if (startDateTextBox.val() != '') {
                var testStartDate = startDateTextBox.datetimepicker('getDate');
                var testEndDate = endDateTextBox.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    startDateTextBox.datetimepicker('setDate', testEndDate);
            }
            else {
                startDateTextBox.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            startDateTextBox.datetimepicker('option', 'maxDate', endDateTextBox.datetimepicker('getDate'));
        }
    });
//-->
</script>

<%@ include file="/WEB-INF/views/footer.jsp" %>