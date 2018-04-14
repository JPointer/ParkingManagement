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
			<li><a href="/home">
				<sec:authorize access="hasRole('OPERATOR')">operator profile</sec:authorize>
				<sec:authorize access="hasAnyRole('REGULAR','VIP')">driver profile</sec:authorize>
			</a></li>
			<li><a href='<spring:url value="/list"/>'>
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
<div class="container">
	<div class="jumbotron">
		<h1 align="center">Driver Profile</h1>
		<table class="table">
			<tr>
				<td align="center"><h2>username:</h2></td>
				<td align="center"><h2>${user.username}</h2></td>
			</tr>
			<tr>
				<td align="center"><h2>first name:</h2></td>
				<td align="center"><h2>${user.firstName}</h2></td>
			</tr>
			<tr>
				<td align="center"><h2>second name:</h2></td>
				<td align="center"><h2>${user.secondName}</h2></td>
			</tr>
			<tr>
				<td align="center"><h2>status:</h2></td>
				<td align="center"><h2>${role}</h2></td>
			</tr>
			<sec:authorize access="hasAnyRole('REGULAR','VIP')">
				<tr>
					<td align="center"><h2>to pay:</h2></td>
					<td align="center"><h2>${user.bill}</h2></td>
				</tr>
			</sec:authorize>


		</table>
	</div>
</div>
</body>
</html>
