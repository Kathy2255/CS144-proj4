package edu.ucla.cs.cs144;

import java.io.IOException;
import java.net.HttpURLConnection;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class ProxyServlet extends HttpServlet implements Servlet {
       
    public ProxyServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try{

        	response.setContentType("text/xml"); 
        	String baseurl = "http://google.com/complete/search?output=toolbar&";
        	String charset = "UTF-8";

        	String q = request.getParameter("q");
        	String query = String.format("q=%s", URLEncoder.encode(q, charset));
        	URLConnection connection = new URL(baseurl + query).openConnection();
        	connection.setRequestProperty("Accept-Charset", charset);

        	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        	String inputLine;
        	StringBuffer buf = new StringBuffer();

        	while ((inputLine = in.readLine()) != null) {
        		buf.append(inputLine);
        	}

        	PrintWriter out = response.getWriter();

        	out.println(buf.toString());

        }catch(Exception e) {
        	response.getWriter().write(e.toString());
        }
    }
}
