<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<ul class="breadcrumb">
    <li><a href="${homeUrl}"><spring:message code="Home" /></a> <span class="divider">/</span></li>
    <li class="active"><spring:message code="Procedures" /> <span class="divider">/</li>
    <li class="active">${userprocAttribute.name}</li>
</ul>

<h1>${userprocAttribute.name}</h1>
<p class="lead">${userprocAttribute.description}</p>
<div class="bs-docs-example" data-content="${userprocAttribute.name}">
    ${userprocAttribute.content}
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />