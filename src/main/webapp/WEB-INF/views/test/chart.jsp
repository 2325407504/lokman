<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<div>
    <spring:url var="chart1Url" value="/test/report3" />
    <img src="${chart1Url}" alt="">
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />