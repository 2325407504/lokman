<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Expenses" />
    <jsp:param name="property" value="expense" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="expense">
            <aripd:descriptionitem label="Account" field="expenseAttribute.account.employee.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Expensetype" field="expenseAttribute.expensetype.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document Date" field="expenseAttribute.documentDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Company" field="expenseAttribute.company"></aripd:descriptionitem>
            <aripd:descriptionitem label="Description" field="expenseAttribute.description"></aripd:descriptionitem>
            <aripd:descriptionitem label="Amount" field="expenseAttribute.amount"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />