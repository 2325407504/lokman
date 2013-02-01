<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="container">
	<div class="row">
		<div class="span12">
			<ul class="breadcrumb">
			  <li><a href="#"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
			  <li class="active"><spring:message code="Field Management System"></spring:message></li>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="span12">
			<div>
				<c:url var="chartUrl" value="/fms/report3" />
				<img src="${chartUrl}" alt="">
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>