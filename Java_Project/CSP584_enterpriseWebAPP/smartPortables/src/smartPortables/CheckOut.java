package smartPortables;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/CheckOut")

//once the user clicks buy now button page is redirected to checkout page where user has to give checkout information

public class CheckOut extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try
        {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
        HttpSession session=request.getSession(); 

		//get the order product details	on clicking submit the form will be passed to submitorder page	
		
	    String userName = session.getAttribute("username").toString();
        String orderTotal = request.getParameter("orderTotal");
        String DeleteCart = request.getParameter("DeleteCart");
        String CheckOut = request.getParameter("CheckOut");
        String index = request.getParameter("index");
        Integer key = Integer.parseInt(index) - 1;
        
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='CheckOut' action='Payment' method='post'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		if (DeleteCart!= null) {
        	pw.print("<h4 style='color:red'>You have cancel the product from the cart</h4>");
        	boolean removed = utility.removeOrders(key);
        } 
		
		if (CheckOut!= null) {
		pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
		
//		if (utility.session.getAttribute("usertype").equals("salesman")){
//			pw.print(request.getParameter("UserName"));
//			pw.print("<input type='hidden' name='UserName' value='" + request.getParameter("UserName")+ "'>");
//		}
//		else {	
		
			pw.print(userName);
//		}
			
			
			Double manufacturerRebate = (double) 0;
    		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
    		String TOMCAT_HOME = System.getProperty("catalina.home");
    		try
    			{
    				orderPayments = MySqlDataStoreUtilities.selectOrder();
    				
    			}
    		catch(Exception e)
    			{
    		
    			}
			
			
			
			
		pw.print("</td></tr>");
		// for each order iterate and display the order name price
		
		SimpleDateFormat ft = new SimpleDateFormat ("E yyyy.MM.dd ");

		
		double orderTot = 0;
		for (OrderItem oi : utility.getCustomerOrders()) 
		{
			pw.print("<tr><td> Product Purchased:</td><td>");
			pw.print(oi.getName()+"</td></tr><tr><td>");

			 
			double rebateDiscount = 0;
			double showrebate = 0;
			// some label offer special rebate
			if (oi.getRetailer().equalsIgnoreCase("Fitness_Watches")) {
				
				rebateDiscount = 0.1;
				showrebate = rebateDiscount*100;
			}
			
			
			double price1 = oi.getPrice();
			double dis = oi.getDiscount();
			orderTot += (price1*(100-dis)/100)*(1-rebateDiscount);
			
			String placedDate = ft.format(oi.getTimePlaced());
			String deliveredDate = ft.format(oi.getTimeDelivered());
			
//			System.out.println("Current Date: " + ft.format(oi.getTimePlaced()));
//			oi.getTimePlaced();
			pw.print("<input type='hidden' name='orderPrice' value='"+orderTot+"'>");
			pw.print("<input type='hidden' name='orderDiscount' value='"+oi.getDiscount()+"'>");
			pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
			pw.print("<input type='hidden' name='placedDate' value='"+placedDate+"'>");
			pw.print("<input type='hidden' name='deliverDate' value='"+deliveredDate+"'>");
//			System.out.println(oi.getTimePlaced());
			pw.print("Product Price:</td><td>"+ oi.getPrice()+"</td>");
			pw.print("<td>Discount:"+ oi.getDiscount()+"</td>");
			if (rebateDiscount!=0) {
				pw.print("<td>Manufactor rebate:"+ showrebate +"% </td>");

			}
			pw.print("</td></tr>");
		}
		pw.print("<tr><td>");
        pw.print("Total Order Cost</td><td>"+orderTot);
		pw.print("<input type='hidden' name='orderTotal' value='"+orderTot+"'>");
		pw.print("</td></tr></table><table><tr></tr><tr></tr>");	
		pw.print("<tr><td>");
     	pw.print("Credit/accountNo</td>");
		pw.print("<td><input type='text' name='creditCardNo'>");
		pw.print("</td></tr>");
		pw.print("<tr><td>");
	    pw.print("Customer Address</td>");
		pw.print("<td><input type='text' name='userAddress'>");
		
		if (utility.session.getAttribute("usertype").equals("salesman")) {
			pw.print("<tr><td>");
			pw.print("Assign To Customer Name</td>");
			pw.print("<td><input type='text' name='assingCustomer'>");
		}
        pw.print("</td></tr>");
		pw.print("<tr><td colspan='2'>");
		pw.print("<input type='submit' name='submit' class='btnbuy'>");
        pw.print("</td></tr></table></form>");
		pw.print("</div></div></div>");
		}
		utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}  			
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    }
}
