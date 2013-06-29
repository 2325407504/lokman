<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Expensetypes" />
    <jsp:param name="property" value="expensetype" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="expensetype">
    <aripd:descriptionitem label="Name" field="expensetypeAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${expensetypeEdit}"><spring:message code="Edit" /></a>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />