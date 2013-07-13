<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Forwardings" />
    <jsp:param name="property" value="userforwarding" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/userforwarding/get" id="forwardings" dataUrlShow="/userforwarding/show" dataUrlEdit="/userforwarding/edit">
	<aripd:datatablescolumn label="Action" field="id"/>
	<aripd:datatablescolumn label="Account" field="account.username"/>
	<aripd:datatablescolumn label="Document No" field="waybillNo"/>
	<aripd:datatablescolumn label="Driver" field="driver"/>
	<aripd:datatablescolumn label="Subcontractor" field="subcontractor.name"/>
	<aripd:datatablescolumn label="Quota" field="quota.name"/>
	<aripd:datatablescolumn label="Plate" field="plate"/>
	<aripd:datatablescolumn label="Starting Time" field="startingTime"/>
	<aripd:datatablescolumn label="Ending Time" field="endingTime"/>
	<aripd:datatablescolumn label="Ending Point" field="endingpoint"/>
	<aripd:datatablescolumn label="Weight" field="weight"/>
	<aripd:datatablescolumn label="Shipping Cost" field="shippingCost"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />