<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Productions" />
    <jsp:param name="property" value="userproduction" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/userproduction/get" id="productions" dataUrlShow="/userproduction/show" dataUrlEdit="/userproduction/edit" sortingColumn="2" sortingDirection="desc">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Shift" field="shiftdate"/>
    <aripd:datatablescolumn label="Feed" field="feed"/>
    <aripd:datatablescolumn label="Remark" field="remark"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />