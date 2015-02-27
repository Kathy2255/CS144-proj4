<%@ page import = "edu.ucla.cs.cs144.SearchResult" %>

<HTML>
	<BODY>
		<form action="/eBay/search">
			<input type="hidden" name="numResultsToSkip" value="0"/>
			<input type="hidden" name="numResultsToReturn" value="10"/>
			Search:</br>
			<input type="text" name="q"/>
			<input type="submit" value="submit"/>
		</form>

		<%
			String numResultsToSkip = "" + request.getAttribute("numResultsToSkip");
			String numResultsToReturn = "" + request.getAttribute("numResultsToReturn");
			String query = "" + request.getAttribute("query");
			String[] result = (String[])request.getAttribute("result");
			String prev = "" + request.getAttribute("prev");
			String next = "" + request.getAttribute("next");
		%>
		<h3>Search Result of <%= query %> :</h3>
		<%
			for(String r : result){
		%>
			<p><%= r %></p>
		<%
			}
		%>
		<% if(prev != ""){ %>
		<p><%= prev %></p>
		<% } if(next != ""){ %>
		<p><%= next %></p>
		<% } %>
	</BODY>
</HTML>

<table style="width:100%">
	<tr>
		<td>
		</td>
	</tr>
</table>