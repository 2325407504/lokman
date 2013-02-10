<footer class="container">
	<div class="row">
		<div class="span12">
			<c:set var="value1" value="value1" />
			<c:set var="value2" value="value2" />
			<c:set var="value3" value="value3" />
			<spring:message code="label.copyright"
				arguments="${value1};${value2};${value3}" htmlEscape="false"
				argumentSeparator=";" />
		</div>
	</div>
</footer>
<script type='text/javascript'>
	//<![CDATA[ 
	$(window).load(function() {
		if (!Modernizr.inputtypes['date']) {
			//$('input[type=date]').datepicker({
			//beforeShowDay: checkBadDates
			//});
		}
	});//]]>  
	function checkBadDates(date) {
		var day = date.getDate();
		if (day > 15) {
			return {
				0 : false
			}
		} else {
			return {
				0 : true
			}
		}
	}
</script>

</body>
</html>