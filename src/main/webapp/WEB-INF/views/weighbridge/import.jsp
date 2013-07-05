<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Weighbridges" />
    <jsp:param name="property" value="weighbridge" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="import" />
</jsp:include>

<div class="bs-docs-example" data-content="<spring:message code="Weighbridge" />">
    <spring:url var="weighbridgeImport" value="/weighbridge/import" />
    <form:form modelAttribute="weighbridgeImportAttribute" action="${weighbridgeImport}" method="post" enctype="multipart/form-data">
        <form:errors path="*" cssClass="alert alert-error" element="div" />
        <form:input path="file" type="file"/>
        <button class="pull-right btn btn-mini btn-primary" type="submit"><spring:message code="Upload" /></button>
    </form:form>
    <div class="bs-docs-example" data-content="<spring:message code="Example" />">
        <table class="table">
            <thead>
                <tr>
                    <th><spring:message code="Account" /></th>
                    <th><spring:message code="Date" /></th>
                    <th><spring:message code="Company" /></th>
                    <th><spring:message code="Description" /></th>
                    <th><spring:message code="Amount" /></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>user1</td>
                    <td>11.02.2013 21:03</td>
                    <td>company1</td>
                    <td>description1</td>
                    <td>11</td>
                </tr>
                <tr>
                    <td>user2</td>
                    <td>01.03.2013 12:13</td>
                    <td>company2</td>
                    <td>description2</td>
                    <td>121.34</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />