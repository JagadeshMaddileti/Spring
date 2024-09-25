<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
   <title>School Form</title>
    <style>
          .error {
              color: red;
              font-size: 0.875em;
          }
      </style>
   <link type="text/css"
   		  rel="stylesheet"
   		  href="${pageContext.request.contextPath}/resources/css/form-styles.css">

</head>
<body>
<div id="wrapper">
		<div id="header">
			<h2>Schools</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save School</h3>

		<form:form action="saveSchool" modelAttribute="school" method="POST">
            <!-- need to associate this data with contact id -->
        	<form:hidden path="id" />
			<table>
				<tbody>
					<tr>
						<td><label>Name:</label></td>
						<td><form:input path="name" />
						<form:errors path="name" cssClass="error" /></td>

					</tr>

					<tr>
						<td><label>Location:</label></td>
						<td><form:input path="location" />
						  <form:errors path="location" cssClass="error" /></td>

					</tr>

					<tr>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		<div style="clear; both;"></div>
		<p>
			<a href="${pageContext.request.contextPath}/school/list">Back to List</a>
		</p>
	</div>
</body>
</html>