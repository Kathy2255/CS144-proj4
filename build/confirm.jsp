<html>

	<body>
		<h1>Confirmation Page</h1>
		
        <p>Item ID: <%= request.getAttribute("ItemID") %></p> 
        <p>Item Name: <%= request.getAttribute("Name") %></p> 
       	<p>Buy Price: <%= request.getAttribute("Buy_Price") %></p> 
        <p>Credit Card: <%= request.getAttribute("CC") %></p>
        <p>Time: <%= request.getAttribute("Time") %></p>
    	
    	
		
	</body>


</html>

