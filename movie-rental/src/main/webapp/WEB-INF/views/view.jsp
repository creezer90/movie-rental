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
<script type="text/javascript">
	function setCursor(element, cursor) {
		element.style.cursor = cursor;
	}
	function orderBy(field) {
		var lsb = document.getElementById("movieContextLastSortBy");
		var lsd = document.getElementById("movieContextSortDirection");
		if (lsb.value==field && lsd.value == "ASC"){
			lsd.value = "DESC";
		}else {
			lsd.value = "ASC";
		}
		
		$("#movieContextSortBy").val(field);
		$("#movieContextForm").submit();
	}
	function usePageSize(size) {
		$("#movieContextPageSize").val(size);
		$("#movieContextPageNumber").val(0);
		$("#movieContextForm").submit();
	}
	function usePageNumber(number){
		$("#movieContextPageNumber").val(number);
		$("#movieContextForm").submit();
	}
	function forwardMovie(id){
		var createdForm = document.createElement('form'); 
		createdForm.setAttribute("action", "rentMovieView"); 
		createdForm.setAttribute("method", "get"); 
		
		var idInput = document.createElement('input');
		idInput.setAttribute("type", "hidden");
		idInput.setAttribute("name", "id");
		idInput.setAttribute("value", id);
		
		createdForm.appendChild(idInput);
		document.getElementById('div_forms').appendChild(createdForm);
		createdForm.submit();
	}
	
</script>
</head>
<body>
	<jsp:include page="navigation.jsp"></jsp:include>


	<div class="container">


		<table class="table table-condensed">
			<thead>
				<tr>
					<th class="col-sm-1" onmouseover="setCursor(this, 'pointer')"
						onmouseout="setCursor(this, 'default')"
						onclick="orderBy('id_movie')">Movie ID <span
						class="glyphicon glyphicon-chevron-up"></span>
					</th>
					<th class="col-sm-4" onmouseover="setCursor(this, 'pointer')"
						onmouseout="setCursor(this, 'default')" onclick="orderBy('title')">Title</th>
					<th class="col-sm-2" onmouseover="setCursor(this, 'pointer')"
						onmouseout="setCursor(this, 'default')"
						onclick="orderBy('country')">Country</th>
					<th class="col-sm-2" onmouseover="setCursor(this, 'pointer')"
						onmouseout="setCursor(this, 'default')" onclick="orderBy('genre')">Genre</th>
					<th class="col-sm-1" onmouseover="setCursor(this, 'pointer')"
						onmouseout="setCursor(this, 'default')" onclick="orderBy('price')">Price</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${movieContext.result}" var="m">


					<tr id='${m.id}' onclick="forwardMovie('${m.id}')"
						<c:if test="${m.is_available==false}">bgcolor="#ff5050"</c:if>>
						<td>${m.id_movie}</td>
						<td>${m.title}</td>
						<td>${m.country}</td>
						<td>${m.genre}</td>
						<td>${m.price}</td>
					</tr>
				</c:forEach>


			</tbody>

			<td colspan="4" align="center">
				<ul class="pagination pagination-sm">
					<c:forEach begin="1" end="${movieContext.totalPages}"
						varStatus="status">
						<li
							<c:if test="${movieContext.pageNumber == status.index-1}">class="active"</c:if>><a
							href="#" onclick="usePageNumber(${status.index-1})">${status.index}</a></li>
					</c:forEach>
				</ul>
			</td>
			<td colspan="1" align="right">Pokaż w tabeli:<select
				class="form-control" style="width: 75px;" id="pageSizeQuestionsPick"
				onchange="usePageSize(this.value)">
					<option
						<c:if test="${movieContext.pageSize == 1}">selected="selected"</c:if>>1</option>
					<option
						<c:if test="${movieContext.pageSize == 5}">selected="selected"</c:if>>5</option>
					<option
						<c:if test="${movieContext.pageSize == 10}">selected="selected"</c:if>>10</option>
					<option
						<c:if test="${movieContext.pageSize == 20}">selected="selected"</c:if>>20</option>
			</select>
			</td>
		</table>
	</div>
	<div class="panel panel-default" style="width: 50%; margin: auto;">
		<div class="panel-heading" style="font-weight: bold;">Search
			form</div>
		<div class="panel-body" id="div_forms">
			<form action="/movie-rental/" id="movieContextForm">


				<div class="form-group">
					<label for="input_movie_id">Movie id:</label> <input type="text"
						name="id_movie" value="${searchCriteria.id_movie}"
						class="form-control" id="movie_id" />
				</div>
				<div class="form-group">
					<label for="input_title">Title:</label> <input type="text"
						name="title" value="${searchCriteria.title}" class="form-control"
						id="input_title" />
				</div>
				<div class="form-group">
					<label for="input_country">Country:</label> <input type="text"
						name="country" value="${searchCriteria.country}"
						class="form-control" id="input_country" />
				</div>
				<div class="form-group">
					<label for="input_genre">Genre:</label> <input type="text"
						name="genre" value="${searchCriteria.genre}" class="form-control"
						id="input_genre" />
				</div>
				<div class="form-group">
					<label for="input_minPrice">Min price:</label> <input type="text"
						name="minPrice" value="${searchCriteria.minPrice}"
						class="form-control" id="input_minPrice" />
				</div>
				<div class="form-group">
					<label for="input_maxPrice">Max price:</label> <input type="text"
						name="maxPrice" value="${searchCriteria.maxPrice}"
						class="form-control" id="input_minPrice" />
				</div>
				<button type="submit" class="btn btn-default">Search</button>

				<input type="hidden" id="movieContextSortBy" name="sortBy"
					value="${movieContext.sortBy}" /> <input type="hidden"
					id="movieContextLastSortBy" name="lastSortBy"
					value="${movieContext.lastSortBy}" /> <input type="hidden"
					id="movieContextPageSize" name="pageSize"
					value="${movieContext.pageSize}" /> <input type="hidden"
					id="movieContextPageNumber" name="pageNumber"
					value="${movieContext.pageNumber}" /> <input type="hidden"
					id="movieContextSortDirection" name="sortDirection"
					value="${movieContext.sortDirection}" />
			</form>
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