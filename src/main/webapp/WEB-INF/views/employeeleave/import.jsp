<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Leaves" />
    <jsp:param name="property" value="employeeleave" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="import" />
</jsp:include>

<div class="bs-docs-example" data-content="<spring:message code="Employee Leave" />">
    <spring:url var="employeeleaveImport" value="/employeeleave/import" />
    <form:form modelAttribute="employeeleaveImportAttribute" action="${employeeleaveImport}" method="post" enctype="multipart/form-data">
        <form:errors path="*" cssClass="alert alert-error" element="div" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload" /></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example" />">
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="Account" /></th>
                    <th><spring:message code="Employee Leave Type" /></th>
                    <th><spring:message code="Starting Date" /></th>
                    <th><spring:message code="Ending Date" /></th>
                    <th><spring:message code="Remark" /></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>user1</td>
                    <td>code1</td>
                    <td>11.02.2013</td>
                    <td>11.03.2013</td>
                    <td>remark1</td>
                </tr>
                <tr>
                    <td>user2</td>
                    <td>code2</td>
                    <td>01.07.2013</td>
                    <td>01.08.2013</td>
                    <td>remark2</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />