<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Regions" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Regions" />
    <jsp:param name="property" value="region" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="region">
    <aripd:descriptionitem label="Name" field="regionAttribute.name"></aripd:descriptionitem>
</aripd:description>

<div class="form-actions">
    <a class="btn" href="${regionEdit}"><spring:message code="Edit" /></a>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />