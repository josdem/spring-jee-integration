<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../_header.jsp" />
</head>
<body>

  <jsp:include page="../_menu.jsp" />

  <div class="container">
    <h1>UserStories of ${project.codeName}</h1>
  </div>

  <div class="container">
    <ul>
      <c:forEach items="${userStories}" var="us">
        <li><a
          href="${pageContext.request.contextPath}/project/${project.codeName}/userStories/${us.id}/task">${us.description}</a>
        </li>
      </c:forEach>
    </ul>
    <hr>
    <a
      href="${pageContext.request.contextPath}/project/${project.codeName}/userStory/new"
      class="btn btn-primary"> Create a new user story </a>
    <a
      href="${pageContext.request.contextPath}/project/${project.codeName}/userStory/exportToExcel"
      class="btn btn-default"> Export to excel </a>  
    <a
      href="${pageContext.request.contextPath}/project/${project.codeName}/userStory/exportToPdf"
      class="btn btn-default"> Export to PDF </a>
  </div>


  <jsp:include page="../_footer.jsp" />

</body>
</html>