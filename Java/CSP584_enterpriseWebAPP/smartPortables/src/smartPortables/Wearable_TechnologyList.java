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

@WebServlet("/Wearable_TechnologyList")

public class Wearable_TechnologyList extends HttpServlet {

	/* Console Page Displays all the Consoles and their Information in Game Speed */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("label");
        

		/* Checks the Tablets type whether it is microsft or sony or nintendo */

		HashMap<String, Wearable_Technology> hm = new HashMap<String, Wearable_Technology>();
		if(CategoryName==null){
			hm.putAll(SaxParserDataStore.wearable_Technologies);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("Fitness_Watches"))
		   {
			 for(Map.Entry<String,Wearable_Technology> entry : SaxParserDataStore.wearable_Technologies.entrySet())
			 {
				if(entry.getValue().getLabel().equals("Fitness_Watches"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
//					 System.out.println("%%%%%%%%%%%%%%" + entry.getValue().getWarranties() + "#############");
				 }
			 }
				name = "Fitness_Watches";
		   }
		   else if(CategoryName.equals("Smart_Watches"))
		    {
			for(Map.Entry<String,Wearable_Technology> entry : SaxParserDataStore.wearable_Technologies.entrySet())
				{
				 if(entry.getValue().getLabel().equals("Smart_Watches"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Smart_Watches";
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
			   	 name = "Headphones";
			}
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the Console and Console information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Consoles</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Wearable_Technology> entry : hm.entrySet())
		{
			Wearable_Technology wearable_Technology = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+wearable_Technology.getName()+"</h3>");
			pw.print("<strong>$"+wearable_Technology.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/consoles/"+wearable_Technology.getImage()+"' alt='' /></li>");
//			System.out.println(wearable_Technology.getImage());
			
			pw.print("<li><form method='post' action='Cart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearable_Technology'>"+
					"<input type='hidden' name='label' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='ProductCategory' value='wearable_Technology'>"+
					"<input type='hidden' name='RetailerName' value='"+wearable_Technology.getLabel()+"'>"+
					"<input type='hidden' name='ProductModelName' value='"+wearable_Technology.getName()+"'>"+
					"<input type='hidden' name='ProductPrice' value='"+wearable_Technology.getPrice()+"'>"+
					"<input type='hidden' name='discount' value='"+wearable_Technology.getDiscount()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearable_Technology'>"+
					"<input type='hidden' name='label' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+wearable_Technology.getPrice()+"'>"+
					"<input type='hidden' name='discount' value='"+wearable_Technology.getDiscount()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
