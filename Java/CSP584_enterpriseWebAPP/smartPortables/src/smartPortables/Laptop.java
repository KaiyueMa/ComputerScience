package smartPortables;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Tablet")

/* 
	Tablet class contains class variables name,price,image,retailer,condition,discount.

	Tablet class has a constructor with Arguments name,price,image,retailer,condition,discount.
	  
	Tablet class contains getters and setters for name,price,image,retailer,condition,discount.
*/

public class Laptop extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private String image;
	private String label;
	private String condition;
	private double discount;
	private String saleDate;
	private int quantiltiesOfAvailable;
	private int numberOfItems;
	private int totalSales;
	
	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

	public int getQuantiltiesOfAvailable() {
		return quantiltiesOfAvailable;
	}

	public void setQuantiltiesOfAvailable(int quantiltiesOfAvailable) {
		this.quantiltiesOfAvailable = quantiltiesOfAvailable;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
	
	public Laptop(String name, double price, String image, String label, String condition,double discount){
		this.name=name;
		this.price=price;
		this.image=image;
		this.condition=condition;
		this.discount = discount;
		this.label = label;
	}
	

	
	public Laptop(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
