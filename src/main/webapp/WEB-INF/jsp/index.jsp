<%@ include file="/WEB-INF/jsp/header.jsp" %>

<sec:authorize access="isAuthenticated()">
<div class="well">
	<div class="row-fluid">
		<div class="span6">
			<h4><spring:message code="Today's Trips" text="Today's Trips"></spring:message></h4>
			<p><a href="#" class="badge badge-inverse">12</a></p>
		</div>
		<div class="span6">
			<h4><spring:message code="Today's Forwardings" text="Today's Forwardings"></spring:message></h4>
			<p><a href="#" class="badge badge-inverse">8</a></p>
		</div>
	</div>
</div>

<div class="page-header">
	<h1><spring:message code="Trip Report" text="Trip Report"></spring:message> <small><spring:message code="Trips"></spring:message></small></h1>
</div>
<spring:url var="chart1Url" value="/trip/report3" />
<img src="${chart1Url}" alt="">
<table class="table table-bordered">
	<thead>
		<tr>
			<th><spring:message code="Date"></spring:message></th>
			<th><spring:message code="Weight"></spring:message></th>
		</tr>
	</thead>
	<tbody>
	</tbody>
	<tfoot>
	</tfoot>
</table>
</sec:authorize>

<sec:authorize access="isAnonymous()">
<div id="myCarousel" class="carousel slide">
	<div class="carousel-inner">
		<div class="item active">
			<img src="http://dl.dropbox.com/u/36591931/toprakhareketi_com/forest1.jpg" alt="">
			<div class="container">
				<div class="carousel-caption">
					<h1>Example headline.</h1>
					<p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
					<a class="btn btn-large btn-primary" href="#">Sign up today</a>
				</div>
			</div>
		</div>
		<div class="item">
			<img src="http://dl.dropbox.com/u/36591931/toprakhareketi_com/forest2.jpg" alt="">
			<div class="container">
				<div class="carousel-caption">
					<h1>Another example headline.</h1>
					<p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
					<a class="btn btn-large btn-primary" href="#">Learn more</a>
				</div>
			</div>
		</div>
		<div class="item">
			<img src="http://dl.dropbox.com/u/36591931/toprakhareketi_com/forest3.jpg" alt="">
			<div class="container">
				<div class="carousel-caption">
					<h1>One more for good measure.</h1>
					<p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
					<a class="btn btn-large btn-primary" href="#">Browse gallery</a>
				</div>
			</div>
		</div>
	</div>
	<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
	<a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
</div>
</sec:authorize>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>