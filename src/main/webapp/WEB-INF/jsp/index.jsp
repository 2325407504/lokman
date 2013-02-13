<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Dashboard" text="Dashboard"></spring:message></li>
</ul>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>