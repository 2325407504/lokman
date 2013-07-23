<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Forwardings" />
    <jsp:param name="property" value="userforwarding" />
    <jsp:param name="new" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="forwarding">
            <aripd:descriptionitem label="Document No" field="userforwardingAttribute.waybillNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Driver" field="userforwardingAttribute.driver"></aripd:descriptionitem>
            <aripd:descriptionitem label="Plate" field="userforwardingAttribute.plate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Time" field="userforwardingAttribute.startingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Time" field="userforwardingAttribute.endingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Point" field="userforwardingAttribute.endingpoint"></aripd:descriptionitem>
            <aripd:descriptionitem label="Weight" field="userforwardingAttribute.weight"></aripd:descriptionitem>
            <aripd:descriptionitem label="Shipping Cost" field="userforwardingAttribute.shippingCost"></aripd:descriptionitem>
            <aripd:descriptionitem label="Subcontractor" field="userforwardingAttribute.subcontractor.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Quota" field="userforwardingAttribute.quota.name"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span8">
        <aripd:datatables datasource="/useruatf/get/${userforwardingAttribute.id}" id="uatfs">
            <aripd:datatablescolumn label="Code" field="code"/>
            <aripd:datatablescolumn label="Company" field="company"/>
            <aripd:datatablescolumn label="County" field="county"/>
            <aripd:datatablescolumn label="City" field="city"/>
            <aripd:datatablescolumn label="Weight" field="weight"/>
        </aripd:datatables>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />