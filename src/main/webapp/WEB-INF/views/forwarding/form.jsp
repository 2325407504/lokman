<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Forwardings" />
    <jsp:param name="property" value="forwarding" />
    <jsp:param name="import" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="form" />
</jsp:include>

<spring:url var="save" value="/forwarding/save" />
<form:form id="forwarding" modelAttribute="forwardingAttribute" action="${save}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <form:hidden path="id" />
    <div class="row-fluid">
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:checkbox path="submitted" /> <spring:message code="message.record.submitted" />
                    <form:errors cssClass="text-error" path="submitted" />
                </div>
                <div class="control-group">
                    <form:label path="account"><spring:message code="Account" /></form:label>
                    <form:select multiple="false" path="account.id" items="${accounts}" itemLabel="employee.fullname" itemValue="id" />
                    <form:errors cssClass="text-error" path="account" />
                </div>
                <div class="control-group">
                    <form:label path="waybillNo"><spring:message code="Document No" /></form:label>
                    <form:input path="waybillNo" />
                    <form:errors cssClass="text-error" path="waybillNo" />
                </div>
                <div class="control-group">
                    <form:label path="driver"><spring:message code="Driver" /></form:label>
                    <spring:url var="getdrivernames" value="/driver/getnames" />
                    <form:input path="driver" data-provide="typeahead" data-items="4" data-link="${getdrivernames}" />
                    <form:errors cssClass="text-error" path="driver" />
                </div>
                <div class="control-group">
                    <form:label path="plate"><spring:message code="Plate" /></form:label>
                    <spring:url var="gettruckplates" value="/truck/getplates" />
                    <form:input path="plate" data-provide="typeahead" data-items="4" data-link="${gettruckplates}" />
                    <form:errors cssClass="text-error" path="plate" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <spring:message code="Starting Time" var="startingTime" />
                    <spring:message code="Ending Time" var="endingTime" />
                    <form:label path="startingTime">${startingTime} - ${endingTime}</form:label>
                    <form:input type="datetime" path="startingTime" cssClass="input-medium" placeholder="${startingTime}" />
                    <form:errors cssClass="text-error" path="startingTime" />
                    <form:input type="datetime" path="endingTime" cssClass="input-medium" placeholder="${endingTime}" />
                    <form:errors cssClass="text-error" path="endingTime" />
                </div>
                <div class="control-group">
                    <spring:message code="Starting Point" var="startingpoint" />
                    <spring:message code="Ending Point" var="endingpoint" />
                    <form:label path="startingpoint">${startingpoint} - ${endingpoint}</form:label>
                    <form:select path="startingpoint.id" multiple="false" items="${startingpoints}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="startingpoint" />
                    <form:select path="endingpoint.id" multiple="false" items="${endingpoints}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="endingpoint" />
                </div>
                <div class="control-group">
                    <spring:message code="Starting Km" var="startingKm" />
                    <spring:message code="Ending Km" var="endingKm" />
                    <form:label path="startingKm">${startingKm} - ${endingKm}</form:label>
                    <form:input type="number" path="startingKm" cssClass="input-medium" placeholder="${startingKm}" />
                    <form:errors cssClass="text-error" path="startingKm" />
                    <form:input type="number" path="endingKm" cssClass="input-medium" placeholder="${endingKm}" />
                    <form:errors cssClass="text-error" path="endingKm" />
                </div>
            </fieldset>
        </div>
        <div class="span4">
            <fieldset>
                <div class="control-group">
                    <form:label path="weight"><spring:message code="Weight" /></form:label>
                    <form:input path="weight" />
                    <form:errors cssClass="text-error" path="weight" />
                </div>
                <div class="control-group">
                    <form:label path="shippingCost"><spring:message code="Shipping Cost" /></form:label>
                    <form:input path="shippingCost" />
                    <form:errors cssClass="text-error" path="shippingCost" />
                </div>
                <div class="control-group">
                    <form:label path="subcontractor"><spring:message code="Subcontractor" /></form:label>
                    <form:select path="subcontractor.id" multiple="false" items="${subcontractors}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="subcontractor" />
                </div>
                <div class="control-group">
                    <form:label path="quota"><spring:message code="Quota" /></form:label>
                    <form:select path="quota.id" multiple="false" items="${quotas}" itemLabel="name" itemValue="id"/>
                    <form:errors cssClass="text-error" path="quota" />
                </div>
                <div class="control-group">
                    <form:label path="remark"><spring:message code="Remark" /></form:label>
                    <form:textarea path="remark" />
                    <form:errors cssClass="text-error" path="remark" />
                </div>
            </fieldset>
        </div>
    </div>
    <div class="row-fluid">
        <div class="span12">
            <div class="form-actions">
                <c:if test="${ !empty forwardingAttribute.id }">
                    <a class="btn btn-danger" href="javascript:$('#form-${forwardingAttribute.id}').submit();"><i class="icon-trash icon-white"></i> <spring:message code="Delete" /></a>
                </c:if>
                <button class="btn btn-primary" type="submit"><spring:message code="Save" /></button>
            </div>
        </div>
    </div>
