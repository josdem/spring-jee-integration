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
        <h1>Create a new project</h1>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">

        <form:form commandName="project" method="post" action="${pageContext.request.contextPath}/saveProject">
          
          <div class="form-group">
            <label class="control-label" for="name">Name</label>
            <form:input path="name" htmlEscape="true" placeholder="New project" class="form-control"/>
            <span class="control-label">${status.errorCode}</span>
            <form:errors path="name" element="span"/>
          </div>
          
          
          <div class="form-group">
            <label class="control-label" for="codeName">Code Name</label>
            <form:input path="codeName" htmlEscape="true" placeholder="PROJECT-CODE" class="form-control"/>
            <span class="control-label">${status.errorCode}</span>
            <form:errors path="codeName" element="span"/>
          </div>
          
          <div class="form-group">
            <label class="control-label" for="description">Description</label>
            <form:textarea path="description" htmlEscape="true" class="form-control" rows="3"/>
            <span class="control-label">${status.errorCode}</span>
            <form:errors path="description" element="span"/>
          </div>
            
          <button type="submit" class="btn btn-default">Create a new project</button>
        </form:form>

      </div>
    </div>
  </div>



  <div class="container"></div>

  <jsp:include page="../_footer.jsp" />

</body>
</html>