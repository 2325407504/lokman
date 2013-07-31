<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employees" />
    <jsp:param name="property" value="employee" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="active" value="import" />
</jsp:include>

<div class="bs-docs-example" data-content="<spring:message code="Employee" />">
    <spring:url var="employeeImport" value="/employee/import" />
    <form:form modelAttribute="employeeImportAttribute" action="${employeeImport}" method="post" enctype="multipart/form-data">
        <form:errors path="*" cssClass="alert alert-error" element="div" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload" /></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example" />">
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="TC Kimlik No" /></th>
                    <th><spring:message code="FirstName" /></th>
                    <th><spring:message code="LastName" /></th>
                    <th><spring:message code="Postal Address" /></th>
                    <th><spring:message code="Phone Number" /></th>
                    <th><spring:message code="Date of birth" /></th>
                    <th><spring:message code="Date of starting the job" /></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>12345678901</td>
                    <td>Ad1</td>
                    <td>Soyad1</td>
                    <td>Adres1</td>
                    <td>5331112233</td>
                    <td>01.03.1980</td>
                    <td>01.06.2012</td>
                </tr>
                <tr>
                    <td>12345678902</td>
                    <td>Ad2</td>
                    <td>Soyad2</td>
                    <td>Adres2</td>
                    <td>5551112233</td>
                    <td>01.12.1982</td>
                    <td>01.04.2010</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />