<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Waybills" />
    <jsp:param name="property" value="waybill" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="import" />
</jsp:include>

<div class="bs-docs-example" data-content="<spring:message code="Waybill" />">
    <spring:url var="waybillImport" value="/waybill/import" />
    <form:form modelAttribute="waybillImportAttribute" action="${waybillImport}" method="post" enctype="multipart/form-data">
        <form:errors path="*" cssClass="alert alert-error" element="div" />
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

<jsp:include page="/WEB-INF/views/footer.jsp" />