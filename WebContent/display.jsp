<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Department Library</title>
<link rel="stylesheet" href="library.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	
	$("td.type").dblclick(function(){
		var x = $(this).text();
		var form = $("<input type='text' name='text' value='"+x+"'/>");	
		$(this).html(form);

		$("input").blur(function(){
			var nv = $("input[name = 'text']").val();
			
			
			$.ajax({ 
		           url: "Type",
		           data: {
		               "type" : nv,
		               "oldtype" : x
		           },
					success: function(){
						var x = $(this).parent().html(nv);
						$(this).remove();
						window.location.href = "DisplayItems";
					}
		       });
		});
		
	});
});
	
</script>
</head>
<body>
<div id="header">
<c:choose><c:when test="${sessionScope.user == null}"><a href = 'Login' class='remove'>Login</a></c:when>
<c:otherwise><a href = 'Logout' class='remove'>Logout</a></c:otherwise>
</c:choose>
<a href = 'AddItems' class = 'remove'>Add Items  |</a><a href="AddType" class = 'remove'>Add New Type |</a>
<p id="name">CSULA Library</p>
</div>
<table>
<tr>
<th>Id</th><th>Type</th><th>Name</th><th>Additional Info</th><th>Available</th><th>Operation</th>
</tr>
<c:forEach items="${dept}" var="entry">
<tr><td>${entry.getId()}</td><c:choose><c:when test="${sessionScope.user == null}"><td>${entry.getType()}</td></c:when><c:otherwise><td class="type">${entry.getType()}</td></c:otherwise></c:choose><td><a href="CheckoutLog?id=${entry.getId()}">${entry.getName()}</a></td><td>${entry.getInfo()}</td><td>${entry.getAvailable()}</td>
<td><a href = 'EditItems?id=${entry.getId()}' >Edit</a></td>
</tr>
</c:forEach>
</table>
<br>
</body>
</html>