		<footer class="container"><div class="span12"><spring:message code="label.copyright"></spring:message></div></footer>
		<script type="text/javascript" src="//code.jquery.com/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="//code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
		<script type="text/javascript" src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.1/js/bootstrap.min.js"></script>
		<script>
		$(function() {
			$("#datepicker").datepicker({
				beforeShowDay: checkBadDates
			});
		});
		
		function checkBadDates(date) {
			var day = date.getDate();
			if (day > 15) {
				return {0: false}
			} else {
				return {0: true}
			}
		}
		</script>
	</body>
</html>