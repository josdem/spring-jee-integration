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
      <div class="page-header">
        <h1>Create a new Sprint</h1>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">

        <form:form commandName="sprintCommand" method="post" action="${pageContext.request.contextPath}/sprint/save">
          
          <div class="form-group">
            <label class="control-label" for="name">Project</label>
            <form:select path="project.id" items="${projectList}" itemValue="id" itemLabel="codeName" class="form-control"/>
            <form:errors path="project" element="span"/>
          </div>
          
          <div class="form-group">
            <label class="control-label" for="name">Name</label>
            <form:input path="name" htmlEscape="true" placeholder="New project" class="form-control"/>
            <form:errors path="name" element="span"/>
          </div>
          
          <div class="form-group">
            <label class="control-label" for="description">Description</label>
            <form:textarea path="description" htmlEscape="true" class="form-control" rows="3"/>
            <form:errors path="description" element="span"/>
          </div>
          
          <div class="row">
            <div class="col-xs-6">
              <label class="control-label" for="startDate">Start date:</label>
              <form:input path="startDate" htmlEscape="true" placeholder="dd/mm/yyyy" class="form-control"/>
              <form:errors path="startDate" element="span"/>
            </div>
            <div class="col-xs-6">
              <label class="control-label" for="endDate">End date:</label>
              <form:input path="endDate" htmlEscape="true" placeholder="dd/mm/yyyy" class="form-control"/>
              <form:errors path="endDate" element="span"/>
            </div>
          </div>
          <hr>
          <button type="submit" class="btn btn-primary">Create a new project</button>
        </form:form>
      </div>
    </div>
  </div>

  <div class="container"></div>

  <jsp:include page="../_footer.jsp" />

</body>
</html>