<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
   <title>List Schools</title>
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
   <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div id="wrapper">
		<div id="header">
			<h2>Student Records</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<!-- put new button: Add Student -->
            <c:url var="addStudentLink" value="/school/${schoolId}/add-student" />
            <input type="button" value="Add Student"
                   onclick="window.location.href='${addStudentLink}'; return false;"
                   class="add-button"
            />

			<!--  add our html table here -->

			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Standard</th>
					<th>Action</th>
				</tr>
				<!-- loop over and print our contact -->
				<c:forEach var="temp" items="${students}">

				    <c:url var="updateLink" value="/school/${schoolId}/update-student/${temp.id}">
				     <c:param name="studentId" value="${temp.id}"/>
				    </c:url>
				    <c:url var="deleteLink" value="/school/${schoolId}/delete-student/${temp.id}">
                    <c:param name="studentId" value="${temp.id}"/>
                    </c:url>
					<tr>
						<td> ${temp.firstName} </td>
						<td> ${temp.lastName} </td>
						<td> ${temp.standard}</td>
						<td> <a href="${updateLink}"> Update </a> |
						<a href="${deleteLink}" onclick="if(!(confirm('Are you sure want to delete this student?'))) return false"> Delete </a> </td>
					</tr>
				</c:forEach>
			</table>
			<div style="clear; both;"></div>
            		<p>
            			<a href="${pageContext.request.contextPath}/school/list">Back to List</a>
            		</p>
		</div>
	</div>
</body>
</html>