</form:form>

<c:if test="${ !empty forwardingAttribute.id }">
    <spring:url var="delete" value="/forwarding/delete">
        <spring:param name="id" value="${forwardingAttribute.id}" />
    </spring:url>
    <form:form id="form-${forwardingAttribute.id}" modelAttribute="forwardingAttribute" action="${delete}" method="delete">
        <form:hidden path="id" />
    </form:form>
</c:if>

<c:if test="${forwardingAttribute.id != null}">
    <div class="row-fluid">
        <div class="span12">
            <spring:message code="Code" var="code" />
            <spring:message code="Company" var="company" />
            <spring:message code="County" var="county" />
            <spring:message code="City" var="city" />
            <spring:message code="Weight" var="weight" />

            <spring:url var="uatfSave" value="/uatf/save/${forwardingAttribute.id}" />
            <form:form modelAttribute="uatfAttribute" action="${uatfSave}" method="post" class="form-inline">
                <form:errors path="*" cssClass="alert alert-error" element="div" />
                <form:input path="code" cssClass="input-small" placeholder="${code}" />
                <form:input path="company" cssClass="input-small" placeholder="${company}" />
                <form:input path="county" cssClass="input-small" placeholder="${county}" />
                <form:input path="city" cssClass="input-small" placeholder="${city}" />
                <form:input path="weight" cssClass="input-mini" placeholder="${weight}" />
                <button class="btn" type="submit">
                    <i class="icon-ok"></i>
                </button>
            </form:form>

            <hr>

            <aripd:datatables datasource="/uatf/get/${forwardingAttribute.id}" id="uatfs" dataUrlDelete="/uatf/delete" actionColumn="5" caption="Uatf">
                <aripd:datatablescolumn label="Code" field="code"/>
                <aripd:datatablescolumn label="Company" field="company"/>
                <aripd:datatablescolumn label="County" field="county"/>
                <aripd:datatablescolumn label="City" field="city"/>
                <aripd:datatablescolumn label="Weight" field="weight"/>
                <aripd:datatablescolumn label="Action" field="id"/>
            </aripd:datatables>
        </div>
    </div>
</c:if>

