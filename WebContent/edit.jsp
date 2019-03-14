<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Items</title>
<link rel="stylesheet" href="library.css">
</head>
<body>
<div id="header">
<a href = 'Logout' class="remove">Logout</a>
<p id="name">Edit Items</p>
</div>
<form action= "EditItems?id=${entries.id}" method = 'post'>
<table border =1>
<tr><td>Id:</td><td>${entries.id}</td></tr>
<tr><td>Type:</td>
<td><select name='type'>
<option>${entries.type}</option>
<c:forEach items="${types}" var="entry">
<c:if test ="${entry ne entries.type}"><option>${entry}</option></c:if>
</c:forEach>

</select></td></tr>
<tr><td>Name:</td><td><input type = 'text' name='name' value="${entries.name}"></td></tr>
<tr><td>Additional Info:</td><td><input type = 'text' name='info' value="${entries.info}"></td></tr>
<tr><td colspan='2'><button>Save</button></td></tr>
</table></form>
<br>

</body>
</html>