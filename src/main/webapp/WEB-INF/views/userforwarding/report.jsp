<%@include file="/WEB-INF/views/includes.jsp" %>

<jsp:include page="/WEB-INF/views/header.jsp">
    <jsp:param name="title" value="" />
</jsp:include>

<jsp:include page="/WEB-INF/views/subnav.jsp" >
    <jsp:param name="title" value="Forwardings" />
    <jsp:param name="property" value="userforwarding" />
    <jsp:param name="new" value="true" />
    <jsp:param name="report" value="true" />
    <jsp:param name="submit" value="true" />
    <jsp:param name="active" value="report" />
</jsp:include>

<spring:message code="Starting Time" var="startingTime" />
<spring:message code="Ending Time" var="endingTime" />
<spring:message code="Plate" var="plate" />
<spring:message code="Starting Point" var="startingpoint" />
<spring:message code="Ending Point" var="endingpoint" />
<spring:message code="All" var="all" />

<p class="lead"><spring:message code="Forwarding" /></p>
<spring:url var="report" value="/userforwarding/report" />
<form:form modelAttribute="userforwardingFilterByIntervalForm" action="${report}" method="post">
    <form:errors path="*" cssClass="alert alert-error" element="div" />
    <div class="row-fluid">
        <div class="span12">
            <div class="control-group">
                <form:label path="startingTime">${startingTime} - ${endingTime}</form:label>
                <form:input id="start1" path="startingTime" cssClass="input-medium" placeholder="${startingTime}" />
                <form:errors cssClass="text-error" path="startingTime" />
                <form:input id="end1" path="endingTime" cssClass="input-medium" placeholder="${endingTime}" />
                <form:errors cssClass="text-error" path="endingTime" />
            </div>
            <div class="control-group">
                <form:label path="plate">${plate}</form:label>
                <spring:url var="getTruckPlates" value="/userforwarding/getTruckPlates" />
                <form:input path="plate" placeholder="${plate}" data-provide="typeahead" data-items="4" data-link="${getTruckPlates}" cssClass="input-medium" />
            </div>
            <div class="control-group">
                <form:label path="startingpoint">${startingpoint} - ${endingpoint}</form:label>
                <form:select multiple="false" path="startingpoint.id" cssClass="input-medium">
                    <form:option value="" label="--- ${all}" />
                    <form:options items="${startingpoints}" itemLabel="name" itemValue="id" />
                </form:select>
                <form:select multiple="false" path="endingpoint.id" cssClass="input-medium">
                    <form:option value="" label="--- ${all}" />
                    <form:options items="${endingpoints}" itemLabel="name" itemValue="id" />
                </form:select>
            </div>
            <div class="control-group">
                <button class="btn" type="submit">
                    <i class="icon-download-alt"></i> <spring:message code="Download" />
                </button>
            </div>
        </div>
    </div>
</form:form>

<script type="text/javascript">
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
    var start1 = $('#start1');
    var end1 = $('#end1');

    start1.datetimepicker({
        maxDate: new Date(),
        onClose: function(dateText, inst) {
            if (end1.val() != '') {
                var testStartDate = start1.datetimepicker('getDate');
                var testEndDate = end1.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    end1.datetimepicker('setDate', testStartDate);
            }
            else {
                end1.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            end1.datetimepicker('option', 'minDate', start1.datetimepicker('getDate'));
        }
    });
    end1.datetimepicker({
        maxDate: new Date(),
        onClose: function(dateText, inst) {
            if (start1.val() != '') {
                var testStartDate = start1.datetimepicker('getDate');
                var testEndDate = end1.datetimepicker('getDate');
                if (testStartDate > testEndDate)
                    start1.datetimepicker('setDate', testEndDate);
            }
            else {
                start1.val(dateText);
            }
        },
        onSelect: function(selectedDateTime) {
            start1.datetimepicker('option', 'maxDate', end1.datetimepicker('getDate'));
        }
    });
</script>

<jsp:include page="/WEB-INF/views/footer.jsp" />