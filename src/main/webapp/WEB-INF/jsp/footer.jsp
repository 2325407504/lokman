</div>
</div>
</div>
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

<aripd:flashattribute body="${message}" title="Mesaj"></aripd:flashattribute>

</body>
</html>