<script type="text/javascript">
    function findAndSetKilometer(plate, startingKm, endingKm) {
        $.ajax({
            type: "get",
            url: "truck/getkilometerbyplate",
            data: {q: plate},
            beforeSend: function() {
            },
            success: function(response) {
                console.log(response);
                startingKm.val(response);
                startingKm.attr('min', response);
                startingKm.attr('max', response);
                endingKm.val(response);
                endingKm.attr('min', response);
            },
            error: function(response) {
                console.log(response);
            }
        });
    }

    $('[name=plate]')
            .attr('autocomplete', 'off')
            .typeahead()
            .on('keyup', function(ev) {

        ev.stopPropagation();
        ev.preventDefault();

        //filter out up/down, tab, enter, and escape keys
        if ($.inArray(ev.keyCode, [40, 38, 9, 13, 27]) === -1) {

            var self = $(this);
            var newval = self.val().replace(/\s/g, "").toUpperCase();
            self.val(newval);

            // We get the URL from input
            var urljson = self.attr("data-link");

            //set typeahead source to empty
            self.data('typeahead').source = [];

            //active used so we aren't triggering duplicate keyup events
            if (!self.data('active') && self.val().length > 0) {

                self.data('active', true);

                //Do data request.
                $.ajax({
                    url: urljson,
                    type: 'get',
                    data: {q: $(this).val()},
                    dataType: 'json',
                    success: function(data) {

                        console.log(data);

                        var form = self.closest('form');
                        var startingKm = form.find('[name=startingKm]');
                        var endingKm = form.find('[name=endingKm]');
                        var init = 0;
                        startingKm.val(init);
                        startingKm.attr('min', init);
                        startingKm.attr('max', init);
                        endingKm.val(init);
                        endingKm.attr('min', init);

                        if (data.length > 0) {
                            self.data('typeahead').updater = function(e) {
                                console.log(e);
                                findAndSetKilometer(e, startingKm, endingKm);
                                return e;
                            }
                        }

                        //set this to true when your callback executes
                        self.data('active', true);

                        //set your results into the typehead's source 
                        self.data('typeahead').source = data;

                        //trigger keyup on the typeahead to make it search
                        self.trigger('keyup');

                        //All done, set to false to prepare for the next remote query.
                        self.data('active', false);
                    }
                });
            }
        }
    });

    $('[name=driver]')
            .attr('autocomplete', 'off')
            .typeahead()
            .on('keyup', function(ev) {

        ev.stopPropagation();
        ev.preventDefault();

        //filter out up/down, tab, enter, and escape keys
        if ($.inArray(ev.keyCode, [40, 38, 9, 13, 27]) === -1) {

            var self = $(this);

            // We get the URL from input
            var urljson = self.attr("data-link");

            //set typeahead source to empty
            self.data('typeahead').source = [];

            //active used so we aren't triggering duplicate keyup events
            if (!self.data('active') && self.val().length > 0) {

                self.data('active', true);

                //Do data request.
                $.ajax({
                    url: urljson,
                    type: 'get',
                    data: {q: $(this).val()},
                    dataType: 'json',
                    success: function(data) {

                        //set this to true when your callback executes
                        self.data('active', true);

                        //set your results into the typehead's source 
                        self.data('typeahead').source = data;

                        //trigger keyup on the typeahead to make it search
                        self.trigger('keyup');

                        //All done, set to false to prepare for the next remote query.
                        self.data('active', false);
                    }
                });
            }
        }
    });
</script>

<script type="text/javascript">
    var startDateTextBox = $('#startingTime');
    var endDateTextBox = $('#endingTime');

    startDateTextBox.datetimepicker({
        maxDate: new Date(),
        onClose: function(dateText, inst) {
            if (endDateTextBox.val() != '') {
                var testStartDate = startDateTextBox.datetimepicker('getDate');
                var testEndDate = endDateTextBox.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    endDateTextBox.datetimepicker('setDate', testStartDate);
            }
            else {
                endDateTextBox.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            endDateTextBox.datetimepicker('option', 'minDate', startDateTextBox.datetimepicker('getDate'));
        }
    });
    endDateTextBox.datetimepicker({
        maxDate: new Date(),
        onClose: function(dateText, inst) {
            if (startDateTextBox.val() != '') {
                var testStartDate = startDateTextBox.datetimepicker('getDate');
                var testEndDate = endDateTextBox.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    startDateTextBox.datetimepicker('setDate', testEndDate);
            }
            else {
                startDateTextBox.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            startDateTextBox.datetimepicker('option', 'maxDate', endDateTextBox.datetimepicker('getDate'));
        }
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />