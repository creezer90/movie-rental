<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link rel="icon" href="../../favicon.ico">



<sec:authorize var="loggedIn" access="isAuthenticated()" />
<sec:authorize var="isAdmin" access="hasAnyRole('ADMIN')" />
<sec:authentication var="user" property="principal" />

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				onclick="document.location.href='/movie-rental/'">Movie Rental</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">About<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">About project</a></li>
						<li role="separator" class="divider"></li>
						<li class="dropdown-header">About me</li>
						<li><a href="#">GitHub</a></li>
						<li><a href="#">Contact</a></li>
					</ul></li>
			</ul>
			<button type="button"
				class="btn btn-success navbar-btn pull-right left-margin"
				onclick="document.location.href='/movie-rental/registration'">Registration</button>

			<c:choose>
				<c:when test="${loggedIn}">
					<form action="${logoutUrl}" method="post">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<button type="submit"
							class="btn btn-warning navbar-btn pull-right">Log Out</button>
					</form>
				</c:when>
				<c:otherwise>
					<form action="${loginUrl}" method="post"
						class="navbar-form navbar-right" role="search">
						<div class="form-group">
							<input type="text" class="form-control" name="email" id="email"
								placeholder="Username">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" name="password"
								id="password" placeholder="Password">
						</div>
						<button type="submit" class="btn btn-warning">Log In</button>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</c:otherwise>
			</c:choose>


		</div>
	</div>
</nav>

