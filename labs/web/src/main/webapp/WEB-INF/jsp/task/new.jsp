<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../_header.jsp" />
</head>
<body>

  <jsp:include page="../_menu.jsp" />

  <div class="container">
    <div class="row">
      <div class="container">
        <h1>${project.codeName}</h1>
        <h2>${userStory.description}</h2>
      </div>
    </div>
    
    <div class="row">
      <div class="col-md-6">
        
        <form:form commandName="task" method="post" action="${pageContext.request.contextPath}/project/${project.codeName}/userStories/${userStory.id}/task">
          <fieldset>
          <legend>Create a new task</legend>
          
          <div class="form-group">
            <label class="control-label" for="name">Status</label>
            <form:select path="status" items="${taskStatusList}" class="form-control"/>
          </div>

          <div class="form-group">
            <label class="control-label" for="description">Description</label>
            <form:textarea path="description" htmlEscape="true" class="form-control" rows="3"/>
            <form:errors path="description" element="span"/>
          </div>

          <hr>
          <button type="submit" class="btn btn-primary">Create a new task</button>
          <a href="${pageContext.request.contextPath}/project/${project.codeName}/userStories/${userStory.id}/saveTasks" class="btn btn-success">Confirm all tasks</a>
          </fieldset>
        </form:form>
        
      </div>
      <div class="col-md-6">
        <h3>Task list</h3>
        <ul>
          <c:forEach items="${tasks}" var="task">
            <li>
              <c:choose>
                <c:when test="${task.status == 'TODO'}">
                  <span class="label label-info">TODO</span>
                </c:when>
                <c:when test="${task.status == 'WIP'}">
                  <span class="label label-primary">WIP</span>
                </c:when>
                <c:when test="${task.status == 'DONE'}">
                  <span class="label label-success">DONE</span>
                </c:when>
              </c:choose>
               - ${task.description}
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
    
  </div>

  <jsp:include page="../_footer.jsp" />

</body>
</html>