<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Customers" />
    <jsp:param name="property" value="customer" />
    <jsp:param name="new" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="customer1" caption="Customer">
            <aripd:descriptionitem label="Tax No" field="customerAttribute.taxNo"></aripd:descriptionitem>
            <aripd:descriptionitem label="Tax Office" field="customerAttribute.taxOffice"></aripd:descriptionitem>
            <aripd:descriptionitem label="Company" field="customerAttribute.name"></aripd:descriptionitem>
            <aripd:descriptionitem label="Postal Address" field="customerAttribute.address"></aripd:descriptionitem>
            <aripd:descriptionitem label="Phone Number" field="customerAttribute.phonenumber"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span4">
        <aripd:description id="customer2" caption="Additional Information">
            <aripd:descriptionitem label="Status" field="customerAttribute.active"></aripd:descriptionitem>
            <aripd:descriptionitem label="Container" field="customerAttribute.container"></aripd:descriptionitem>
            <aripd:descriptionitem label="Shipping Cost" field="customerAttribute.shippingcost"></aripd:descriptionitem>
            <aripd:descriptionitem label="Disposal Cost" field="customerAttribute.disposalcost"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span4">
        <c:if test="${customerAttribute.member != null}">
            <aripd:description id="customer3" caption="Member">
                <aripd:descriptionitem label="Status" field="customerAttribute.member.active"></aripd:descriptionitem>
                <aripd:descriptionitem label="Username" field="customerAttribute.member.username"></aripd:descriptionitem>
                <aripd:descriptionitem label="E-mail Address" field="customerAttribute.member.email"></aripd:descriptionitem>
                <aripd:descriptionitem label="Roles" field="customerAttribute.member.rolesAsString"></aripd:descriptionitem>
            </aripd:description>
        </c:if>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />