<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Forwardings" />
    <jsp:param name="property" value="forwarding" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/forwarding/get" id="forwardings" dataUrlShow="/forwarding/show" dataUrlEdit="/forwarding/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Account" field="account.username"/>
    <aripd:column label="Document No" field="waybillNo"/>
    <aripd:column label="Driver" field="driver"/>
    <aripd:column label="Subcontractor" field="subcontractor.name"/>
    <aripd:column label="Quota" field="quota.name"/>
    <aripd:column label="Plate" field="plate"/>
    <aripd:column label="Starting Time" field="startingTime"/>
    <aripd:column label="Ending Time" field="endingTime"/>
    <aripd:column label="Ending Point" field="endingPoint"/>
    <aripd:column label="Weight" field="loadWeightInTonne"/>
    <aripd:column label="Shipping Cost" field="shippingCost"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />