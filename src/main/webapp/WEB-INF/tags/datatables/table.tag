<%@ tag language="java" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="datasource" required="true" rtexprvalue="true" %>
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
    	"sPaginationType": "bootstrap",
    	"oLanguage": {
    		"sLengthMenu": "<spring:message code="table.records.count" />",
    		"sInfo": "<spring:message code="table.showing.records" />"},
        "aoColumns":[
                   	<jsp:doBody />
                   	]
    } );
} );	
</script>	