<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Expenses" />
    <jsp:param name="property" value="userexpense" />
    <jsp:param name="new" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="expense">
            <aripd:descriptionitem label="Account" field="userexpenseAttribute.account.employee.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Expensetype" field="userexpenseAttribute.expensetype.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document Date" field="userexpenseAttribute.documentDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Company" field="userexpenseAttribute.company"></aripd:descriptionitem>
            <aripd:descriptionitem label="Description" field="userexpenseAttribute.description"></aripd:descriptionitem>
            <aripd:descriptionitem label="Amount" field="userexpenseAttribute.amount"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />