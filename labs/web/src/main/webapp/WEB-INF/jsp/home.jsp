<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
        <h1><spring:message code="welcome"/> </h1>
        <p>You're learning SpringMVC 4 and the ways to configure it, so, you must use you actual acknowledge about Spring.</p>
        <p><spring:theme code='css' /></p>
      </div>
    </div> <!-- /container -->
    
    <div class="container">
      <h2>Exercises</h2>
      <ul>
        <li><a href="${pageContext.request.contextPath}/project">ProjectController</a></li>
        <li><a href="${pageContext.request.contextPath}/explorer">ExplorerController</a></li>
        <li><a href="${pageContext.request.contextPath}/search">SearchController</a></li>
        <li><a href="${pageContext.request.contextPath}/sprint/new">SprintController</a></li>
        <li><a href="${pageContext.request.contextPath}/fileUpload">FileUploadController</a></li>
        <li><a href="${pageContext.request.contextPath}/error">ErrorController</a></li>
      </ul>
    </div>

    <jsp:include page="_footer.jsp"/>
    
  </body>
</html>