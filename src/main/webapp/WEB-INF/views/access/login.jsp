<%@ include file="/WEB-INF/views/header.jsp" %>

<form class="form-signin" action="j_spring_security_check" method="post">
    <h2 class="form-signin-heading"><spring:message code="Login" /></h2>
    <input type="text" class="input-block-level" name="j_username" placeholder="<spring:message code="Username" />">
    <input type="password" class="input-block-level" name="j_password" placeholder="<spring:message code="Password" />">
    <label class="checkbox">
        <input type="checkbox" name="_spring_security_remember_me" />
        <spring:message code="Remember me" />
    </label>
    <button class="btn btn-large btn-primary" type="submit"><spring:message code="Login" /></button>
</form>

<script>
    $(function() {
        $("input[name=j_username]").focus();
    });
</script>


<%@ include file="/WEB-INF/views/footer.jsp" %>