<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <jsp:include page="_header.jsp"/>
  </head>
  <body>

    <jsp:include page="_menu.jsp"/>

    <div class="container">
      <!-- Main component for a primary marketing message or call to action -->
      <div class="jumbotron">
        <h1>Wops, this feature is new!!!</h1>
        <p>${message}</p>
      </div>
    </div> <!-- /container -->

  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="alert alert-danger">
          <c:forEach items="${ex.stackTrace}" var="trace">
            ${trace}
          </c:forEach>
        </div>
      </div>
    </div>
  </div>

  <div class="container">
      <h2>Exercises</h2>
      <ul>
        <li><a href="${pageContext.request.contextPath}/project">ProjectController</a></li>
        <li><a href="${pageContext.request.contextPath}/explorer">ExplorerController</a></li>
        <li><a href="${pageContext.request.contextPath}/search">SearchController</a></li>
        <li><a href="${pageContext.request.contextPath}/sprint/new">SprintController</a></li>
        <li><a href="${pageContext.request.contextPath}/fileUpload">FileUploadController</a></li>
      </ul>
    </div>

    <jsp:include page="_footer.jsp"/>
    
  </body>
</html>