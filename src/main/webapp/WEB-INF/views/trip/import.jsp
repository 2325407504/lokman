<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Trips" />
    <jsp:param name="property" value="trip" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="import" />
</jsp:include>

<div class="bs-docs-example" data-content="<spring:message code="Trip" />">
    <spring:url var="import" value="/trip/import" />
    <form:form modelAttribute="tripAttribute" action="${import}" method="post" enctype="multipart/form-data">
        <form:errors path="*" cssClass="alert alert-error" element="div" />
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

<jsp:include page="/WEB-INF/views/footer.jsp" />