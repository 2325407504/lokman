<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Invoices" />
    <jsp:param name="property" value="invoice" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="invoice-other">
            <aripd:descriptionitem label="Status" field="invoiceAttribute.submitted"></aripd:descriptionitem>
            <aripd:descriptionitem label="Account" field="invoiceAttribute.account.client.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document No" field="invoiceAttribute.documentNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document Date" field="invoiceAttribute.documentDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Customer" field="invoiceAttribute.customer.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Amount" field="invoiceAttribute.amount"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>
<div class="row-fluid">
    <div class="span12">
        <aripd:datatables datasource="/invoice/waybill/get/${invoiceAttribute.id}" id="waybills" dataUrlShow="/waybill/show">
            <aripd:datatablescolumn label="Action" field="id"/>
            <aripd:datatablescolumn label="Document No" field="documentNo"/>
            <aripd:datatablescolumn label="Document Date" field="documentDate"/>
            <aripd:datatablescolumn label="Company" field="company"/>
            <aripd:datatablescolumn label="Driver" field="driver"/>
            <aripd:datatablescolumn label="Plate" field="plate"/>
        </aripd:datatables>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />