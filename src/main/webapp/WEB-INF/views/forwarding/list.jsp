<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Forwardings" />
    <jsp:param name="property" value="forwarding" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/forwarding/get" id="forwardings" dataUrlShow="/forwarding/show" dataUrlEdit="/forwarding/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Member" field="member.username"/>
    <aripd:datatablescolumn label="Document No" field="waybillNo"/>
    <aripd:datatablescolumn label="Driver" field="driver"/>
    <aripd:datatablescolumn label="Plate" field="plate"/>
    <aripd:datatablescolumn label="Starting Time" field="startingTime"/>
    <aripd:datatablescolumn label="Ending Time" field="endingTime"/>
    <aripd:datatablescolumn label="Starting Point" field="startingpoint.name"/>
    <aripd:datatablescolumn label="Ending Point" field="endingpoint.name"/>
    <aripd:datatablescolumn label="Starting Km" field="startingKm"/>
    <aripd:datatablescolumn label="Ending Km" field="endingKm"/>
    <aripd:datatablescolumn label="Weight" field="weight"/>
    <aripd:datatablescolumn label="Shipping Cost" field="shippingCost"/>
    <aripd:datatablescolumn label="Subcontractor" field="subcontractor.name"/>
    <aripd:datatablescolumn label="Quota" field="quota.name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />