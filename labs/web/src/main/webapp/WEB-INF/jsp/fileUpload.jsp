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
    <h1>Upload an image for your project</h1>
  </div>

  <div class="container">
    <form action="${pageContext.request.contextPath}/fileUpload" method="post" enctype="multipart/form-data" >
      <input type="file" name="file" title="Search for a file to add">
      <hr>
      <button type="submit" class="btn btn-primery">Upload the file</button>
    </form>
  </div>
  <hr>
  
  <jsp:include page="_footer.jsp" />
  <script src="${pageContext.request.contextPath}/static/bootstrap-file-input/bootstrap.file-input.js"></script>
  <script>
  $(document).ready(function(){
    $('input[type=file]').bootstrapFileInput();
  });
  </script>

</body>
</html>