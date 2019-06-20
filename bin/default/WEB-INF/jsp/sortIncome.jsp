<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sort!</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <h2 class="hello-title">Sort!</h2>
    <script src="/js/main.js"></script>
    
        <table class="order-table">
    		<tr>
    			<th class="order-table-header">Id</th>
    			<th class="order-table-header">Name</th>
    			<th class="order-table-header">Surname</th>
    			<th class="order-table-header">Income</th>
    			<th class="order-table-header">Tax</th>
    		</tr>
    		<tbody>
	    		<c:forEach var="o" items="${result}">
		    		<tr>
		    			<td>${o.id}</td>
						<td>${o.name}</td>
						<td>${o.surname}</td>
						<td>${o.income}</td>
						<td>${o.taxPayedAmount}</td>
					</tr>
					    			
	    		</c:forEach>
    		</tbody>
    	</table>
    	
    <form action="/sort/income-asc" method="get">
    	<input type="submit" value="sort income asc" />
    </form>
    
    <form action="/sort/income-desc" method="get">
    	<input type="submit" value="sort income desc" />
    </form>
    
    <form action="/sort/tax-asc" method="get">
    	<input type="submit" value="sort tax asc" />
    </form>	
    	
    <form action="/sort/tax-desc" method="get">
    	<input type="submit" value="sort tax desc" />
    </form>
</body>
</html>