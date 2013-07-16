<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employeeeleaves" />
    <jsp:param name="property" value="employeeleave" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="employeeleave">
            <aripd:descriptionitem label="Account" field="employeeleaveAttribute.account.employee.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Employeeeleavetype" field="employeeleaveAttribute.employeeleavetype.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Document Date" field="employeeleaveAttribute.documentDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Company" field="employeeleaveAttribute.company"></aripd:descriptionitem>
            <aripd:descriptionitem label="Description" field="employeeleaveAttribute.description"></aripd:descriptionitem>
            <aripd:descriptionitem label="Amount" field="employeeleaveAttribute.amount"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />