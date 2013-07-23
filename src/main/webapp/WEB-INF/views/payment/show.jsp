<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Payments" />
    <jsp:param name="property" value="payment" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="payment">
            <aripd:descriptionitem label="Employee" field="paymentAttribute.employee.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Paymenttype" field="paymentAttribute.paymenttype.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document Date" field="paymentAttribute.documentDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Remark" field="paymentAttribute.remark"></aripd:descriptionitem>
            <aripd:descriptionitem label="Amount" field="paymentAttribute.amount"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />