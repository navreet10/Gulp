<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

 <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="javascripts/home.js"></script>
<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/style.css" />
<title>Search Results</title>
</head>
<body class="body">
<form id = "target" action="NewsFeed" method="post">
<div class="container">
<jsp:include page="NavBar.jsp" />
<div class="alert alert-success">
                <strong>${messageSearch}</strong> <span id="showSearchTerm"></span>
            </div>
 <div class="row">
 
   
  <div class="col-sm-12"> <h2>Posts</h2>         
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Post date</th>
        <th>Post</th>
        <th>User</th>
      </tr>
    </thead>
    <tbody>
     <c:forEach var="post" items="${postsSearch}">
      <tr>
        <td><fmt:formatDate type="date" 
            value="${post.postdate}" /></td>
        <td><c:out value="${post.posttext}"></c:out></td>
        <td><c:out value="${post.bhuser.username}"></c:out></td>
      </tr>
      </c:forEach>
      
    </tbody>
  </table></div>											
</div>

</div>
</form>
</body>
</html>