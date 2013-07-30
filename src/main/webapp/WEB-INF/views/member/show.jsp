<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="Members" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Members" />
    <jsp:param name="property" value="member" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<c:forEach var="role" items="${memberAttribute.roles}"><span class="label label-success">${role.name}</span>&nbsp;</c:forEach>
<aripd:description id="member">
    <aripd:descriptionitem label="Active" field="memberAttribute.active"></aripd:descriptionitem>
    <aripd:descriptionitem label="Username" field="memberAttribute.username"></aripd:descriptionitem>
    <aripd:descriptionitem label="E-mail Address" field="memberAttribute.email"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />