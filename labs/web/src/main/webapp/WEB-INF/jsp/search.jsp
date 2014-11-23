<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="_header.jsp" />
</head>
<body>

  <jsp:include page="_menu.jsp" />

  <div class="container">
    <h1>Search in projects</h1>
  </div>

  <div class="container">
    <form action="${pageContext.request.contextPath}/search" method="post">
      <div class="form-group">
        <label for="name">User Story effort</label>
        <!-- Hey ma! Help me to set the selected value -->
        <select class="form-control" name="minValue">
          <option>1</option>
          <option>2</option>
          <option>3</option>
          <option>4</option>
          <option>5</option>
        </select>
        And
        <select class="form-control" name="maxValue">
          <option>1</option>
          <option>2</option>
          <option>3</option>
          <option>4</option>
          <option>5</option>
        </select>
      </div>
      <div class="form-group">
        <label for="name">Task description</label>
        <input name="taskDescription" placeholder="What did you write?" class="form-control" value="${taskDescription}">
      </div>
      <button type="submit" class="btn btn-primery">Search in projects</button>
    </form>
  </div>
  <hr>
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <h3>UserStories <small>${minValue} and ${maxValue}</small></h3>
        <ul>
        <c:forEach items="${userStories}" var="us">
          <li>${us.effort} - ${us.description}</li>
        </c:forEach>
        </ul>
      </div>
      <div class="col-md-6">
        <h3>Tasks <small>${taskDescription}</small></h3>
        <ul>
        <c:forEach items="${tasks}" var="task">
          <li>${task.description}</li>
        </c:forEach>
        </ul> 
      </div>
    </div>
  </div>

  <jsp:include page="_footer.jsp" />

</body>
</html>