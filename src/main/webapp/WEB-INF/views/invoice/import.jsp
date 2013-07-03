<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Invoices" />
    <jsp:param name="property" value="invoice" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="import" />
</jsp:include>

<div class="bs-docs-example" data-content="<spring:message code="XLSX" /> - <spring:message code="Invoice" />">
    <spring:url var="invoiceImportXLSX" value="/invoice/import/xls" />
    <form:form modelAttribute="fileUploadBean" action="${invoiceImportXLSX}" method="post" enctype="multipart/form-data">
        <form:errors cssClass="text-error" path="file" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload" /></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example" />">
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="Account" /></th>
                    <th><spring:message code="Invoice" /></th>
                    <th><spring:message code="Driver" /></th>
                    <th><spring:message code="Plate" /></th>
                    <th><spring:message code="Starting Time" /></th>
                    <th><spring:message code="Ending Time" /></th>
                    <th><spring:message code="Ending Point" /></th>
                    <th><spring:message code="Weight" /></th>
                    <th><spring:message code="Shipping Cost" /></th>
                    <th><spring:message code="Subcontractor" /></th>
                    <th><spring:message code="Quota" /></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>user1</td>
                    <td>invoiceno1</td>
                    <td>driver1</td>
                    <td>plate1</td>
                    <td>11.02.2013 21:03</td>
                    <td>11.02.2013 22:22</td>
                    <td>ending point1</td>
                    <td>5</td>
                    <td>110,2</td>
                    <td>UID1</td>
                    <td>UID1</td>
                </tr>
                <tr>
                    <td>user1</td>
                    <td>invoiceno1</td>
                    <td>driver1</td>
                    <td>plate1</td>
                    <td>11.02.2013 21:03</td>
                    <td>11.02.2013 22:22</td>
                    <td>ending point1</td>
                    <td>5</td>
                    <td>24,54</td>
                    <td>UID1</td>
                    <td>UID1</td>
                </tr>
                <tr>
                    <td>user2</td>
                    <td>invoiceno2</td>
                    <td>driver2</td>
                    <td>plate2</td>
                    <td>09.02.2013 12:12</td>
                    <td>10.02.2013 14:13</td>
                    <td>ending point2</td>
                    <td>7</td>
                    <td>98,7</td>
                    <td>UID2</td>
                    <td>UID2</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="bs-docs-example" data-content="<spring:message code="XLSX" /> - <spring:message code="Waybill" />">
    <spring:url var="waybillImportXLSX" value="/waybill/import/xls" />
    <form:form modelAttribute="fileUploadBean" action="${waybillImportXLSX}" method="post" enctype="multipart/form-data">
        <form:errors cssClass="text-error" path="file" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload" /></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example" />">
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="Invoice" /></th>
                    <th><spring:message code="Waybill" /></th>
                    <th><spring:message code="Company" /></th>
                    <th><spring:message code="County" /></th>
                    <th><spring:message code="City" /></th>
                    <th><spring:message code="Weight" /></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>invoiceno1</td>
                    <td>waybill11</td>
                    <td>company11</td>
                    <td>county11</td>
                    <td>city11</td>
                    <td>7</td>
                </tr>
                <tr>
                    <td>invoiceno1</td>
                    <td>waybill12</td>
                    <td>company12</td>
                    <td>county12</td>
                    <td>city12</td>
                    <td>11</td>
                </tr>
                <tr>
                    <td>invoiceno2</td>
                    <td>waybill21</td>
                    <td>company21</td>
                    <td>county21</td>
                    <td>city21</td>
                    <td>12</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />