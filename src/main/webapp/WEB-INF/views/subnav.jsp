<%@include file="/WEB-INF/views/includes.jsp" %>

<c:set var="attribute" value="${param.property}Attribute" />

<c:if test="${param.active == 'list'}"><c:set var="list_class" value="active" /></c:if>
<c:if test="${param.active == 'new'}"><c:set var="new_class" value="active" /></c:if>
<c:if test="${param.active == 'show'}"><c:set var="show_class" value="active" /></c:if>
<c:if test="${param.active == 'form'}">
    <c:choose>
        <c:when test="${ !empty requestScope[attribute].id }">
            <c:set var="edit_class" value="active" />
        </c:when>
        <c:when test="${ empty requestScope[attribute].id }">
            <c:set var="new_class" value="active" />
        </c:when>
    </c:choose>
</c:if>

<spring:url var="home" value="/" />
<spring:url var="list" value="/{property}/list">
    <spring:param name="property" value="${param.property}" />
</spring:url>

<ul class="nav nav-tabs">
    <li class=""><a href="${home}"><i class="icon-home"></i></a></li>
    <li class="${list_class}">
        <a href="${list}"><spring:message code="${param.title}" text="${param.title}" /></a>
    </li>
    <c:if test="${param.new}">
        <spring:url var="new" value="/{property}/new">
            <spring:param name="property" value="${param.property}" />
        </spring:url>
        <li class="${new_class}">
            <a href="${new}"><spring:message code="New Entry" /></a>
        </li>
    </c:if>
    <c:if test="${param.import}">
        <c:if test="${param.active == 'import'}"><c:set var="import_class" value="active" /></c:if>
        <spring:url var="import" value="/{property}/import">
            <spring:param name="property" value="${param.property}" />
        </spring:url>
        <li class="${import_class}">
            <a href="${import}"><spring:message code="Import" /></a>
        </li>
    </c:if>
    <c:if test="${param.report}">
        <c:if test="${param.active == 'report'}"><c:set var="report_class" value="active" /></c:if>
        <spring:url var="report" value="/{property}/report">
            <spring:param name="property" value="${param.property}" />
        </spring:url>
        <li class="${report_class}">
            <a href="${report}"><spring:message code="Report" /></a>
        </li>
    </c:if>
    <c:choose>
        <c:when test="${ !empty requestScope[attribute].id }">
            <li class="pull-right"><span class="label">${requestScope[attribute].id}</span></li>
            <li class="pull-right ${show_class}">
                <spring:url var="show" value="/{property}/show/{id}">
                    <spring:param name="property" value="${param.property}" />
                    <spring:param name="id" value="${requestScope[attribute].id}" />
                </spring:url>
                <a href="${show}">
                    <spring:message code="Show" />
                </a>
            </li>
            <li class="pull-right ${edit_class}">
                <spring:url var="edit" value="/{property}/edit/{id}">
                    <spring:param name="property" value="${param.property}" />
                    <spring:param name="id" value="${requestScope[attribute].id}" />
                </spring:url>
                <a href="${edit}">
                    <spring:message code="Edit" />
                </a>
            </li>
            <c:if test="${param.submit}">
                <li class="pull-right">
                    <spring:url var="submit" value="/{property}/submit/{id}">
                        <spring:param name="property" value="${param.property}" />
                        <spring:param name="id" value="${requestScope[attribute].id}" />
                    </spring:url>
                    <c:choose>
                        <c:when test="${requestScope[attribute].submitted}">
                            <a class="text-error" href="${submit}"><spring:message code="Draw Back" /></a>
                        </c:when>
                        <c:when test="${!requestScope[attribute].submitted}">
                            <a class="text-success" href="${submit}"><spring:message code="Submit" /></a>
                        </c:when>
                    </c:choose>
                </li>
            </c:if>
        </c:when>
    </c:choose>
</ul>