<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Quotas" />
    <jsp:param name="property" value="quota" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="quota">
    <aripd:descriptionitem label="Code" field="quotaAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="quotaAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />