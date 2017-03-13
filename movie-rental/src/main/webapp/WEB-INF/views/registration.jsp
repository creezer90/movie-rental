<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<!-- Bootstrap core CSS -->
<link href="<c:url value="css/bootstrap.min.css"/>" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="<c:url value="css/ie10-viewport-bug-workaround.css"/>"
	rel="stylesheet">
<!-- Custom styles for this template -->
<link href="<c:url value="css/navbar-fixed-top.css"/>" rel="stylesheet">



<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>
	<jsp:include page="navBar.jsp"></jsp:include>



	<div class="container">
		<c:if test="${newUserReqistered }">
			<div class="alert alert-success">
				<p>New User Added</p>
			</div>
		</c:if>
		<div class="panel panel-default">
			<div class="panel-heading" style="font-weight: bold;">
				Registration
				<div class="panel-body"></div>
				<form:form action="/movie-rental/user/new" commandName="userForm"
					method="POST">
					<div class="form-group">
						<label for="name">Name: <form:errors
								style="color: red; font-weight: bold;" path="name" /></label>
						<form:input type="text" class="form-control" id="name" path="name" />
					</div>
					<div class="form-group">
						<label for="email">Email: <form:errors
								style="color: red; font-weight: bold;" path="email" /></label>
						<form:input type="text" class="form-control" id="email"
							path="email" />
					</div>
					<div class="form-group">
						<label for="password">Password: <form:errors
								style="color: red; font-weight: bold;" path="password" /></label>
						<form:input type="password" class="form-control" id="password"
							path="password" />
					</div>
					<div class="form-group">
						<label for="password">Repeat password:</label>
						<form:input type="password" class="form-control" id="password2"
							path="passwordRepeated" />
					</div>
					<button type="submit" class="btn btn-default">Zarejestruj</button>
				</form:form>
			</div>
		</div>
	</div>

</body>

<script src="<c:url value="js/jquery1.12.4.min.js" />"></script>

<script>
	window.jQuery
			|| document
					.write('<script src="js/vendor/jquery.min.js"><\/script>')
</script>
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->


<script src="<c:url value="js/bootstrap.min.js" />"></script>
<script src="<c:url value="js/editBook.js" />"></script>


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="js/ie10-viewport-bug-workaround.js" />"></script>

</html>