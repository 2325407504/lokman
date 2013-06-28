<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="aripd" tagdir="/WEB-INF/tags"%>

</div>
</div>
</div>
<footer class="container">
    <div class="row">
        <div class="span12 text-center">
            <c:set var="value1" value="2013" />
            <c:set var="value2" value="LGK" />
            <spring:message code="Copyright"
                            arguments="${value1};${value2}" htmlEscape="false"
                            argumentSeparator=";" />
        </div>
    </div>
</footer>

<aripd:flashattribute body="${message}"></aripd:flashattribute>

</body>
</html>