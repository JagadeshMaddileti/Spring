<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
   <title>Student Form</title>
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
			<h2>Students</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Student</h3>

   <c:choose>
        <c:when test="${student.id == null}">
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/school/${schoolId}/add-student"/>
        </c:when>
        <c:otherwise>
            <c:set var="actionUrl" value="${pageContext.request.contextPath}/school/${schoolId}/update-student/${student.id}"/>
        </c:otherwise>
    </c:choose>
    <form:form action="${actionUrl}" modelAttribute="student" method="POST">            <!-- need to associate this data with contact id -->
        	<form:hidden path="id" />
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><form:input path="firstName" />
						<form:errors path="firstName" cssClass="error" /></td>

					</tr>
					<tr>
						<td><label>Last name:</label></td>
						<td><form:input path="lastName" />
						 <form:errors path="lastName" cssClass="error" /></td>

					</tr>
					<tr>
                    	<td><label>Standard:</label></td>
                    	<td><form:input path="standard" />
                    	 <form:errors path="standard" cssClass="error" /></td>

                    </tr>

					<tr>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</body>
</html>