<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Weighbridges" />
    <jsp:param name="property" value="weighbridge" />
    <jsp:param name="new" value="true" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="weighbridge">
            <aripd:descriptionitem label="Status" field="weighbridgeAttribute.submitted"></aripd:descriptionitem>
            <aripd:descriptionitem label="Member" field="weighbridgeAttribute.member.employee.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Clerk" field="weighbridgeAttribute.clerk"></aripd:descriptionitem>
            <aripd:descriptionitem label="Plate" field="weighbridgeAttribute.plate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Driver" field="weighbridgeAttribute.driver"></aripd:descriptionitem>
            <aripd:descriptionitem label="Location From" field="weighbridgeAttribute.locationFrom"></aripd:descriptionitem>
            <aripd:descriptionitem label="Location To" field="weighbridgeAttribute.locationTo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Check-in" field="weighbridgeAttribute.checkin"></aripd:descriptionitem>
            <aripd:descriptionitem label="Check-out" field="weighbridgeAttribute.checkout"></aripd:descriptionitem>
            <aripd:descriptionitem label="Good Type" field="weighbridgeAttribute.goodtype"></aripd:descriptionitem>
            <aripd:descriptionitem label="Customer" field="weighbridgeAttribute.customer"></aripd:descriptionitem>
            <aripd:descriptionitem label="First Weighing" field="weighbridgeAttribute.firstWeighing"></aripd:descriptionitem>
            <aripd:descriptionitem label="Last Weighing" field="weighbridgeAttribute.lastWeighing"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span4">
        <table class="table">
            <caption><spring:message code="Extrications" /></caption>
            <c:forEach items="${weighbridgeAttribute.extrications}" var="extrication">
                <tr>
                    <td>${extrication.waste.name}</td>
                    <td>${extrication.val}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />