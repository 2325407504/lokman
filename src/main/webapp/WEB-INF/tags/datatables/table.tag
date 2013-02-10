<%@ tag language="java" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="datasource" required="true" rtexprvalue="true" %>
<%@ attribute name="dataUrlEdit" required="false" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="org_languagetool_tags_table_outputmode" value="TABLE" scope="request" />
<table class="table table-striped table-bordered" id="${id}">
  <thead>
    <tr><jsp:doBody /></tr>
  </thead>
  <tbody></tbody>
</table>
<c:set var="org_languagetool_tags_table_outputmode" value="SCRIPT" scope="request" />
<c:set var="org_languagetool_tags_table_firstcolumn" value="TRUE" scope="request" />
<script type="text/javascript">
$(document).ready(function() {
	$('#${id}').dataTable( {
		"sAjaxSource": "<c:url value="${datasource}" />",
		"bProcessing": true,
		"bServerSide": true,
		"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
			$('td:eq(0)', nRow).html( '<a href="<c:url value="${dataUrlEdit}" />/'+aData.id+'">'+aData.id+'</a>' );
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
		} );
} );	
</script>	