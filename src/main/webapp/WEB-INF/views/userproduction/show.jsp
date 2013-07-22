<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Productions" />
    <jsp:param name="property" value="userproduction" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="production">
            <aripd:descriptionitem label="Shift" field="userproductionAttribute.shiftdate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Feed" field="userproductionAttribute.feed"></aripd:descriptionitem>
            <aripd:descriptionitem label="Remark" field="userproductionAttribute.remark"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span4">
        <table class="table">
            <caption><spring:message code="Machine Times" /></caption>
            <c:forEach items="${userproductionAttribute.machinetimes}" var="machinetime">
                <tr>
                    <td>${machinetime.machine.name}</td>
                    <td>${machinetime.val}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="span4">
        <table class="table">
            <caption><spring:message code="Compensations" /></caption>
            <c:forEach items="${userproductionAttribute.compensations}" var="compensation">
                <tr>
                    <td>${compensation.electricmeter.name}</td>
                    <td>${compensation.val}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<div class="row-fluid">
    <div class="span12">
        <aripd:datatables datasource="/bigbag/get/${userproductionAttribute.id}" id="bigbags" caption="Production Amounts">
            <aripd:datatablescolumn label="Product" field="product.name"/>
            <aripd:datatablescolumn label="Weight" field="weight"/>
        </aripd:datatables>
    </div>
</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />