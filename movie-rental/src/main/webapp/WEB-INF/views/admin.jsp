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
<link href="<c:url value="css/calendar.css"/>" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>










</body>

<script src="<c:url value="js/jquery1.12.4.min.js" />"></script>

<script>
	window.jQuery
			|| document
					.write('_$tag__________________________________$tag_____')
</script>
<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->


<script src="<c:url value="js/bootstrap.min.js" />"></script>
<script src="<c:url value="js/editBook.js" />"></script>


<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<c:url value="js/ie10-viewport-bug-workaround.js" />"></script>

</html>