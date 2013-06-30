<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Productions" />
    <jsp:param name="property" value="production" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="show" />
</jsp:include>

<div class="row-fluid">
    <div class="span4">
        <aripd:description id="production">
            <aripd:descriptionitem label="Account" field="productionAttribute.account.client.fullname"></aripd:descriptionitem>
            <aripd:descriptionitem label="Shift" field="productionAttribute.shiftdate"></aripd:descriptionitem>
            <aripd:descriptionitem label="Weight" field="productionAttribute.feed"></aripd:descriptionitem>
            <aripd:descriptionitem label="Remark" field="productionAttribute.remark"></aripd:descriptionitem>
        </aripd:description>
    </div>
    <div class="span4">
        <table class="table">
            <caption><spring:message code="Machine Times" /></caption>
            <c:forEach items="${productionAttribute.machinetimes}" var="machinetime">
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
            <c:forEach items="${productionAttribute.compensations}" var="compensation">
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
        <aripd:datatables datasource="/bigbag/get/${productionAttribute.id}" id="bigbags" caption="Production Amounts">
            <aripd:column label="Product" field="product.name"/>
            <aripd:column label="Weight" field="weight"/>
        </aripd:datatables>
    </div>
</div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />