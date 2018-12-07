package smartPortables;

import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Wearable_Technology")


/* 
	Console class contains class variables name,price,image,retailer,condition,discount and Accessories Hashmap.

	Console class constructor with Arguments name,price,image,retailer,condition,discount and Accessories .
	  
	Accessory class contains getters and setters for name,price,image,retailer,condition,discount and Accessories .

*/

public class Wearable_Technology extends HttpServlet{
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

	HashMap<String,String> accessories;
	HashMap<String,String> warranties;
	public Wearable_Technology(String id, String name, double price, String image, String label,String condition, double discount){
		this.id = id;
		this.name=name;
		this.price=price;
		this.image=image;
		this.label=label;
		this.condition=condition;
		this.discount=discount;
        this.accessories=new HashMap<String,String>();
        this.warranties=new HashMap<String,String>();
	}
	
	public Wearable_Technology(String id, String name, double price, String image, String label, String condition,
			double discount, HashMap<String, String> accessories, HashMap<String, String> warranties) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.label = label;
		this.condition = condition;
		this.discount = discount;
		this.accessories = accessories;
		this.warranties = warranties;
	}
	
	public Wearable_Technology(String name, double price, String image, String label,String condition,double discount){
		this.name=name;
		this.price=price;
		this.image=image;
		this.condition=condition;
		this.discount = discount;
		this.label = label;
	}
	
    public HashMap<String, String> getWarranties() {
		return warranties;
	}

	public void setWarranties(HashMap<String, String> warranties) {
		this.warranties = warranties;
	}

	HashMap<String,String> getAccessories() {
		return accessories;
		}

	public Wearable_Technology(){
		
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

	public void setAccessories( HashMap<String,String> accessories) {
		this.accessories = accessories;
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
