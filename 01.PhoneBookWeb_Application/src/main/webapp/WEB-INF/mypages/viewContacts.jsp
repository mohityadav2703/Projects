<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Contact Details</title>

	<!-- Datatable plugin CSS file -->
	<link rel="stylesheet" href= "https://cdn.datatables.net/1.10.22/css/jquery.dataTables.min.css" />

     <!-- jQuery library file -->
     <script type="text/javascript" 
     src="https://code.jquery.com/jquery-3.5.1.js">
     </script>

      <!-- Datatable plugin JS library file -->
     <script type="text/javascript" src="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js">
     </script>
     
	<script>
		$(document).ready(function() {
			$('#contactTbl').DataTable({
				paging : true,
				searching : true,
				layout : {
					bottomEnd : {
						paging : {
							firstLast : false
						}
					}
				}
			});
		});

		function deleteConfirm() {
			return confirm("Are you sure, you want to delete?");
		}
	</script>
	
</head>
<body>
	<h3>View Contacts Here</h3>

	<a href="addContact">+ Add New Contact</a>
	<table border="1" id="contactTbl">
		<thead>
			<tr>
				<th>S.NO</th>
				<th>Name</th>
				<th>Email</th>
				<th>Phone Number</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${contacts}" var="c" varStatus="index">
				<tr>
					<td>${index.count}</td>
					<td>${c.contactName}</td>
					<td>${c.contactEmail}</td>
					<td>${c.contactNumber}</td>
					<td><a href="editContact?cid=${c.contactId}">Edit</a> <a
						href="deleteContact?cid=${c.contactId}" onclick="deleteConfirm()">Delete</a>
					</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>