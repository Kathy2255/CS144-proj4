package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Date;

import java.util.HashMap;

public class PaymentServlet extends HttpServlet implements Servlet {

	public PaymentServlet() {}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String id = request.getParameter("itemid");
		  	HttpSession session = request.getSession(true);

		  	session.setAttribute("currentItemBeingPurchased", id);

		  	HashMap<String, String[]> tempmap = (HashMap<String, String[]>)session.getAttribute("itemMap");
		  	if (tempmap.containsKey(id)) {
		  		String[] values = (String[])tempmap.get(id);
		  		request.setAttribute("Name", values[0]);
				request.setAttribute("ItemID", id);
				request.setAttribute("Buy_Price", values[1]);
				request.getRequestDispatcher("/payment.jsp").forward(request, response);
		  	}

	  	
		} catch(Exception e) {
			request.getRequestDispatcher("/payment.jsp").forward(request, response);
		}

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);

	}

}