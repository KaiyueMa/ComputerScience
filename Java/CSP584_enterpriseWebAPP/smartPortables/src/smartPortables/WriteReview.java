package smartPortables;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/WriteReview")
 //once the user clicks writereview button from products page he will be directed
  //to write review page where he can provide reqview for item rating reviewtext 
 
public class WriteReview extends HttpServlet {
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
  response.setContentType("text/html");
  PrintWriter pw = response.getWriter();
         Utilities utility= new Utilities(request, pw);
  review(request, response);
 }
 
 protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         try
                {
                   
                response.setContentType("text/html");
  PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
          
  if(!utility.isLoggedin()){
   HttpSession session = request.getSession(true);    
   session.setAttribute("login_msg", "Please Login to Write a Review");
   response.sendRedirect("Login");
   return;
  }
                
			  	String ProductModelName = request.getParameter("ProductModelName");
			  	String ProductCategory = request.getParameter("ProductCategory");
			  	String ProductPrice = request.getParameter("ProductPrice");
			  	String RetailerName = request.getParameter("RetailerName");
			  	String RetailerZip = request.getParameter("ProductPrice");
			  	String RetailerCity = request.getParameter("ProductPrice");
			  	String RetailerState = request.getParameter("ProductPrice");
			  	String ProductOnSale = request.getParameter("discount");
			  	String ManufacturerName = request.getParameter("ProductPrice");
			  	String ManufacturerRebate = request.getParameter("discount");
			  	String UserID = request.getParameter("ProductPrice");
			  	String UserAge = request.getParameter("ProductPrice");
			  	String UserGender = request.getParameter("ProductPrice");
			  	String UserOccupation = request.getParameter("ProductPrice");
//			  	String ReviewRating = request.getParameter("ProductPrice");
//			  	String ReviewDate = request.getParameter("ProductPrice");
			  	String ReviewText = request.getParameter("ProductPrice");
                
                String warranty = request.getParameter("warranty");
                String productprice=request.getParameter("price");
         
      // on filling the form and clicking submit button user will be directed to submit review page
                utility.printHtml("Header.html");
  utility.printHtml("LeftNavigationBar.html");
  pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
                pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
  pw.print("<a style='font-size: 24px;'>Review</a >");
  pw.print("</h2><div class='entry'>");
                pw.print("<table class='gridtable'>");
  pw.print("<tr><td> Product Name: </td><td>");
  pw.print(ProductModelName);
  pw.print("<input type='hidden' name='ProductModelName' value='"+ProductModelName+"'>");
  pw.print("</td></tr>");
         pw.print("<tr><td> Product Type:</td><td>");
                pw.print(ProductCategory);
         pw.print("<input type='hidden' name='ProductCategory' value='"+ProductCategory+"'>");
  pw.print("</td></tr>");
  
  
//  pw.print("<tr><td> Retailer Name:</td><td>");
//        pw.print(RetailerName);
//        pw.print("<input type='hidden' name='RetailerName' value='"+RetailerName+"'>");
//        pw.print("</td></tr>");  
//  
//        pw.print("<tr><td> Retailer Zip:</td><td>");
//        pw.print(RetailerZip);
//        pw.print("<input type='hidden' name='RetailerZip' value='"+RetailerZip+"'>");
//        pw.print("</td></tr>"); 
//        
//        pw.print("<tr><td> Retailer City:</td><td>");
//        pw.print(RetailerCity);
//        pw.print("<input type='hidden' name='RetailerCity' value='"+RetailerCity+"'>");
//        pw.print("</td></tr>"); 
//        
//        pw.print("<tr><td> RetailerState:</td><td>");
//        pw.print(RetailerState);
//        pw.print("<input type='hidden' name='RetailerState' value='"+RetailerState+"'>");
//        pw.print("</td></tr>"); 
//        
//        pw.print("<tr><td> ProductOnSale :</td><td>");
//        pw.print(ProductOnSale);
//        pw.print("<input type='hidden' name='ProductOnSale' value='"+ProductOnSale+"'>");
//        pw.print("</td></tr>"); 
//        
		  pw.print("<tr><td> Retailer Name:</td><td>");
		  pw.print(RetailerName);
		  pw.print("<input type='hidden' name='RetailerName' value='"+RetailerName+"'>");
		  pw.print("</td></tr>");	
		  
		  pw.print("<tr><td> Product Price:</td><td>");
		  pw.print(ProductPrice);
		  pw.print("<input type='hidden' name='ProductPrice' value='"+ProductPrice+"'>");
		  pw.print("</td></tr>");

		  pw.print("<tr><td> Product Discount:</td><td>");
		  pw.print(ProductOnSale + "%");
		  pw.print("<input type='hidden' name='ProductOnSale' value='"+ProductOnSale+"'>");
		  pw.print("</td></tr>");
		  
		  
//        
//        pw.print("<tr><td> ManufacturerName:</td><td>");
//        pw.print(ManufacturerName);
//        pw.print("<input type='hidden' name='ManufacturerName' value='"+ManufacturerName+"'>");
//        pw.print("</td></tr>"); 
//        
//        pw.print("<tr><td> UserID :</td><td>");
//        pw.print(UserID);
//        pw.print("<input type='hidden' name='UserID' value='"+UserID+"'>");
//        pw.print("</td></tr>"); 
//        
//        pw.print("<tr><td> UserGender :</td><td>");
//        pw.print(UserGender);
//        pw.print("<input type='hidden' name='UserGender' value='"+UserGender+"'>");
//        pw.print("</td></tr>"); 
//        
//        pw.print("<tr><td> UserOccupation :</td><td>");
//        pw.print(UserOccupation);
//        pw.print("<input type='hidden' name='UserOccupation' value='"+UserOccupation+"'>");
//        pw.print("</td></tr>"); 
//        
//        pw.print("<tr><td> ReviewRating :</td><td>");
//        pw.print(ReviewRating);
//        pw.print("<input type='hidden' name='ReviewRating' value='"+ReviewRating+"'>");
//        pw.print("</td></tr>"); 
//        
//        pw.print("<tr><td> ReviewDate :</td><td>");
//        pw.print(ReviewDate);
//        pw.print("<input type='hidden' name='ReviewDate' value='"+ReviewDate+"'>");
//        pw.print("</td></tr>"); 
        
//  pw.print("<tr><td> UserAge:</td><td>");
//                pw.print(UserAge);
//         pw.print("<input type='hidden' name='UserAge' value='"+UserAge+"'>");
//  pw.print("</td></tr>");  
//                pw.print("<tr><td> ReviewText: </td><td>");
//                pw.print(ReviewText);
//  pw.print("<input type='hidden' name='ReviewText' value='"+ReviewText+"'>");
//                pw.print("</td></tr><table>");
//  
//      pw.print("<table><tr></tr><tr></tr><tr><td> Review Rating: </td>");
//     pw.print("<td>");
//      pw.print("<select name='reviewrating'>");
//      pw.print("<option value='1' selected>1</option>");
//      pw.print("<option value='2'>2</option>");
//      pw.print("<option value='3'>3</option>");
//      pw.print("<option value='4'>4</option>");
//      pw.print("<option value='5'>5</option>");  
//     pw.print("</td></tr>");
//     
//     
//     pw.print("<tr><td> Sex: </td><td>");
//     pw.print("<select name='reviewrating'>");
//     pw.print("<option value='1' selected>male</option>");
//     pw.print("<option value='2'>female</option>");
//    pw.print("</td></tr>");
//    
//    
//    
//     pw.print("<tr>");
//     pw.print("<td> Retailer Zip Code: </td>");
//     pw.print("<td> <input type='text' name='zipcode'> </td>");
//           pw.print("</tr>"); 
//           
//           pw.print("<tr>");
//     pw.print("<td> UserOccupation: </td>");
//     pw.print("<td> <input type='text' name='UserOccupation'> </td>");
//           pw.print("</tr>"); 
//
//
//     pw.print("<tr>");
//     pw.print("<td> Retailer City: </td>");
//     pw.print("<td> <input type='text' name='retailercity'> </td>");
//           pw.print("</tr>");  
//           
//           pw.print("<tr>");
//     pw.print("<td> State: </td>");
//     pw.print("<td> <input type='text' name='State'> </td>");
//           pw.print("</tr>");
//           
//           pw.print("<tr>");
//     pw.print("<td> Age: </td>");
//     pw.print("<td> <input type='text' name='Age'> </td>");
//           pw.print("</tr>"); 
           
        pw.print("</td></tr><table>");
		
			pw.print("<table><tr></tr><tr></tr><tr><td> ReviewRating: </td>");
			pw.print("<td>");
				pw.print("<select name='ReviewRating'>");
				pw.print("<option value='1' selected>1</option>");
				pw.print("<option value='2'>2</option>");
				pw.print("<option value='3'>3</option>");
				pw.print("<option value='4'>4</option>");
				pw.print("<option value='5'>5</option>");  
			pw.print("</td></tr>");
			
			
			pw.print("<tr><td> UserGender: </td><td>");
			pw.print("<select name='UserGender'>");
			pw.print("<option value='male' selected>male</option>");
			pw.print("<option value='female'>female</option>");
		pw.print("</td></tr>");
		
		
		
			pw.print("<tr>");
			pw.print("<td> Retailer Zip Code: </td>");
			pw.print("<td> <input type='text' name='RetailerZip'> </td>");
	        pw.print("</tr>");	
	        
	        pw.print("<tr>");
			pw.print("<td> UserOccupation: </td>");
			pw.print("<td> <input type='text' name='UserOccupation'> </td>");
	        pw.print("</tr>");	


			pw.print("<tr>");
			pw.print("<td> RetailerCity: </td>");
			pw.print("<td> <input type='text' name='RetailerCity'> </td>");
	        pw.print("</tr>");		
	        
	        pw.print("<tr>");
			pw.print("<td> RetailerState: </td>");
			pw.print("<td> <input type='text' name='RetailerState'> </td>");
	        pw.print("</tr>");
	        
	        pw.print("<tr>");
			pw.print("<td> UserAge: </td>");
			pw.print("<td> <input type='text' name='UserAge'> </td>");
	        pw.print("</tr>");	     
           
         
  
	        pw.print("<tr>");
	        pw.print("<td> Review Date: </td>");
	        pw.print("<td> <input type='date' name='ReviewDate'> </td>");
	        pw.print("</tr>");    

	        pw.print("<tr>");
	        pw.print("<td> Review Text: </td>");	
	        pw.print("<td><textarea name='ReviewText' rows='4' cols='50'> </textarea></td></tr>");
	        pw.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='SubmitReview' value='SubmitReview'></td></tr></table>");

	        pw.print("</h2></div></div></div>");  
  utility.printHtml("Footer.html");
                       
                    }
               catch(Exception e)
  {
                 System.out.println(e.getMessage());
  }     
       
   
  }
 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
  response.setContentType("text/html");
  
  PrintWriter pw = response.getWriter();
  
  Utilities utility= new Utilities(request, pw);
  review(request, response);
            }
}