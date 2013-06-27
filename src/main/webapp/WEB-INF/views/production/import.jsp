<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="productionList" value="/production/list" />
<spring:url var="productionNew" value="/production/new" />
<spring:url var="productionImportXLSX" value="/production/import/xls" />
<spring:url var="bigbagImportXLSX" value="/bigbag/import/xls" />
<spring:url var="productionExport" value="/production/export/xls" />
<spring:url var="bigbagExport" value="/bigbag/export/xls" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${productionList}"><spring:message code="Productions" /></a></li>
    <li class=""><a href="${productionNew}"><spring:message code="New Entry" /></a></li>
    <li class="active"><a href="${productionImportXLSX}"><spring:message code="Import" /></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${productionExport}"><spring:message code="Waybill" /></a></li>
            <li><a href="${bigbagExport}"><spring:message code="UATF" /></a></li>
            </ul>
        </li>
    </ul>

    <div class="bs-docs-example" data-content="<spring:message code="XLSX" /> - <spring:message code="Waybill" />">
    <form:form modelAttribute="fileUploadBean" action="${productionImportXLSX}" method="post" enctype="multipart/form-data">
        <form:errors cssClass="text-error" path="file" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload" /></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example" />">
            <table class="table">
                <thead>
                    <tr>
                        <th><spring:message code="Account" /></th>
                    <th><spring:message code="Waybill" /></th>
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

    <div class="bs-docs-example" data-content="<spring:message code="XLSX" /> - <spring:message code="UATF" />">
    <form:form modelAttribute="fileUploadBean" action="${bigbagImportXLSX}" method="post" enctype="multipart/form-data">
        <form:errors cssClass="text-error" path="file" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload" /></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example" />">
            <table class="table">
                <thead>
                    <tr>
                        <th><spring:message code="Waybill" /></th>
                    <th><spring:message code="UATF" /></th>
                    <th><spring:message code="Company" /></th>
                    <th><spring:message code="County" /></th>
                    <th><spring:message code="City" /></th>
                    <th><spring:message code="Weight" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>waybillno1</td>
                        <td>bigbag11</td>
                        <td>company11</td>
                        <td>county11</td>
                        <td>city11</td>
                        <td>7</td>
                    </tr>
                    <tr>
                        <td>waybillno1</td>
                        <td>bigbag12</td>
                        <td>company12</td>
                        <td>county12</td>
                        <td>city12</td>
                        <td>11</td>
                    </tr>
                    <tr>
                        <td>waybillno2</td>
                        <td>bigbag21</td>
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