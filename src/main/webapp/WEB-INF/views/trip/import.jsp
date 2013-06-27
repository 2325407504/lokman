<%@ include file="/WEB-INF/views/header.jsp" %>

<spring:url var="homeUrl" value="/" />
<spring:url var="tripList" value="/trip/list" />
<spring:url var="tripNew" value="/trip/new" />
<spring:url var="tripImportXLSX" value="/trip/import/xls" />
<spring:url var="tripExport" value="/trip/export/xls" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class=""><a href="${tripList}"><spring:message code="Trips" /></a></li>
    <li class=""><a href="${tripNew}"><spring:message code="New Entry" /></a></li>
    <li class="active"><a href="${tripImportXLSX}"><spring:message code="Import" /></a></li>
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <spring:message code="Export" />
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="${tripExport}"><spring:message code="Trips" /></a></li>
            </ul>
        </li>
    </ul>

    <div class="bs-docs-example" data-content="<spring:message code="XLSX" />">
    <form:form modelAttribute="fileUploadBean" action="${tripImportXLSX}" method="post" enctype="multipart/form-data">
        <form:errors cssClass="text-error" path="file" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload" /></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example" />">
            <table class="table">
                <thead>
                    <tr>
                        <th><spring:message code="Account" /></th>
                    <th><spring:message code="Plate" /></th>
                    <th><spring:message code="Driver" /></th>
                    <th><spring:message code="Starting Point" /></th>
                    <th><spring:message code="Starting Km" /></th>
                    <th><spring:message code="Starting Time" /></th>
                    <th><spring:message code="Ending Point" /></th>
                    <th><spring:message code="Ending Km" /></th>
                    <th><spring:message code="Ending Time" /></th>
                    <th><spring:message code="Weight" /></th>
                    <th><spring:message code="Remark" /></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>user1</td>
                        <td>plate1</td>
                        <td>driverCode1</td>
                        <td>startingPoint1</td>
                        <td>123</td>
                        <td>11.02.2013 21:03</td>
                        <td>endingPoint1</td>
                        <td>125</td>
                        <td>11.02.2013 22:15</td>
                        <td>11</td>
                        <td>remark1</td>
                    </tr>
                    <tr>
                        <td>user2</td>
                        <td>plate2</td>
                        <td>driverCode2</td>
                        <td>startingPoint2</td>
                        <td>234</td>
                        <td>10.03.2013 20:12</td>
                        <td>endingPoint2</td>
                        <td>245</td>
                        <td>11.03.2013 02:21</td>
                        <td>22</td>
                        <td>remark2</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

<%@ include file="/WEB-INF/views/footer.jsp" %>