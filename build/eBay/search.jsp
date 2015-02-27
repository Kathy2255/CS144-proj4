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
			int numResultsToSkip = Integer.parseInt(request.getAttribute("numResultsToSkip"));
			int numResultsToReturn = Integer.parseInt(request.getAttribute("numResultsToReturn"));
			string query = request.getAttribute("q");
			SearchResult[] result = (SearchResult[])request.getAttribute("result");
		%>

		<table style="width:100%">
		<%
			for(r : result){
		%>
			<tr>
				<td>
					<%= r.getItemId() %>
				</td>
				<td>
					<%= r.getName() %>
				</td>
			</tr>
		<%
			}
		%>
		</table>
	</BODY>
</HTML>

<table style="width:100%">
	<tr>
		<td>
		</td>
	</tr>
</table>