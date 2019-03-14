<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CheckOut Log</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
$(function(){
	
	$(".return").click(function(){
		var id = $("#itemid").text();
		var cin = $(this).val();
		$.ajax({ 
	           url: "Return",
	           data: {
	               "cin" : cin,
	               "id" : id
	           },
				success: function(){
					
					    window.location.href = "CheckoutLog?id="+id;
				}
	});
	
});

});
</script>
</head>
<body>
ID:<p id="itemid">${item.id}</p>
<p>Name:${item.name}</p>
<a href="DisplayItems">Back to Items</a>  <c:if test="${item.available == 'Yes'}"><a href="CheckOut?id=${item.id}&name=${item.name}">| Check Out</a></c:if>
<table border = "1">
<tr><th>CIN</th><th>Name</th><th>Date Borrowed</th><th>Due Back By</th><th>Date Returned</th></tr>
<c:forEach items = "${list}" var = "entry">
<tr><td class="cin">${entry.getCin()}</td><td>${entry.getName()}</td><td>${entry.getBorrowed()}</td><td>${entry.getDue()}</td>
<c:choose>
<c:when test= "${entry.getReturned() eq null}">
<td><button class="return" value="${entry.getCin()}">Return</button></td>
</c:when>
<c:otherwise><td>${entry.getReturned()}</td></c:otherwise>
</c:choose>
</tr>
</c:forEach>
</table>
</body>
</html>