<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="waybillList" value="/waybill/list" />
<spring:url var="waybillNew" value="/waybill/new" />
<spring:url var="waybillImportXLSX" value="/waybill/import/xls" />
<spring:url var="outgoingImportXLSX" value="/outgoing/import/xls" />
<spring:url var="waybillExport" value="/waybill/export/xls" />
<spring:url var="outgoingExport" value="/outgoing/export/xls" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${waybillList}"><spring:message code="Waybills"></spring:message></a></li>
    <li class=""><a href="${waybillNew}"><spring:message code="New Entry"></spring:message></a></li>
    <li class="active"><a href="${waybillImportXLSX}"><spring:message code="Import"></spring:message></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export"></spring:message>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${waybillExport}"><spring:message code="Waybill"></spring:message></a></li>
            <li><a href="${outgoingExport}"><spring:message code="Outgoing"></spring:message></a></li>
            </ul>
        </li>
    </ul>

    <div class="bs-docs-example" data-content="<spring:message code="XLSX"></spring:message> - <spring:message code="Waybill"></spring:message>">
    <form:form modelAttribute="fileUploadBean" action="${waybillImportXLSX}" method="post" enctype="multipart/form-data">
        <form:errors cssClass="text-error" path="file" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload"></spring:message></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example"></spring:message>">
            <table class="table">
                <thead>
                    <tr>
                        <th><spring:message code="Account"></spring:message></th>
                    <th><spring:message code="Waybill"></spring:message></th>
                    <th><spring:message code="Driver"></spring:message></th>
                    <th><spring:message code="Plate"></spring:message></th>
                    <th><spring:message code="Starting Time"></spring:message></th>
                    <th><spring:message code="Ending Time"></spring:message></th>
                    <th><spring:message code="Ending Point"></spring:message></th>
                    <th><spring:message code="Weight"></spring:message></th>
                    <th><spring:message code="Shipping Cost"></spring:message></th>
                    <th><spring:message code="Subcontractor"></spring:message></th>
                    <th><spring:message code="Quota"></spring:message></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>user1</td>
                        <td>waybillno1</td>
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
                        <td>waybillno1</td>
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
                        <td>waybillno2</td>
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

    <div class="bs-docs-example" data-content="<spring:message code="XLSX"></spring:message> - <spring:message code="Outgoing"></spring:message>">
    <form:form modelAttribute="fileUploadBean" action="${outgoingImportXLSX}" method="post" enctype="multipart/form-data">
        <form:errors cssClass="text-error" path="file" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload"></spring:message></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example"></spring:message>">
            <table class="table">
                <thead>
                    <tr>
                        <th><spring:message code="Waybill"></spring:message></th>
                    <th><spring:message code="Outgoing"></spring:message></th>
                    <th><spring:message code="Company"></spring:message></th>
                    <th><spring:message code="County"></spring:message></th>
                    <th><spring:message code="City"></spring:message></th>
                    <th><spring:message code="Weight"></spring:message></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>waybillno1</td>
                        <td>outgoing11</td>
                        <td>company11</td>
                        <td>county11</td>
                        <td>city11</td>
                        <td>7</td>
                    </tr>
                    <tr>
                        <td>waybillno1</td>
                        <td>outgoing12</td>
                        <td>company12</td>
                        <td>county12</td>
                        <td>city12</td>
                        <td>11</td>
                    </tr>
                    <tr>
                        <td>waybillno2</td>
                        <td>outgoing21</td>
                        <td>company21</td>
                        <td>county21</td>
                        <td>city21</td>
                        <td>12</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

<%@ include file="/WEB-INF/views/footer.jsp" %>