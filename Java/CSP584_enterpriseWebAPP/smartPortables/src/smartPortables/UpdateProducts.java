package smartPortables;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/UpdateProducts")

public class UpdateProducts extends HttpServlet {
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayRegistration(request, response, pw, false);
	}

	/*   Username,Password,Usertype information are Obtained from HttpServletRequest variable and validates whether
		 the User Credential Already Exists or else User Details are Added to the Users HashMap */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		String ProductID = request.getParameter("ProductID");
		String ProductName = request.getParameter("ProductName");
		String Price = request.getParameter("Price");
		String Image = request.getParameter("Image");
		String Catagory = request.getParameter("Catagory");
		String subCatagory = request.getParameter("subCatagory");
		String Condition = request.getParameter("Condition");
		String Discount = request.getParameter("Discount");
		
		error_msg = "Success";
		double Price2 = Double.parseDouble(Price);
		double Discount2 = Double.parseDouble(Discount);
		
		if (Catagory.equalsIgnoreCase("wearableTechnology")) {
			Wearable_Technology wearableTechnology = new Wearable_Technology(ProductID, ProductName, Price2, Image, subCatagory, Condition, Discount2 , null, null);
			SaxParserDataStore.wearable_Technologies.put(ProductID, wearableTechnology);
        }
		displayRegistration(request, response, pw, true);
	}

	/*  displayRegistration function displays the Registration page of New User */
	
	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Login</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		if (error)
			pw.print("<h4 style='color:red'>"+error_msg+"</h4>");
		pw.print("<form method='post' action='UpdateProducts'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>ProductID</h3></td><td><input type='text' name='ProductID' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>ProductName</h3></td><td><input type='text' name='ProductName' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Price</h3></td><td><input type='text' name='Price' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Image</h3></td><td><input type='text' name='Image' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Catagory</h3></td><td><input type='text' name='Catagory' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>subCatagory</h3></td><td><input type='text' name='subCatagory' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Condition</h3></td><td><input type='text' name='Condition' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Discount</h3></td><td><input type='text' name='Discount' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<input type='submit' class='btnbuy' name='ByUser' value='Add/UpdateProduct' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</form>" + "</div></div></div>");
//		utility.printHtml("Footer.html");
	}
}