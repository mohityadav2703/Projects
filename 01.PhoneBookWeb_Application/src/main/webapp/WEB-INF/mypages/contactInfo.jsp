<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
<meta charset=UTF-8>
<title>Save Data</title>
</head>
<body>

	<h2>Save Contact</h2>

	<p>
		<span style="font-size: 18px; color: green; font-family: Arial;">${succMsg}</span>
	</p>
	<p>
		<span style="font-size: 18px; color: red; font-family: Arial;">${errMsg}</span>
	</p>
	<form:form action="/saveContact" method="post" modelAttribute="contact">
		<table border="1">
			<tr>
				<form:hidden path="contactId"/>
				
				<th>Contact Name :</th>
				<th>Contact Email :</th>
				<th>Contact Number :</th>
				<th>Action</th>
			</tr>

			<tr>
				<td><form:input path="contactName" /></td>
				<td><form:input path="contactEmail" /></td>
				<td><form:input type="text" path="contactNumber" /></td>
				<td><input type="reset" value="Reset" /></td>
				<td><input type="submit" value="Save" /></td>
			</tr>

		</table>
	</form:form>
	<a href="viewContacts"> view All Contacts</a>
</body>

</html>