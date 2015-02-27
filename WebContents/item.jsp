<html>
	<body>
		<form action="/eBay/item">
			Search:</br>
			<input type="text" name="itemID"/>
			<input type="submit" value="submit"/>
		</form>


		<% 
			String name = "" + request.getAttribute("name"); 
			String category = "" + request.getAttribute("category"); 
			String currently = "" + request.getAttribute("currently"); 
			String buy_price = "" + request.getAttribute("buy_price"); 
			String first_bid = "" + request.getAttribute("first_bid"); 
			String number_of_bids = "" + request.getAttribute("number_of_bids"); 
			String location = "" + request.getAttribute("location");
			String country = "" + request.getAttribute("country");
			String started = "" + request.getAttribute("started"); 
			String ends = "" + request.getAttribute("ends"); 
			String sellerID = "" + request.getAttribute("sellerID"); 
			String sellerRating = "" + request.getAttribute("sellerRating"); 
			String description = "" + request.getAttribute("description"); 
			String[] bidderIDList = (String[])request.getAttribute("bidderIDList"); 
			String[] bidderRatingList = (String[])request.getAttribute("bidderRatingList"); 
			String[] bidderLocationList = (String[])request.getAttribute("bidderLocationList"); 
			String[] bidderCountryList = (String[])request.getAttribute("bidderCountryList"); 
			String[] timeList = (String[])request.getAttribute("timeList"); 
			String[] amountList = (String[])request.getAttribute("amountList"); 
		%>

		<h2><%= name %></h2>
		<p>Category: <%= category %></p>
		<p>Currently: <%= currently %></p>
		<p>Buy Price: <%= buy_price %></p>
		<p>First Bid: <%= first_bid %></p>
		<p>Number of Bids: <%= number_of_bids %></p>
		<p>Country: <%= country %></p>
		<p>Location: <%= location %></p>
		<p>Start: <%= started %></p>
		<p>End: <%= ends %></p>
		<p>Seller ID: <%= sellerID %></p>
		<p>Seller Rating: <%= sellerRating %></p>
		<p>Description: <%= description %></p>
		<%
			if(bidderIDList.length > 0){
		%>
		<table style="width:100%">
			<tr>
				<td>Bidder's ID</td>
				<td>Bidder's Rating</td>
				<td>Bidder's Location</td>
				<td>Bidder's Country</td>
				<td>Time</td>
				<td>Amount</td>
			</tr>
			<%
					for(int i = 0; i < bidderIDList.length; i++){
			%>
			<tr>
				<td><%= bidderIDList[i] %></td>
				<td><%= bidderRatingList[i] %></td>
				<td><%= bidderLocationList[i] %></td>
				<td><%= bidderCountryList[i] %></td>
				<td><%= timeList[i] %></td>
				<td><%= amountList[i] %></td>
			</tr>
			<%
					}
				}
			%>
		</talbe>
	</body>
</html>