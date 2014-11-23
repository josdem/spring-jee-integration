<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../_header.jsp" />
</head>
<body>

	<jsp:include page="../_menu.jsp" />

	<div class="container">
		<h1>Create a new UserStory for ${userStoryCommand.project.codeName}</h1>
	</div>
	<!-- /container -->

	<div class="container">
		<form:form commandName="userStoryCommand" method="post"
			action="${pageContext.request.contextPath}/project/${userStoryCommand.project.codeName}/userStory/save">
			<div class="form-group">
				<label for="description">Description</label>
				<form:textarea path="description" htmlEscape="true"
					class="form-control" rows="3" />
        <form:errors path="description" element="span"/>
			</div>
			<div class="form-group">
				<label for="priority">Priority</label>
				<form:input path="priority" htmlEscape="true" placeholder="A number..."
					class="form-control" />
        <form:errors path="priority" element="span"/>
			</div>
			<div class="form-group">
				<label for="effort">Effort</label>
				<form:input path="effort" htmlEscape="true"
					placeholder="A number between 1 and 5" class="form-control" />
        <form:errors path="effort" element="span"/>
			</div>
			<form:hidden path="project.id"/>
			<button type="submit" class="btn btn-default">Create a new
				user story</button>
		</form:form>
	</div>


	<jsp:include page="../_footer.jsp" />

</body>
</html>