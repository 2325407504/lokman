<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Paymenttypes" />
    <jsp:param name="property" value="paymenttype" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<aripd:description id="paymenttype">
    <aripd:descriptionitem label="Code" field="paymenttypeAttribute.code"></aripd:descriptionitem>
    <aripd:descriptionitem label="Name" field="paymenttypeAttribute.name"></aripd:descriptionitem>
</aripd:description>

<jsp:include page="/WEB-INF/views/footer.jsp" />