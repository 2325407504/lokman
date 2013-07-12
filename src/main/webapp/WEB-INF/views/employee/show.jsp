<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Employees" />
    <jsp:param name="property" value="employee" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span12">
        <aripd:description id="employee">
            <aripd:descriptionitem label="TC Kimlik" field="employeeAttribute.tckimlik"></aripd:descriptionitem>
            <aripd:descriptionitem label="First Name" field="employeeAttribute.firstName"></aripd:descriptionitem>
            <aripd:descriptionitem label="Last Name" field="employeeAttribute.lastName"></aripd:descriptionitem>
            <aripd:descriptionitem label="Postal Address" field="employeeAttribute.address"></aripd:descriptionitem>
            <aripd:descriptionitem label="Phone Number" field="employeeAttribute.phonenumber"></aripd:descriptionitem>
            <aripd:descriptionitem label="Date of birth" field="employeeAttribute.birthdate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Date of starting the job" field="employeeAttribute.startingDate"></aripd:descriptionitem>
        </aripd:description>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />