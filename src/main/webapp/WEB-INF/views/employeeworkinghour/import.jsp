<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Working Hours" />
    <jsp:param name="property" value="employeeworkinghour" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="import" />
</jsp:include>

<div class="bs-docs-example" data-content="<spring:message code="Employee Working Hour" />">
    <spring:url var="employeeworkinghourImport" value="/employeeworkinghour/import" />
    <form:form modelAttribute="employeeworkinghourImportAttribute" action="${employeeworkinghourImport}" method="post" enctype="multipart/form-data">
        <form:errors path="*" cssClass="alert alert-error" element="div" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload" /></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example" />">
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="Account" /></th>
                    <th><spring:message code="Employee Working Hour Type" /></th>
                    <th><spring:message code="Starting Time" /></th>
                    <th><spring:message code="Ending Time" /></th>
                    <th><spring:message code="Remark" /></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>user1</td>
                    <td>code1</td>
                    <td>11.02.2013 08:00</td>
                    <td>11.02.2013 16:00</td>
                    <td>remark1</td>
                </tr>
                <tr>
                    <td>user2</td>
                    <td>code2</td>
                    <td>01.07.2013 16:00</td>
                    <td>01.07.2013 24:00</td>
                    <td>remark2</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />