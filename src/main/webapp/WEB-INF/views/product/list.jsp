<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<spring:url var="homeUrl" value="/" />
<spring:url var="productList" value="/product/list" />
<spring:url var="productNew" value="/product/new" />

<ul class="nav nav-tabs">
    <li class=""><a href="${homeUrl}"><i class="icon-home"></i></a></li>
    <li class="active"><a href="${productList}"><spring:message code="Products" /></a></li>
    <li class=""><a href="${productNew}"><spring:message code="New Entry" /></a></li>
    </ul>

<aripd:datatables datasource="/product/get" id="products" dataUrlShow="/product/show" dataUrlEdit="/product/edit">
    <aripd:column label="Action" field="id"/>
    <aripd:column label="Product Group" field="productgroup.name"/>
    <aripd:column label="Code" field="code"/>
    <aripd:column label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />