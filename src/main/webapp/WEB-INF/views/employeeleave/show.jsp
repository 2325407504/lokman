<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Leaves" />
    <jsp:param name="property" value="employeeleave" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="employeeleave">
            <aripd:descriptionitem label="Submit" field="employeeleaveAttribute.submitted"></aripd:descriptionitem>
            <aripd:descriptionitem label="Account" field="employeeleaveAttribute.account.employee.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Employee Leave Type" field="employeeleaveAttribute.employeeleavetype.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Date" field="employeeleaveAttribute.startingDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Date" field="employeeleaveAttribute.endingDate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Remark" field="employeeleaveAttribute.remark"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />