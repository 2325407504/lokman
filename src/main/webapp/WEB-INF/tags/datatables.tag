<%@ tag language="java" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="datasource" required="true" rtexprvalue="true" %>
<%@ attribute name="dataUrlShow" required="false" rtexprvalue="true" %>
<%@ attribute name="dataUrlEdit" required="false" rtexprvalue="true" %>
<%@ attribute name="dataUrlDelete" required="false" rtexprvalue="true" %>
<%@ attribute name="actionColumn" required="false" type="java.lang.Integer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${actionColumn == null}"><c:set var="actionColumn" value="0" /></c:if>
<c:set var="org_languagetool_tags_table_outputmode" value="TABLE" scope="request" />
<table class="table table-striped table-bordered" id="${id}">
	<thead>
		<tr><jsp:doBody /></tr>
	</thead>
	<tbody></tbody>
	<tfoot>
		<tr><jsp:doBody /></tr>
	</tfoot>
</table>
<c:set var="org_languagetool_tags_table_outputmode" value="SCRIPT" scope="request" />
<c:set var="org_languagetool_tags_table_firstcolumn" value="TRUE" scope="request" />
<link rel="stylesheet" href="/resources/dataTables/DT_bootstrap.css" />
<script type="text/javascript" src="/resources/dataTables/jquery.dataTables.js"></script>
<script type="text/javascript" src="/resources/dataTables/DT_bootstrap.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#${id}').dataTable( {
		"sAjaxSource": "<spring:url value="${datasource}" />",
		"bProcessing": true,
		"bServerSide": true,
		"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			<c:if test="${not empty dataUrlShow or not empty dataUrlEdit or not empty dataUrlDelete}">
			$('td:eq(${actionColumn})', nRow).html('');
			<c:if test="${not empty dataUrlShow}">
				$('td:eq(${actionColumn})', nRow).append( '<a href="<spring:url value="${dataUrlShow}" />/'+aData.id+'"><i class="icon-list-alt"></i></a>&nbsp;' );
			</c:if>
			if (aData.submitted) {
				$(nRow).addClass('success');
			}
			else if (!aData.submitted) {
				<c:if test="${not empty dataUrlEdit}">
					$('td:eq(${actionColumn})', nRow).append( '<a href="<spring:url value="${dataUrlEdit}" />/'+aData.id+'"><i class="icon-pencil"></i></a>&nbsp;' );
				</c:if>
			}
			<c:if test="${not empty dataUrlDelete}">
				$('td:eq(${actionColumn})', nRow).append( '<form style="display:inline" method="POST" action="<spring:url value="${dataUrlDelete}" />/'+aData.id+'"><button type="submit"><i class="icon-remove"></i></button></form>&nbsp;' );
			</c:if>
			</c:if>
		},
		"oLanguage": {
			"sProcessing":   "<spring:message code="table.records.processing" />",
			"sLengthMenu":   "<spring:message code="table.records.count" />",
			"sZeroRecords":  "<spring:message code="table.records.zero" />",
			"sInfo":         "<spring:message code="table.showing.records" />"},
			"sInfoEmtpy":    "<spring:message code="table.records.empty" />",
			"sInfoFiltered": "<spring:message code="table.showing.filtered" />",
			"sInfoPostFix":  "",
			"sSearch":       "<spring:message code="table.records.search" />",
			"sUrl":          "",
			"oPaginate": {
				"sFirst":    "<spring:message code="table.records.first" />",
				"sPrevious": "<spring:message code="table.records.previous" />",
				"sNext":     "<spring:message code="table.records.next" />",
				"sLast":     "<spring:message code="table.records.last" />"
			},
			"aoColumns":[
			             <jsp:doBody />
			            ]
		});
});
</script>	