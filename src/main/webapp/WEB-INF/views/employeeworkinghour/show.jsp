<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employee Working Hours" />
    <jsp:param name="property" value="employeeworkinghour" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="employeeworkinghour">
            <aripd:descriptionitem label="Submit" field="employeeworkinghourAttribute.submitted"></aripd:descriptionitem>
            <aripd:descriptionitem label="Employee" field="employeeworkinghourAttribute.employee.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Employee Working Hour Type" field="employeeworkinghourAttribute.employeeworkinghourtype.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Starting Time" field="employeeworkinghourAttribute.startingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Ending Time" field="employeeworkinghourAttribute.endingTime"></aripd:descriptionitem>
            <aripd:descriptionitem label="Hour" field="employeeworkinghourAttribute.nofWorkhours"></aripd:descriptionitem>
            <aripd:descriptionitem label="Remark" field="employeeworkinghourAttribute.remark"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />