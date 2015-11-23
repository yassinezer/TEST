<html>
<head>
	<title>Edit Employee</title>
<link rel ="stylesheet" href = "/test/resources/style.css" />

<script type="text/javascript">
$(function() {
    $("#form").validate(); 	
});
</script>
</head>
<body>
	<h3>Edit Employee No : ${employeeId} for the Department  No : ${departmentId} </h3>
	<form action="/test/app/departments/${departmentId}/employees/${employeeId}/edit" method="post" id="form" >
		<table id="table2">
		<tr><td class="alt2"> First Name : </td> <td>   <input class="alt3" type="text" placeholder="FIRST NAME"  name="firstname"  required="required"  pattern="[a-zA-Z]+"
title="the family name must be a string of characters" />   </td> </tr>
		<tr><td class="alt2"> Last Name : </td> <td>   <input class="alt3" type="text" placeholder="LAST NAME"  name="lastname"  required="required" pattern="[a-zA-Z]+"
title="the last name must be a string of characters" />   </td> </tr>
		<tr><td class="alt2"> Salary  : </td> <td>   <input class="alt3" type="text" name="salary" placeholder="SALARY $"  required="required"  pattern="[0-9]+[.]{0,1}[0-9]+"
title="the salary must be a number or a float"  />   </td> </tr>
		</table>
		<input type="submit" value="Confirm" id="submit" />
		<input type="reset" value="Reset" id="reset" />
	</form>
</body>
</html>

