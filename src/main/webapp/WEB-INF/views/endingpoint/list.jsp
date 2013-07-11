<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Ending Points" />
    <jsp:param name="property" value="endingpoint" />
    <jsp:param name="active" value="list" />
</jsp:include>

<aripd:datatables datasource="/endingpoint/get" id="endingpoints" dataUrlShow="/endingpoint/show" dataUrlEdit="/endingpoint/edit">
    <aripd:datatablescolumn label="Action" field="id"/>
    <aripd:datatablescolumn label="Code" field="code"/>
    <aripd:datatablescolumn label="Name" field="name"/>
</aripd:datatables>

<jsp:include page="/WEB-INF/views/footer.jsp" />