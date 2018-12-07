package smartPortables;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				
				String user_type = (String) session.getAttribute("usertype");
//				System.out.print(user_type);
				
				if(session.getAttribute("usertype").equals("manager")) {
					
					result = result + "<li><a href='ProductModify?button=Addproduct'><span class='glyphicon'>Addproduct</span></a></li>";
					result = result + "<li><a href='ProductModify?button=Updateproduct'><span class='glyphicon'>Updateproduct</span></a></li>";
					result = result + "<li><a href='ProductModify?button=Deleteproduct'><span class='glyphicon'>Deleteproduct</span></a></li>"
							+ "<li><a href='Inventory'><span class='glyphicon'>Inventory</span></a></li>"
							+ "<li><a href='SalesReport'><span class='glyphicon'>SalesReport</span></a></li>";
				}
				
				if(session.getAttribute("usertype").equals("salesman")) {
					result = result + "<li><a href='CreateUserAccount'><span class='glyphicon'>CreateUserAccount</span></a></li>";
				}
				
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
//						+ "<li><a href='DataVisualization'><span class='glyphicon'>Trending</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
				pw.print(result);
		} else
				pw.print(result);
	}
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	}  

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{		
//				FileInputStream fileInputStream=new FileInputStream(new File("/Users/michael/Codes/Java-eclipse/smartPortables/WebContent/UserDetails.txt"));
//				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
//				hm= (HashMap)objectInputStream.readObject();
				hm= MySqlDataStoreUtilities.selectUser();
			}
			catch(Exception e)
			{
			}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
			try
			{
//				FileInputStream fileInputStream = new FileInputStream(new File("/Users/michael/Codes/Java-eclipse/smartPortables/WebContent/PaymentDetails.txt"));
//				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
//				orderPayments = (HashMap)objectInputStream.readObject();
				orderPayments = MySqlDataStoreUtilities.selectOrder();	
			}
			catch(Exception e)
			{
			
			}
			int size=0;
			for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
					 size=size + 1;
					 
			}
			return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public boolean removeOrders(int orderId) {
		if(OrdersHashMap.orders.containsKey(username())) {
			ArrayList<OrderItem> orders = OrdersHashMap.orders.get(username());
			orders.remove(orderId);
			return true;
		}
		return false;
	}
	
	public void storeProduct(String name,String type,String maker, String acc){
		
		
//      java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
//      String placedDate = format.format(cal.getTime());
		
		
		Date dateNow = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateNow);
		Date datePlaced = cal.getTime();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)+15);
		Date date15 = cal.getTime();
		
