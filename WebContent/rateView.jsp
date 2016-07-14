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
<title>Rate</title>
</head>
<body class="body">

	<div class="container">
		<br> <br>
		<jsp:include page="NavBar.jsp" />

		<div style="background-color: white;" class="panel-body">
			<div class="row">
				<div class="col-sm-4">
					<div align="left">
						<img src="images/rt${restid}.jpeg" alt="${restName}" />
					</div>
					<div align="left">
						<c:out value="${address}"></c:out>
					</div>
					<div align="left">
						<c:out value="${zip}"></c:out>
					</div>
					<div align="left">
						<c:out value="${description}"></c:out>
					</div>
				</div>
				<div class="col-sm-6"></div>

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
					<form id="review" action="SubmitReview" method="post">
						<div id="accordion">
							<h3>Rate:</h3>
							<div style="overflow: hidden;">
								<!-- 		
						<table>
						<tr><td>Rating:<input type="text" id="rating" name="rating">  </td> </tr>
						<tr><td><textarea rows="3" cols="80" id="review" name="review"
										maxlength="450"></textarea> </td> </tr>
						<tr><td><div id="text"></div></td> </tr>
						<tr><td align="right"><input type="submit" id="submit" name="submit" value="Rate"></td> </tr>
						</table> -->
								<div>
									<div class="row">
										<div class="col-sm-4">
											<div style="background-color: silver;">

												<a href="#" id="start1"><span
													class="glyphicon glyphicon-star"></span></a>
												<a href="#" id="start2"><span
													class="glyphicon glyphicon-star"></span></a> <a href="#"
													id="start3"><span class="glyphicon glyphicon-star"></span></a>
												<a href="#" id="start4"><span
													class="glyphicon glyphicon-star"></span></a> <a href="#"
													id="start5"><span class="glyphicon glyphicon-star"></span></a>

											</div>
										</div>
										<div class="col-sm-6"></div>
										<div class="col-sm-2">
											<div align="right">
												Rating:<input type="text" id="rating" name="rating"
													style="width: 30px;">
											</div>
										</div>
									</div>


								</div>
								<br> <br>
								<div>
									<textarea rows="3" cols="80" id="review" name="review"
										maxlength="450"></textarea>
								</div>
								<div id="text"></div>
								<div align="right">
									<input type="submit" id="submit" name="submit" value="Rate">
								</div>
								<br> <br>


							</div>
							<h3>Reviews:</h3>
							<div>
								<c:set var="activity" scope="session" value="${restDetails}" />
								<c:if test="${activity != null}">
									<div class="panel panel-primary">
										<c:forEach var="activity" items="${activity}">
											<div class="panel-group">
												<div class="panel-heading">
													<table width="100%">
														<tr>
															<td align="left"><img
																src="${activity.subscriber.url}20" />${activity.subscriber.name}</td>
															<td align="right"><fmt:formatDate type="date"
																	value="${activity.daterate}" /></td>
														</tr>
													</table>

												</div>
												<div class="panel-body">
													<c:out value="${activity.review}"></c:out>
												</div>
											</div>
										</c:forEach>
									</div>
								</c:if>
								<c:if test="${activity == null}">
									<h2>No Reviews!!</h2>
								</c:if>
							</div>
						</div>
					</form>
				</div>
				<div class="col-sm-2"></div>
			</div>

		</div>
	</div>
</body>
</html>