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
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="forwarding">
            <aripd:descriptionitem label="Account" field="forwardingAttribute.account.client.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Waybill No" field="forwardingAttribute.waybillNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Driver" field="forwardingAttribute.driver"></aripd:descriptionitem>
            <aripd:descriptionitem label="Plate" field="forwardingAttribute.plate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Time" field="forwardingAttribute.startingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Time" field="forwardingAttribute.endingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Point" field="forwardingAttribute.endingPoint"></aripd:descriptionitem>
            <aripd:descriptionitem label="Weight" field="forwardingAttribute.loadWeightInTonne"></aripd:descriptionitem>
            <aripd:descriptionitem label="Shipping Cost" field="forwardingAttribute.shippingCost"></aripd:descriptionitem>
            <aripd:descriptionitem label="Subcontractor" field="forwardingAttribute.subcontractor.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Quota" field="forwardingAttribute.quota.name"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span8">
        <aripd:datatables datasource="/uatf/get/${forwardingAttribute.id}" id="uatfs">
            <aripd:column label="Code" field="code"/>
            <aripd:column label="Company" field="company"/>
            <aripd:column label="County" field="county"/>
            <aripd:column label="City" field="city"/>
            <aripd:column label="Weight" field="loadWeightInTonne"/>
        </aripd:datatables>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />