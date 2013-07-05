<%@ tag language="java" description="Flash Attribute for messages" pageEncoding="UTF-8" %>
<%@ attribute name="body" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${not empty body}">
    <div class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-body">
            <p><spring:message code="${body}" text="${body}" /></p>
        </div>
        <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true">
                <spring:message code="Okay" text="Okay" />
            </button>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function() {
            $('[role=dialog]').modal();
        });
    </script>
</c:if>