<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="library.css">
</head>
<body>
<div id="header">
<a href = 'Logout' class = 'remove'>Logout</a>
<p id="name">Add Items</p>
</div>
<form action= 'AddItems' method = 'post'>
<table border =1 >
<tr><td>Type:</td><td><select name='type'><c:forEach items="${types}" var="entry"><option>${entry}</option></c:forEach></select></tr>
<tr><td>Name:</td><td><input type = 'text' name='name' placeholder="Name"/></td></tr>
<tr><td>Additional Info:</td><td><input type = 'text' name='info' placeholder="Additional Info"/></td></tr>
<tr><td># of copies:</td><td><input type = 'text' name='copies' placeholder="Number of copies"/></td></tr>
<tr><td colspan='2'><button>Add</button></td></tr>
</table></form>
</body>
</html>