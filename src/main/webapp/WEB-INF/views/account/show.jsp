<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Accounts" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Accounts" />
    <jsp:param name="property" value="account" />
    <jsp:param name="active" value="show" />
</jsp:include>

<c:forEach var="role" items="${accountAttribute.roles}"><span class="label label-success">${role.name}</span>&nbsp;</c:forEach>
<aripd:description id="account">
    <aripd:descriptionitem label="Username" field="accountAttribute.username"></aripd:descriptionitem>
    <aripd:descriptionitem label="Fullname" field="accountAttribute.client.fullname"></aripd:descriptionitem>
    <aripd:descriptionitem label="E-mail Address" field="accountAttribute.email"></aripd:descriptionitem>
    <aripd:descriptionitem label="Region" field="accountAttribute.region.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />