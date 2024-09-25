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
			<h2>School Records</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<!-- put new button: Add Customer -->
			<input type="button" value="Add School"
				   onclick="window.location.href='showFormForAdd'; return false;"
				   class="add-button"
			/>

			<!--  add our html table here -->

			<table>
				<tr>
					<th>Name</th>
					<th>Location</th>
					<th>Students</th>
					<th>Action</th>
				</tr>
				<!-- loop over and print our contact -->
				<c:forEach var="temp" items="${schools}">

				    <c:url var="updateLink" value="/school/showFormForUpdate">
				     <c:param name="schoolId" value="${temp.id}"/>
				    </c:url>
				    <c:url var="deleteLink" value="/school/delete">
                    <c:param name="schoolId" value="${temp.id}"/>
                    </c:url>
					<tr>
						<td> ${temp.name} </td>
						<td> ${temp.location} </td>
						<td><a href="${temp.id}/students"
                               class="btn btn-info">View</a></td>
						<td> <a href="${updateLink}"> Update </a> |
						<a href="${deleteLink}" onclick="if(!(confirm('Are you sure want to delete this schoolt?'))) return false"> Delete </a> </td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>