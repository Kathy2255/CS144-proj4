package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucla.cs.cs144.AuctionSearchClient;
import org.apache.axis2.AxisFault;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import java.io.StringReader;

import javax.servlet.http.HttpSession;

import java.util.HashMap;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try{
        	String itemID = request.getParameter("itemID").toString();
        	String xmlString = AuctionSearchClient.getXMLDataForItemId(itemID);
			//response.getWriter().write(xmlString);
			//response.getWriter().write("name:: ");
			//response.getWriter().write(name);
        	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        	factory.setValidating(false);
        	factory.setIgnoringElementContentWhitespace(true);
			DocumentBuilder builder = factory.newDocumentBuilder(); 
			InputSource inputSource = new InputSource(new StringReader(xmlString));
			Document doc = builder.parse(inputSource);

			String name = doc.getElementsByTagName("Name").item(0).getTextContent();
			//category
			NodeList categoryList = doc.getElementsByTagName("Category");
			String category = "";
			for(int i = 0; i < categoryList.getLength(); i++){
				category += categoryList.item(i).getTextContent();
				if(i != categoryList.getLength() - 1){
					category += " | ";
				}
			}
			String currently = doc.getElementsByTagName("Currently").item(0).getTextContent();
			String buy_price = "--";
			if(doc.getElementsByTagName("Buy_Price").getLength() > 0){
				buy_price = doc.getElementsByTagName("Buy_Price").item(0).getTextContent();
			}
			String first_bid = doc.getElementsByTagName("First_Bid").item(0).getTextContent();
			String number_of_bids = doc.getElementsByTagName("Number_of_Bids").item(0).getTextContent();

			//UserID, Rating
			NodeList bidList = doc.getElementsByTagName("Bid");
			NodeList bidderList = doc.getElementsByTagName("Bidder");
			String[] bidderIDList = new String[bidList.getLength()];
			String[] bidderRatingList = new String[bidList.getLength()];
			for(int i = 0; i < bidList.getLength(); i++){
				Element bidderElement = (Element)bidderList.item(i);
				bidderIDList[i] = bidderElement.getAttribute("UserID");
				bidderRatingList[i] = bidderElement.getAttribute("Rating");
			}
			//Location of bidders
			NodeList locationList = doc.getElementsByTagName("Location");
			Element locationElement = (Element)locationList.item(locationList.getLength() - 1);
			String location = locationElement.getTextContent();
			String latitude = locationElement.getAttribute("Latitude");
			String longitude = locationElement.getAttribute("Longitude");
			String mapLocation = location;
			String mapLatitude = latitude;
			String mapLongitude = longitude; 
			String[] bidderLocationList = new String[bidList.getLength()];
			if(latitude != "" && longitude != ""){
					location += ("[" + latitude + ", " + longitude + "]");
				}
			for(int i = 0; i < bidList.getLength(); i++){
				locationElement = (Element)locationList.item(i);
				bidderLocationList[i] = locationElement.getTextContent();
				latitude = locationElement.getAttribute("Latitude");
				longitude = locationElement.getAttribute("Longitude");
				if(latitude != "" && longitude != ""){
					bidderLocationList[i] += ("[" + latitude + ", " + longitude + "]");
				}
			}

			//Country of bidders
			NodeList countryList = doc.getElementsByTagName("Country");
			String[] bidderCountryList = new String[bidList.getLength()];
			for(int i = 0; i < bidList.getLength(); i++){
				bidderCountryList[i] = countryList.item(i).getTextContent();
			}
			String country = countryList.item(countryList.getLength() - 1).getTextContent();
			//time of bid
			NodeList timeNodeList = doc.getElementsByTagName("Time");
			String[] timeList = new String[bidList.getLength()];
			for(int i = 0; i < bidList.getLength(); i++){
				timeList[i] = timeNodeList.item(i).getTextContent();
			}
			//amount of bid
			NodeList amountNodeList = doc.getElementsByTagName("Amount");
			String[] amountList = new String[bidList.getLength()];
			for(int i = 0; i < bidList.getLength(); i++){
				amountList[i] = amountNodeList.item(i).getTextContent();
			}

			String started = doc.getElementsByTagName("Started").item(0).getTextContent();
			String ends = doc.getElementsByTagName("Ends").item(0).getTextContent();
			Element sellerElement = (Element)doc.getElementsByTagName("Seller").item(0);
			String sellerID = sellerElement.getAttribute("UserID");
			String sellerRating = sellerElement.getAttribute("Rating");
			String description = doc.getElementsByTagName("Description").item(0).getTextContent();
			
			request.setAttribute("itemid", itemID);
			request.setAttribute("name", name);
			request.setAttribute("category", category);
			request.setAttribute("currently", currently);

			request.setAttribute("buy_price", buy_price);
			if (!buy_price.equals("--")) {
				HttpSession session = request.getSession(true);
				HashMap<String, String[]> map;

				if (session.getAttribute("itemMap") == null) {
                    map = new HashMap<String, String[]>();
                } else {
                    map = (HashMap<String, String[]>)session.getAttribute("itemMap");
                }

                if (!map.containsKey(itemID)) {
                    String[] mapvalue = new String[2];
                    mapvalue[0] = name;
                    mapvalue[1] = buy_price;
                    map.put(itemID, mapvalue);
                    session.setAttribute("itemMap", map);
                }
			}
			request.setAttribute("first_bid", first_bid);
			request.setAttribute("number_of_bids", number_of_bids);
			request.setAttribute("bidderIDList", bidderIDList);
			request.setAttribute("bidderRatingList", bidderRatingList);
			request.setAttribute("bidderLocationList", bidderLocationList);
			request.setAttribute("bidderCountryList", bidderCountryList);
			request.setAttribute("timeList", timeList);
			request.setAttribute("amountList", amountList);
			request.setAttribute("country", country);
			request.setAttribute("location", location);
			request.setAttribute("started", started);
			request.setAttribute("ends", ends);
			request.setAttribute("sellerID", sellerID);
			request.setAttribute("sellerRating", sellerRating);
			request.setAttribute("description", description);
			request.setAttribute("mapLatitude", mapLatitude);
			request.setAttribute("mapLongitude", mapLongitude);
			request.setAttribute("mapLocation", mapLocation);
			//1045769659
			request.getRequestDispatcher("/item.jsp").forward(request, response);
		}
		catch(Exception e) {
			//response.getWriter().write(e.toString());
			request.getRequestDispatcher("/getItem.html").forward(request, response);
		}
		

    }
}
