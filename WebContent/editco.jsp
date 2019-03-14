<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Out</title>
</head>
<body>
<p>ID:${id}</p>
<p>Name:${name}</p>
<form action= 'CheckOut' method = 'post'>
<table border =1 >

<tr><td>Date Borrowed:</td><td>${cd}<input type = 'hidden' name='borrowed' value="${cd}"/></td></tr>
<tr><td>Due Back By(Optional):</td><td><input type = 'text' name='due'/></td></tr>
<tr><td>CIN:</td><td><input type = 'text' name='cin' placeholder="CIN"/></td></tr>
<tr><td>Name:</td><td><input type = 'text' name='Name' placeholder="Name"/></td></tr>
<tr><td colspan='2'><button>Checkout</button></td></tr>
</table><input type = 'hidden' name='id' value='${id}'/></form>
</body>
</html>