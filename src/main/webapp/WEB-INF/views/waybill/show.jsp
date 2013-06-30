<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Waybills" />
    <jsp:param name="property" value="waybill" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="waybill-other">
            <aripd:descriptionitem label="Status" field="waybillAttribute.submitted"></aripd:descriptionitem>
            <aripd:descriptionitem label="Account" field="waybillAttribute.account.client.fullname"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span4">
        <aripd:description id="waybill">
            <aripd:descriptionitem label="Document No" field="waybillAttribute.documentNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document Date" field="waybillAttribute.documentDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Company" field="waybillAttribute.company"></aripd:descriptionitem>
            <aripd:descriptionitem label="Driver" field="waybillAttribute.driver"></aripd:descriptionitem>
            <aripd:descriptionitem label="Plate" field="waybillAttribute.plate"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span4">
        <aripd:description id="waybill-invoice">
            <aripd:descriptionitem label="Name" field="waybillAttribute.invoice.customer.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Postal Address" field="waybillAttribute.invoice.customer.address"></aripd:descriptionitem>
            <aripd:descriptionitem label="Phone Number" field="waybillAttribute.invoice.customer.phonenumber"></aripd:descriptionitem>
            <aripd:descriptionitem label="Tax No" field="waybillAttribute.invoice.customer.taxNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Tax Office" field="waybillAttribute.invoice.customer.taxOffice"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document No" field="waybillAttribute.invoice.documentNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document Date" field="waybillAttribute.invoice.documentDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Amount" field="waybillAttribute.invoice.amount"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>
<div class="row-fluid">
    <div class="span12">
        <aripd:datatables datasource="/outgoing/get/${waybillAttribute.id}" id="outgoings">
            <aripd:column label="Product" field="product.name"/>
            <aripd:column label="Remark" field="remark"/>
            <aripd:column label="Weight" field="weight"/>
        </aripd:datatables>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />