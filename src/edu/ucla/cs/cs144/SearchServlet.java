package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


import edu.ucla.cs.cs144.SearchResult;
import edu.ucla.cs.cs144.AuctionSearchClient;

public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	try{
	        int numResultsToSkip = Integer.parseInt(request.getParameter("numResultsToSkip").toString());
	        if(!(numResultsToSkip >= 0 && numResultsToSkip < 2147483640)){
	        	numResultsToSkip = 0;
	        }
			int numResultsToReturn = Integer.parseInt(request.getParameter("numResultsToReturn").toString());
			if(!(numResultsToReturn > 0 && numResultsToReturn < 2147483640)){
				numResultsToReturn = 10;
			}
			String query = request.getParameter("q").toString();
			SearchResult[] result = AuctionSearchClient.basicSearch(query, numResultsToSkip, numResultsToReturn + 1);
			int len = Math.min(numResultsToReturn, result.length);
			String[] resultLink = new String[len];
			for(int i = 0; i < len; i++){
				resultLink[i] = "<a href=\"../eBay/item?itemID=" + result[i].getItemId() + "\">" + result[i].getItemId() + " | "
				+ result[i].getName() + "</a>";
			}

			String prev = "";
			String encodeQuery = URLEncoder.encode(query, "UTF-8");
			int prevResultToSkip = Math.max(0, numResultsToSkip - numResultsToReturn);
			if(result.length > 0 && prevResultToSkip < numResultsToSkip){
				prev = "<a href = \"/eBay/search?numResultsToSkip=" + (numResultsToSkip - numResultsToReturn) + "&numResultsToReturn=" + 
						numResultsToReturn + "&q=" + encodeQuery +"\">prev</a>";
			}

			String next = "";
			if(result.length > numResultsToReturn){
				next = "<a href = \"/eBay/search?numResultsToSkip=" + (numResultsToSkip + numResultsToReturn) + "&numResultsToReturn=" + 
						numResultsToReturn + "&q=" + encodeQuery + "\">next</a>";
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
			//request.getRequestDispatcher("/keywordSearch.html").forward(request, response);
		}
	}

}
