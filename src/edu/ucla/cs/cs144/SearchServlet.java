package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucla.cs.cs144.SearchResult;
import edu.ucla.cs.cs144.AuctionSearchClient;

public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try{
	        int numResultsToSkip = Integer.parseInt(request.getParameter("numResultsToSkip").toString());
	        if(numResultsToSkip < 0){
	        	numResultsToSkip = 0;
	        }
			int numResultsToReturn = Integer.parseInt(request.getParameter("numResultsToReturn").toString());
			if(numResultsToReturn < 0){
				numResultsToReturn = 0;
			}
			String query = request.getParameter("q").toString();
			SearchResult[] result = AuctionSearchClient.basicSearch(query, numResultsToSkip, numResultsToReturn);
			String[] resultLink = new String[result.length];
			for(int i = 0; i < result.length; i++){
				resultLink[i] = "<a href=\"/item?itemID=" + result[i].getItemId() + "\">" + result[i].getName() + "</a>";
			}

			String prev = "";
			SearchResult[] resultPrev = null;
			if(numResultsToSkip - numResultsToReturn >= 0){
				resultPrev = AuctionSearchClient.basicSearch(query, numResultsToSkip - numResultsToReturn, numResultsToReturn);
			}
			if(resultPrev != null && resultPrev.length > 0){
				prev = "<a href = \"../eBay/search?numResultsToSkip=" + (numResultsToSkip - numResultsToReturn) + "&numResultsToReturn=" + 
						numResultsToReturn + "&q=" + query +"\">prev</a>";
			}

			String next = "";
			SearchResult[] resultNext = null;
			resultNext = AuctionSearchClient.basicSearch(query, numResultsToSkip + numResultsToReturn, numResultsToReturn);
			if(resultNext != null && resultNext.length > 0){
				next = "<a href = \"/eBay/search?numResultsToSkip=" + (numResultsToSkip + numResultsToReturn) + "&numResultsToReturn=" + 
						numResultsToReturn + "&q=" + query + "\">next</a>";
			}

			request.setAttribute("result", resultLink);
			request.setAttribute("prev", prev);
			request.setAttribute("next", next);
			request.setAttribute("query", query);
			request.setAttribute("numResultsToReturn", numResultsToReturn);
			request.setAttribute("numResultsToSkip", numResultsToSkip);
			request.getRequestDispatcher("/search.jsp").forward(request, response);
		}
		catch(Exception e) {
			response.getWriter().write(e.toString());
		}
	}

}