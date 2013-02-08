		<footer class="container">
			<div class="row">
				<div class="span12">
					<spring:message code="label.copyright"></spring:message>
				</div>
			</div>
		</footer>
		<script type="text/javascript" src="/gelibolu/resources/js/master.js"></script>
		<script type='text/javascript'>//<![CDATA[ 
		$(window).load(function(){
			if (!Modernizr.inputtypes['date']) {
				//$('input[type=date]').datepicker({
					//beforeShowDay: checkBadDates
				//});
			}
		});//]]>  
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