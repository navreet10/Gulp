<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="en_US" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="javascripts/home.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/style.css" />

<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet"
	href="https://jqueryui.com/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<style>
#draggable {
	width: 0px;
	height: 0px;
	padding: 0em;
}

#resizable {
	width: 150px;
	height: 150px;
	padding: 0.5em;
}

#resizable h3 {
	text-align: center;
	margin: 0;
}

#red, #green, #blue {
	float: left;
	clear: left;
	width: 300px;
	margin: 15px;
}

#swatch {
	width: 120px;
	height: 100px;
	margin-top: 18px;
	margin-left: 350px;
	background-image: none;
}

#red .ui-slider-range {
	background: #ef2929;
}

#red .ui-slider-handle {
	border-color: #ef2929;
}

#green .ui-slider-range {
	background: #8ae234;
}

#green .ui-slider-handle {
	border-color: #8ae234;
}

#blue .ui-slider-range {
	background: #729fcf;
}

#blue .ui-slider-handle {
	border-color: #729fcf;
}
</style>
<title>Home</title>
</head>
<body class="body">
	
		<div class="container">
			<br> <br>
			<jsp:include page="NavBar.jsp" />

			<div style="background-color: white;" class="panel-body">
				<div class="row">
					<div class="col-sm-2">
						<img src="${gBigUrl}" alt="${user.name}" />
					</div>
					<div class="col-sm-10"></div>

				</div>
				<c:set var="mes" scope="session" value="${message}" />
				<c:if test="${mes != null}">
					<div class="alert alert-success">
						<strong>${message}</strong> <span id="showSearchTerm"></span>
					</div>
				</c:if>
				<div class="row">
					<div class="col-sm-2"></div>
					<div class="col-sm-8">
					<br>
					<a id="showReviews">View Your Reviews:</a>
					<div id= "reviewsDiv">
						
							
								<c:set var="activity" scope="session" value="${activity}" />
								<c:if test="${activity != null}">
									<div class="panel-group">
										<c:forEach var="activity" items="${activity}">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<table width="100%">
														<tr>
															<td align="left">${activity.restaurant.name}</td>
															<td align="right"><fmt:formatDate type="date"
																						value="${activity.daterate}" /></td>
														</tr>
													</table>

												</div>
												<div class="panel-body" style="background-image: url('../images/rt${activity.restaurant.id}.jpeg');">
													<c:out value="${activity.review}"></c:out>
												</div>
											</div>
										</c:forEach>
									</div>
								</c:if>
								<c:if test="${activity == null}">
									<h2>You are not logged in!!</h2>
								</c:if>
							</div>
					<h3>AllRestaurants:</h3>
							<form id="target" action="Rate" method="post">
							<div>
							<div class="panel-group">
										<c:forEach var="rests" items="${restaurants}">
											<div class="panel panel-primary">
												<div class="panel-heading">
													<table width="100%">
														<tr>
															<td align="left">${rests.restaurant.name}</td>
															<td align="right">${rests.rating}</td>
														</tr>
													</table>

												</div>
												<div class="panel-body">
												<c:set var="imgId" value="${rests.restaurant.id}"></c:set>
												<table width="100%">
														<tr>
															<td align="left"><img src="images/rt${rests.restaurant.id}.jpeg" /></td>
															<td align="right">Number Of ratings:${rests.numRating}</td>
														</tr>
														<tr>
															<td align="left" colspan="2">${rests.restaurant.address}</td>
															
														</tr>
														<tr>
															<td align="left" colspan="2">${rests.restaurant.zip}</td>															
														</tr>
														<tr>
															<td align="left" colspan="2"> </td>															
														</tr>
														<tr> 
															<td colspan="2" align="left"><c:out value="${rests.restaurant.description}"></c:out></td>
														</tr>
													</table>													
												</div>
												<div class="panel-footer"> 
												<div align="right"> <input class='restRate' type="button" id='rest${rests.restaurant.id}' 
												name='rest${rests.restaurant.id}' value="Rate/View"></div>
												</div>
											</div>
										</c:forEach>
									</div>						
								
							</div>
							</form>
						
							<!-- <h3>Search:</h3>
							<form id="search" action="Search" method="post">
							<div>
								<div class="panel panel-primary">
									<div class="panel-heading">
										<div align="left">Filters</div>

									</div>
									<div class="panel-body">
										<div class="form-group">
											<label for="name">Name:</label> <input type="text"
												id="name" name="name" value="" class="form-control">
										</div>
										<div class="form-group">
											<label for="desc">Description:</label> <input type="text"
												id="desc" name="desc" value="" class="form-control">
										</div>
									</div>
									<div class="panel-footer">
										<div id="text"></div>
										<div align="right">
											<input type="submit" id="submit" name="submit"
												value="Search">
										</div>
									</div>
								</div>
							</div>
							</form> -->
							
							
						
					</div>
					<div class="col-sm-2"></div>
				</div>

			</div>
		</div>
</body>
</html>