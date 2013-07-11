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
    <div class="span12">
        <aripd:description id="forwarding">
            <aripd:descriptionitem label="Account" field="forwardingAttribute.account.client.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document No" field="forwardingAttribute.waybillNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Driver" field="forwardingAttribute.driver"></aripd:descriptionitem>
            <aripd:descriptionitem label="Plate" field="forwardingAttribute.plate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Time" field="forwardingAttribute.startingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Time" field="forwardingAttribute.endingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Point" field="forwardingAttribute.endingpoint"></aripd:descriptionitem>
            <aripd:descriptionitem label="Weight" field="forwardingAttribute.loadWeightInTonne"></aripd:descriptionitem>
            <aripd:descriptionitem label="Shipping Cost" field="forwardingAttribute.shippingCost"></aripd:descriptionitem>
            <aripd:descriptionitem label="Subcontractor" field="forwardingAttribute.subcontractor.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Quota" field="forwardingAttribute.quota.name"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>
<div class="row-fluid">
    <div class="span12">
        <aripd:datatables datasource="/uatf/get/${forwardingAttribute.id}" id="uatfs" caption="Uatf">
            <aripd:datatablescolumn label="Code" field="code"/>
            <aripd:datatablescolumn label="Company" field="company"/>
            <aripd:datatablescolumn label="County" field="county"/>
            <aripd:datatablescolumn label="City" field="city"/>
            <aripd:datatablescolumn label="Weight" field="loadWeightInTonne"/>
        </aripd:datatables>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />