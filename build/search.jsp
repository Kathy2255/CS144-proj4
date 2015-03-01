<%@ page import = "edu.ucla.cs.cs144.SearchResult" %>
<%
	String numResultsToSkip = "" + request.getAttribute("numResultsToSkip");
	String numResultsToReturn = "" + request.getAttribute("numResultsToReturn");
	String query = "" + request.getAttribute("query");
%>
<HTML>
	<HEAD>
		<SCRIPT type="text/javascript" src="autosuggest.js"></SCRIPT>
		<SCRIPT type="text/javascript" src="provider.js"></SCRIPT>
		<SCRIPT type="text/javascript">
			window.onload = function () {
				var oTextbox = new AutoSuggestControl(document.getElementById("qtxt"), new SuggestionProvider());        
			}
		</SCRIPT>
		<LINK rel="stylesheet" href="suggest.css">

	</HEAD>
	<BODY>
		<h1>Keyword Search Page</h1>
		<form action="/eBay/search">
			<input type="hidden" name="numResultsToSkip" value="0"/>
			<input type="hidden" name="numResultsToReturn" value="10"/>
			Search:</br>
			<input type="text" name="q", id = "qtxt", value="<%= query %>" autocomplete="off" style="width:500px;"/>
			<input type="submit" value="submit"/>
		</form>

		<%
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