//		cal.setTime(dateNow);
//		cal.add(Calendar.DATE, 15);
//		long date15 = 0;
//		date15 = dateNow.getTime()+(15*24*60*60*1000);

		
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		if(type.equals("wearable_Technology")){
			Wearable_Technology wearable_Technology;
			wearable_Technology = SaxParserDataStore.wearable_Technologies.get(name);
			OrderItem orderitem = new OrderItem(wearable_Technology.getName(), wearable_Technology.getPrice(), wearable_Technology.getImage(), wearable_Technology.getLabel(), wearable_Technology.getDiscount(), dateNow);
			orderitem.setTimePlaced(datePlaced);
			orderitem.setTimeDelivered(date15);
			orderItems.add(orderitem);
		}
		if(type.equals("wearableTechnologies")){
			Wearable_Technology wearable_Technology;
			wearable_Technology = SaxParserDataStore.wearable_Technologies.get(name);
			OrderItem orderitem = new OrderItem(wearable_Technology.getName(), wearable_Technology.getPrice(), wearable_Technology.getImage(), wearable_Technology.getLabel(), wearable_Technology.getDiscount(), dateNow);
			orderitem.setTimePlaced(datePlaced);
			orderitem.setTimeDelivered(date15);
			orderItems.add(orderitem);
		}
		if(type.equals("phones")){
			Phone phone = null;
			phone = SaxParserDataStore.phones.get(name);
			OrderItem orderitem = new OrderItem(phone.getName(), phone.getPrice(), phone.getImage(), phone.getLabel(), phone.getDiscount(), dateNow);
			orderItems.add(orderitem);
		}
		if(type.equals("laptops")){
			Laptop laptops = null;
			laptops = SaxParserDataStore.laptops.get(name);
			OrderItem orderitem = new OrderItem(laptops.getName(), laptops.getPrice(), laptops.getImage(), laptops.getLabel(), laptops.getDiscount(), dateNow);
			orderItems.add(orderitem);
		}
		if(type.equals("accessories")){	
			Accessory accessory = SaxParserDataStore.accessories.get(name); 
			OrderItem orderitem = new OrderItem(accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getLabel(), accessory.getDiscount(), dateNow);
			orderItems.add(orderitem);
		}
		
		if(type.equals("Voice Assistant_Smart Speaker")){	
			VoiceAssistant_SmartSpeaker voice_assistant_smart_speaker = SaxParserDataStore.voiceassistant_smartspeakers.get(name); 
			OrderItem orderitem = new OrderItem(voice_assistant_smart_speaker.getName(), voice_assistant_smart_speaker.getPrice(), voice_assistant_smart_speaker.getImage(), voice_assistant_smart_speaker.getLabel(), voice_assistant_smart_speaker.getDiscount(), dateNow);
			orderItems.add(orderitem);
		}
		
	}
	// store the payment details for orders
	// store the payment details for orders
		public void storePayment(int orderId, String Customer,
			String orderName,double orderPrice,String userAddress,String creditCardNo, String PlacedDate, String DeliveredDate){
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
			String TOMCAT_HOME = System.getProperty("catalina.home");
				// get the payment details file 
				try
				{
//					FileInputStream fileInputStream = new FileInputStream(new File("/Users/michael/Codes/Java-eclipse/smartPortables/WebContent/PaymentDetails.txt"));
//					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
//					orderPayments = (HashMap)objectInputStream.readObject();
					orderPayments = MySqlDataStoreUtilities.selectOrder();
				}
				catch(Exception e)
				{
				
				}
				if(orderPayments==null)
				{
					orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
				}
				// if there exist order id already add it into same list for order id or create a new record with order id
				
				if(!orderPayments.containsKey(orderId)){	
					ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
					orderPayments.put(orderId, arr);
				}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);
			OrderPayment orderpayment = null ;
			if (Customer!= null) {
				orderpayment = new OrderPayment(orderId, Customer,orderName,orderPrice,userAddress,creditCardNo, PlacedDate, DeliveredDate);

			}
			else {
				orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo, PlacedDate, DeliveredDate);
			}
			listOrderPayment.add(orderpayment);	
				
				// add order details into file

				try
				{	
//					FileOutputStream fileOutputStream = new FileOutputStream(new File("/Users/michael/Codes/Java-eclipse/smartPortables/WebContent/PaymentDetails.txt"));
//					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//	            	objectOutputStream.writeObject(orderPayments);
//					objectOutputStream.flush();
//					objectOutputStream.close();       
//					fileOutputStream.close();
					MySqlDataStoreUtilities.insertOrder(orderId, Customer, orderName, orderPrice, userAddress, creditCardNo, PlacedDate, DeliveredDate);

				}
				catch(Exception e)
				{
					System.out.println("inside exception file not written properly");
				}	
		}

	
	/* getConsoles Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, Wearable_Technology> getConsoles(){
			HashMap<String, Wearable_Technology> hm = new HashMap<String, Wearable_Technology>();
			hm.putAll(SaxParserDataStore.wearable_Technologies);
			return hm;
	}
	
	/* getGames Functions returns the  Hashmap with all Games in the store.*/

	public HashMap<String, Phone> getGames(){
			HashMap<String, Phone> hm = new HashMap<String, Phone>();
			hm.putAll(SaxParserDataStore.phones);
			return hm;
	}
	
	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, Laptop> getTablets(){
			HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
			hm.putAll(SaxParserDataStore.laptops);
			return hm;
	}
	
	/* getProducts Functions returns the Arraylist of consoles in the store.*/

	public ArrayList<String> getProducts(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Wearable_Technology> entry : getConsoles().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of games in the store.*/

	public ArrayList<String> getProductsGame(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Phone> entry : getGames().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of Tablets in the store.*/

	public ArrayList<String> getProductsTablets(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Laptop> entry : getTablets().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	
//	public String storeReview(String productname,String producttype,String productmaker,String reviewrating,String reviewdate,String  reviewtext,String reatilerpin,String price,String city){
//		 String message=MongoDBDataStoreUtilities.insertReview(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city);
//		  if(!message.equals("Successfull"))
//		  { return "UnSuccessfull";
//		  }
//		  else
//		  {
//		  HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
//		  try
//		  {
//		   reviews=MongoDBDataStoreUtilities.selectReview();
//		  }
//		  catch(Exception e)
//		  {
//		   
//		  }
//		  if(reviews==null)
//		  {
//		   reviews = new HashMap<String, ArrayList<Review>>();
//		  }
//		   // if there exist product review already add it into same list for productname or create a new record with product name
//		   
//		  if(!reviews.containsKey(productname)){ 
//		   ArrayList<Review> arr = new ArrayList<Review>();
//		   reviews.put(productname, arr);
//		  }
//		  ArrayList<Review> listReview = reviews.get(productname);  
//		  Review review = new Review(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city);
//		  listReview.add(review); 
//		   
//		   // add Reviews into database
//		  
//		  return "Successfull"; 
//		  }
//		 }
	public String storeReview(String ProductModelName,
    		String ProductCategory,
    		String ProductPrice,
    		String RetailerName,
    		String RetailerZip,
    		String RetailerCity,
    		String RetailerState,
    		String ProductOnSale,
    		String ManufacturerName,
    		String ManufacturerRebate,
    		String UserID,
    		String UserAge,
    		String UserGender,
    		String UserOccupation,
    		String ReviewRating,
    		String ReviewDate,
    		String ReviewText){
//    	username()
	String message=MongoDBDataStoreUtilities.insertReview(ProductModelName,ProductCategory,ProductPrice,RetailerName,RetailerZip,
			RetailerCity,RetailerState,ProductOnSale,ManufacturerName,ManufacturerRebate,UserID,UserAge,UserGender,UserOccupation,ReviewRating,
			ReviewDate,ReviewText);
		if(!message.equals("Successfull"))
		{ return "UnSuccessfull";
		}
		else
		{
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try
		{
			//reviews=MongoDBDataStoreUtilities.selectReview();
		}
		catch(Exception e)
		{
			
		}
		if(reviews==null)
		{
			reviews = new HashMap<String, ArrayList<Review>>();
		}
			// if there exist product review already add it into same list for productname or create a new record with product name
			
		if(!reviews.containsKey(ProductModelName)){	
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(ProductModelName, arr);
		}
		ArrayList<Review> listReview = reviews.get(ProductModelName);		
		Review review = new Review(ProductModelName,ProductCategory,ProductPrice,RetailerName,RetailerZip,
				RetailerCity,RetailerState,ProductOnSale,ManufacturerName,ManufacturerRebate,UserID,UserAge,UserGender,UserOccupation,ReviewRating,
				ReviewDate,ReviewText);
		listReview.add(review);	
			
			// add Reviews into database
		
		return "Successfull";	
		}
	}
	

}
