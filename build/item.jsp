<html>
<% 
	String mapLatitude = "" + request.getAttribute("mapLatitude");
	String mapLongitude = "" + request.getAttribute("mapLongitude");
	String mapLocation = "" + request.getAttribute("mapLocation"); 
	String address = mapLocation + ", " + request.getAttribute("country");
%>
<head> 
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" /> 
<style type="text/css"> 
  html { height: 100% } 
  body { height: 100%; margin: 0px; padding: 0px } 
  #map_canvas { height: 100% } 
</style> 
<script type="text/javascript" 
    src="http://maps.google.com/maps/api/js?sensor=false"> 
</script> 
<script type="text/javascript"> 


function initialize() { 
  	var mapLatitude = "<%= mapLatitude %>";
  	var mapLongitude = "<%= mapLongitude %>";
  	var address = "<%= address %>";
  	var latlng = new google.maps.LatLng(mapLatitude, mapLongitude); 
	var myOptions = { 
	    zoom: 14, // default is 8  
	    center: latlng, 
	    mapTypeId: google.maps.MapTypeId.ROADMAP 
	}; 
	var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
  	if(mapLatitude === "" || mapLongitude === ""){
  		var geocoder = new google.maps.Geocoder();
  		geocoder.geocode( { 'address': address}, function(results, status) {
      		if (status == google.maps.GeocoderStatus.OK) {
        		map.setCenter(results[0].geometry.location);
        		var marker = new google.maps.Marker({
            		map: map,
            		position: results[0].geometry.location
        		});
      		} else {
      			map.setZoom(0);
        		//alert("Geocode was not successful for the following reason: " + status);
      		}
    	});
  	}
  } 

</script> 
</head> 
	<body onload="initialize()">
		<p><a href="index.html">back home</a></p>
		<form action="/eBay/item">
			Search:</br>
			<input type="text" name="itemID" autocomplete="off" style="width:500px;"/>
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
		<div id="map_canvas" style="width:50%; height:50%"></div> 
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