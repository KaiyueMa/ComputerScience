package smartPortables;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/SalesReport")

public class SalesReport  extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		String warranty = request.getParameter("warranty");
		System.out.print("name" + name + "type" + type + "maker" + maker + "accesee" + access + "warranty" + warranty);

	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
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
		
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>SalesReport</a>");
		pw.print("</h2><div class='entry'>");

			pw.print("<h4 style='color:black'>Daily Sales Table : </h4>");
			pw.print("<form name ='OnsaleProducts' action='BarChart' method='post'>");
				pw.print("<table  class='gridtable'>");
				int j = 1;

				pw.print("<td>Date</td><td>Total Sales</td>");
				for(Map.Entry<String, Wearable_Technology> entry : hm.entrySet()){
					Wearable_Technology console = entry.getValue();
					
					pw.print("<tr>");
					pw.print("<td>"+console.getSaleDate()+"</td><td>" +  console.getQuantiltiesOfAvailable() +"</td>" );
					pw.print("<input type='hidden' name='orderName' value='"+console.getSaleDate()+"'>");
					pw.print("<input type='hidden' name='orderIndex' value='"+ j +"'>");
					pw.print("<input type='hidden' name='orderPrice' value='"+console.getQuantiltiesOfAvailable()+"'>");		
					pw.print("</tr>");	
					j++;
				}
			
				pw.print("</table></form>");
				
				pw.print("<h4 style='color:black'>Sold Table : </h4>");
				pw.print("<form name ='AvailableProducts' action='BarChart' method='post'>");
					pw.print("<table  class='gridtable'>");
					int i = 1;

					pw.print("<td>Product Name</td><td>Price</td><td>Item Number</td><td>Total Sales</td>");
					for(Map.Entry<String, Wearable_Technology> entry : hm.entrySet()){
						Wearable_Technology console = entry.getValue();
						
						pw.print("<tr>");
						pw.print("<td>"+console.getName()+"</td><td>: "+console.getPrice()+"</td></td><td>"+ console.getNumberOfItems() +"</td><td>" + console.getTotalSales() + "</td>" );
						pw.print("<input type='hidden' name='orderName' value='"+console.getName()+"'>");
						pw.print("<input type='hidden' name='orderIndex' value='"+ i +"'>");
						pw.print("<input type='hidden' name='numberOfItems' value='"+console.getNumberOfItems()+"'>");
						pw.print("<input type='hidden' name='totalSales' value='"+console.getTotalSales()+"'>");		
						pw.print("</tr>");	
						i++;
					}
				
					pw.print("</table></form>");
					
			        pw.print("<div id='content'><div class='post'>");
			        pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Sales BarChart Analysis : </a></h2>"
			                + "<div class='entry'>");
			        pw.println("<div id='chart_div'></div>");
			        pw.println("</div></div></div>");
			        pw.println("  <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n" + 
			        		"    <script type=\"text/javascript\">\r\n" + 
			        		"\r\n" + 
			        		"      // Load the Visualization API and the corechart package.\r\n" + 
			        		"      google.charts.load('current', {'packages':['corechart']});\r\n" + 
			        		"\r\n" + 
			        		"      // Set a callback to run when the Google Visualization API is loaded.\r\n" + 
			        		"      google.charts.setOnLoadCallback(drawChart);\r\n" + 
			        		"\r\n" + 
			        		"      // Callback that creates and populates a data table,\r\n" + 
			        		"      // instantiates the pie chart, passes in the data and\r\n" + 
			        		"      // draws it.\r\n" + 
			        		"      function drawChart() {\r\n" + 
			        		"\r\n" + 
			        		"        // Create the data table.\r\n" + 
			        		"        var data = new google.visualization.DataTable();\r\n" + 
			        		"        data.addColumn('string', 'Topping');\r\n" + 
			        		"        data.addColumn('number', 'Number');\r\n");  
			        		
			        for(Map.Entry<String, Wearable_Technology> entry : hm.entrySet()){
						Wearable_Technology console = entry.getValue();
						pw.println("        data.addRows([\r\n" + 
				        		"          ['"+ console.getName()+"',"+ console.getTotalSales() + "],\r\n" + 
				        		"        ]);");
			        }		
			        
			        
			        pw.println("        // Set chart options\r\n" + 
			        		"        var options = {'title':'Totatl Sales',\r\n" + 
			        		"                       'width':700,\r\n" + 
			        		"                       'height':600};\r\n" + 
			        		"\r\n" + 
			        		"        // Instantiate and draw our chart, passing in some options.\r\n" + 
			        		"        var chart = new google.visualization.BarChart(document.getElementById('chart_div'));\r\n" + 
			        		"        chart.draw(data, options);\r\n" + 
			        		"      }\r\n" + 
			        		"    </script>");
			        
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");

	}
}
