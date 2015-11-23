<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link rel ="stylesheet" href = "/test/resources/style.css" />
	<title>Employer</title>
</head>
<body>
        <div align="left">
            <h3>Employer List of the Department No : ${departmentId}</h3>
            <table border="1" id ="table"  >
            	<tr>
                <th>No</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Salary</th>
                </tr>
                <c:forEach var="employee" items="${list}">
                <tr class="alt">
                    <th>${employee.employeeId}</th>
                    <td>${employee.firstname}</td>
                    <td>${employee.lastname}</td>
                    <td>${employee.salary} $</td>
<td class="alt"> <form action="/test/app/departments/${departmentId}/employees/${employee.employeeId}/edit">
<input  type="submit" value="edit" id="submit"></form></td>
                </tr> 
                </c:forEach>             
            </table>
            <form action="/test/app/departments/${departmentId}/employees/add" style="position:relative;left:0%;">
			<input  type="submit" value="add" id="submit"/>
			</form>
        </div>
</body>
</html>
