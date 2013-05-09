<%@ include file="/WEB-INF/jsp/header.jsp" %>

<spring:url value="/" var="homeUrl" />
<spring:url var="backup_take" value="/backup/take"/>

<ul class="breadcrumb">
  <li><a href="${homeUrl}"><spring:message code="Home"></spring:message></a> <span class="divider">/</span></li>
  <li class="active"><spring:message code="Roles"></spring:message></li>
	<li class="pull-right">
		<a class="btn btn-mini" href="${backup_take}"><spring:message code="Take Backup"></spring:message></a>
	</li>
</ul>

<table class="table">
  <c:forEach var="file" items="${files}">
    <tr>
      <td>${file.name}</td>
      <td><a href="/backup/${file.name}/restore"><spring:message code="Restore"></spring:message></a></td>
      <td><a href="/backup/${file.name}/delete"><spring:message code="Delete"></spring:message></a></td>
    </tr>
  </c:forEach>
</table>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>