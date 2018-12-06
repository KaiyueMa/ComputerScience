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

@WebServlet("/DeleteProducts")

public class DeleteProducts extends HttpServlet {
	private String error_msg;

	/* Trending Page Displays all the Consoles and their Information in Game Speed*/

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Consoles type whether it is microsft or sony or nintendo then add products to hashmap*/

		String name = "Trending";
		String CategoryName = request.getParameter("subCatagory");
		HashMap<String, Wearable_Technology> hm = new HashMap<String, Wearable_Technology>();
		if(CategoryName==null)
		{
			hm.putAll(SaxParserDataStore.wearable_Technologies);
		}
		else
		{
			if(CategoryName.equals("FitnessWatches"))
			{
				for(Map.Entry<String,Wearable_Technology> entry : SaxParserDataStore.wearable_Technologies.entrySet())
				{
				  if(entry.getValue().getLabel().equals("FitnessWatches")) 
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
			}
			else if(CategoryName.equals("SmartWatches"))
			{
				for(Map.Entry<String,Wearable_Technology> entry : SaxParserDataStore.wearable_Technologies.entrySet())
				{
				  if(entry.getValue().getLabel().equals("SmartWatches"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
			}
			else if(CategoryName.equals("Headphones"))
			{
				for(Map.Entry<String,Wearable_Technology> entry : SaxParserDataStore.wearable_Technologies.entrySet())
				{ 
			      if(entry.getValue().getLabel().equals("Headphones"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
			}
			else if(CategoryName.equals("VirtualReality"))
			{
				for(Map.Entry<String,Wearable_Technology> entry : SaxParserDataStore.wearable_Technologies.entrySet())
				{ 
			      if(entry.getValue().getLabel().equals("VirtualReality"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
			}
			else if(CategoryName.equals("PetTracker"))
			{
				for(Map.Entry<String,Wearable_Technology> entry : SaxParserDataStore.wearable_Technologies.entrySet())
				{ 
			      if(entry.getValue().getLabel().equals("PetTracker"))
				  {
					 hm.put(entry.getValue().getId(),entry.getValue());
				  }
				}
			}
		}

		/* Header, Left Navigation Bar are Printed.

		All the consoles and Console information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		displayRegistration(request, response, pw, true, hm);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Consoles type whether it is microsft or sony or nintendo then add products to hashmap*/

		String productName = request.getParameter("name");
		String productId = null;
		
		Wearable_Technology wearableTechnology = null;
		
		HashMap<String, Wearable_Technology> hm = new HashMap<String, Wearable_Technology>();
		
		
		for(Map.Entry<String,Wearable_Technology> entry : SaxParserDataStore.wearable_Technologies.entrySet())
		 {		
			if(entry.getValue().getName().equals(productName))
			 {
				 wearableTechnology = entry.getValue();
				 productId = entry.getValue().getId();
			 }
		 }
		
		hm = SaxParserDataStore.wearable_Technologies;
		
		hm.remove(productId);
		
		
		for(Map.Entry<String,Wearable_Technology> entry : hm.entrySet())
		 {
				 hm.put(entry.getValue().getId(),entry.getValue()); 
		 }

		displayRegistration(request, response, pw, true, hm);
	}
	
	protected void displayRegistration(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error,HashMap<String, Wearable_Technology> hm)
			throws ServletException, IOException {
		String name = "Trending";
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Products</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Wearable_Technology> entry : hm.entrySet()){
			Wearable_Technology console = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+console.getName()+"</h3>");
			pw.print("<strong>$"+console.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/consoles/"+console.getImage()+"' alt='' /></li>");
			pw.print("<li><form method='post' action='DeleteProducts'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					"<input type='hidden' name='maker' value='"+console.getLabel()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Delete'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					"<input type='hidden' name='maker' value='"+console.getLabel()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "</form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='consoles'>"+
					"<input type='hidden' name='maker' value='"+console.getLabel()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "</form></li>");
			pw.print("</ul></div></td>");
			
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}		
		pw.print("</table></div></div></div>");	
		utility.printHtml("Footer.html");
	}

}
