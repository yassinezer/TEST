<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Department</title>
	<link rel ="stylesheet" href = "/test/resources/style.css" />
	</head>
<body>
<h3>Department List</h3>
<table border="1" id ="table" >
	<tr>
	<th>No</th>
    <th>Name Department</th>
    <th>Medium Salary</th>
    </tr>
    <c:forEach var="department" items="${list}" varStatus="status">
        <tr class="alt">
    	<th>${department.departmentId}</th>
        <td>${department.departmentName}</td>
        <td>${listsm[status.index]} $</td>
    <td class="alt"> 
    <form action="/test/app/departments/${department.departmentId}/employees">
    <input id="view" type="submit" value="View List Employee"/>
    </form>
    </td>
    </tr>
    </c:forEach>
    </table>
</body>
</html>

	