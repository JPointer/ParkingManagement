<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href=<c:url value="/resources/css/login.css"/> rel="stylesheet" type="text/css">
	<title>Parking Manager</title>

</head>
<body>
<div class="container">
	<div class="jumbotron">
		<div class="page-header loginHeader">
			<h2>Parking Manager</h2>
		</div>
		<form action='<c:url value="/login"/>' method="POST">
			<div class="inputBlock">
				<div class="form-group">
					<label class="control-label col-sm-2" for="username">Username:</label>
					<div class="col-sm-12">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
							<input type="text" class="form-control" name="username" id="username" placeholder="Enter username">
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="pass">Password:</label>
					<div class="col-sm-12">
						<div class="input-group">
							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
							<input type="password" class="form-control" name="password" id="pass" placeholder="Enter password">
						</div>
					</div>
				</div>
			</div>
			<div class="buttonBlock">
				<input type="submit" class="btn btn-success btn-block" value="Login"/>
				<a href='<spring:url value="/create"/>' class="btn btn-primary btn-block"
				   role="button">Create Account</a>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			</div>
		</form>
	</div>
</div>
</body>
</html>
