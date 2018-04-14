<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href=<c:url value="/resources/css/home.css"/> rel="stylesheet" type="text/css">
	<title>Parking Manager</title>
</head>
<body>
<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#"><span class="logo">Parking Manager</span></a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href='<spring:url value="/home"/>'>
				<sec:authorize access="hasRole('OPERATOR')">operator profile</sec:authorize>
				<sec:authorize access="hasAnyRole('REGULAR','VIP')">driver profile</sec:authorize>
			</a></li>
			<li><a href="/list">
				<sec:authorize access="hasRole('OPERATOR')">visits list</sec:authorize>
				<sec:authorize access="hasAnyRole('REGULAR','VIP')">parking list</sec:authorize>
			</a></li>
			<li><a href='<spring:url value="/rates"/>'>parking rates</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<sec:authorize access="isAuthenticated()">
				<li><a href='<spring:url value="/login"/>'><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
			</sec:authorize>
		</ul>
	</div>
</nav>
<sec:authorize access="hasRole('OPERATOR')">
	<div class="container">
		<div class="jumbotron">
			<h1 align="center">Visits List</h1>
			<table class="table table-striped">
				<thead>
				<tr>
					<th>username</th>
					<th>parking</th>
					<th>begin time</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${visits}" var="visit">
					<tr>
						<td>${visit.username}</td>
						<td>${visit.city} ${visit.street} ${visit.number}</td>
						<td>${visit.begin}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</sec:authorize>
<sec:authorize access="hasAnyRole('REGULAR', 'VIP')">
	<div class="container">
		<div class="jumbotron">
			<h1 align="center">Parking List</h1>
			<table class="table table-striped">
				<thead>
				<tr>
					<th>City</th>
					<th>Street</th>
					<th>Number</th>
					<th>Rate ID</th>
					<th></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${parkings}" var="parking">
					<tr>
						<td>${parking.city}</td>
						<td>${parking.street}</td>
						<td>${parking.number}</td>
						<td>${parking.rate}</td>
						<td>
							<c:choose>
								<c:when test="${parking.occupied}">
									<form action='<spring:url value="/stop"/>' method='POST'>
										<input type="submit" class="btn btn-danger" value="Stop"/>
										<input type='hidden' id='parkingStop' name='parkingStop' value='${parking.idParking}'/>
									</form>
								</c:when>
								<c:otherwise>
									<form action='<spring:url value="/start"/>' method='POST'>
										<input type="submit" class="btn btn-success" value="Start"/>
										<input type='hidden' id='parkingStart' name='parkingStart' value='${parking.idParking}'/>
									</form>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</sec:authorize>
</body>
</html>
