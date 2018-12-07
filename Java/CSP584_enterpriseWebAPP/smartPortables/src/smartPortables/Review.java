package smartPortables;

import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
//	private String productName;
//	private String userName;
//	private String productType;
//	private String productMaker;
//	private String reviewRating;
//	private String reviewDate;
//	private String reviewText;
//	private String retailerpin;
//	private String price;
//	private String retailercity;
	private String ProductModelName;
	private String ProductCategory;
	private String ProductPrice;
	private String RetailerName;
	private String RetailerZip;
	private String RetailerCit;
	private String RetailerState;
	private String ProductOnSale;
	private String ManufacturerName;
	private String ManufacturerRebate;
	private String UserID;
	private String UserAge;
	private String UserGender;
	private String UserOccupation;
	private String ReviewRating;
	private String ReviewDate;
	private String ReviewText;
	
	public Review(String productModelName, String productCategory, String productPrice, String retailerName,
			String retailerZip, String retailerCit, String retailerState, String productOnSale, String manufacturerName,
			String manufacturerRebate, String userID, String userAge, String userGender, String userOccupation,
			String reviewRating, String reviewDate, String reviewText) {
		super();
		ProductModelName = productModelName;
		ProductCategory = productCategory;
		ProductPrice = productPrice;
		RetailerName = retailerName;
		RetailerZip = retailerZip;
		RetailerCit = retailerCit;
		RetailerState = retailerState;
		ProductOnSale = productOnSale;
		ManufacturerName = manufacturerName;
		ManufacturerRebate = manufacturerRebate;
		UserID = userID;
		UserAge = userAge;
		UserGender = userGender;
		UserOccupation = userOccupation;
		ReviewRating = reviewRating;
		ReviewDate = reviewDate;
		ReviewText = reviewText;
	}
	
//	public Review (String productName,String userName,String productType,String productMaker,String reviewRating,String reviewDate,String reviewText,String retailerpin,String price,String retailercity){
//		this.productName=productName;
//		this.userName=userName;
//		this.productType=productType;
//		this.productMaker=productMaker;
//	 	this.reviewRating=reviewRating;
//		this.reviewDate=reviewDate;
//	 	this.reviewText=reviewText;
//		this.retailerpin=retailerpin;
//		this.price= price;
//		this.retailercity= retailercity;
//	}

//	public Review(String productName, String retailerpin, String reviewRating, String reviewText) {
//       this.productName = productName;
//       this.retailerpin = retailerpin;
//       this.reviewRating = reviewRating;
//       this.reviewText = reviewText;
//    }
	
	
	public String getProductModelName() {
		return ProductModelName;
	}



	public void setProductModelName(String productModelName) {
		ProductModelName = productModelName;
	}



	public String getProductCategory() {
		return ProductCategory;
	}



	public void setProductCategory(String productCategory) {
		ProductCategory = productCategory;
	}



	public String getProductPrice() {
		return ProductPrice;
	}



	public void setProductPrice(String productPrice) {
		ProductPrice = productPrice;
	}



	public String getRetailerName() {
		return RetailerName;
	}



	public void setRetailerName(String retailerName) {
		RetailerName = retailerName;
	}



	public String getRetailerZip() {
		return RetailerZip;
	}



	public void setRetailerZip(String retailerZip) {
		RetailerZip = retailerZip;
	}



	public String getRetailerCit() {
		return RetailerCit;
	}



	public void setRetailerCit(String retailerCit) {
		RetailerCit = retailerCit;
	}



	public String getRetailerState() {
		return RetailerState;
	}



	public void setRetailerState(String retailerState) {
		RetailerState = retailerState;
	}



	public String getProductOnSale() {
		return ProductOnSale;
	}



	public void setProductOnSale(String productOnSale) {
		ProductOnSale = productOnSale;
	}



	public String getManufacturerName() {
		return ManufacturerName;
	}



	public void setManufacturerName(String manufacturerName) {
		ManufacturerName = manufacturerName;
	}



	public String getManufacturerRebate() {
		return ManufacturerRebate;
	}



	public void setManufacturerRebate(String manufacturerRebate) {
		ManufacturerRebate = manufacturerRebate;
	}



	public String getUserID() {
		return UserID;
	}



	public void setUserID(String userID) {
		UserID = userID;
	}



	public String getUserAge() {
		return UserAge;
	}



	public void setUserAge(String userAge) {
		UserAge = userAge;
	}



	public String getUserGender() {
		return UserGender;
	}



	public void setUserGender(String userGender) {
		UserGender = userGender;
	}



	public String getUserOccupation() {
		return UserOccupation;
	}



	public void setUserOccupation(String userOccupation) {
		UserOccupation = userOccupation;
	}



	public String getReviewRating() {
		return ReviewRating;
	}



	public void setReviewRating(String reviewRating) {
		ReviewRating = reviewRating;
	}



	public String getReviewDate() {
		return ReviewDate;
	}



	public void setReviewDate(String reviewDate) {
		ReviewDate = reviewDate;
	}



	public String getReviewText() {
		return ReviewText;
	}



	public void setReviewText(String reviewText) {
		ReviewText = reviewText;
	}


//	public String getProductName() {
//		return productName;
//	}
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setProductName(String productName) {
//		this.productName = productName;
//	}
//
//	public String getProductType() {
//		return productType;
//	}
//
//	public void setProductType(String productType) {
//		this.productType = productType;
//	}
//
//	public String getProductMaker() {
//		return productMaker;
//	}
//
//	public void setProductMaker(String productMaker) {
//		this.productMaker = productMaker;
//	}
//
//	public String getReviewRating() {
//		return reviewRating;
//	}
//
//	public String getReviewText() {
//		return reviewText;
//	}
//	public void setReviewText(String reviewText) {
//		this.reviewText = reviewText;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public void setReviewRating(String reviewRating) {
//		this.reviewRating = reviewRating;
//	}
//	public String getReviewDate() {
//		return reviewDate;
//	}
//
//	public void setReviewDate(String reviewDate) {
//		this.reviewDate = reviewDate;
//	}
//    
//		public String getRetailerPin() {
//		return retailerpin;
//	}
//
//	public void setRetailerPin(String retailerpin) {
//		this.retailerpin = retailerpin;
//	}
//			public String getRetailerCity() {
//		return retailercity;
//	}
//
//	public void setRetailerCity(String retailercity) {
//		this.retailercity = retailercity;
//	}
//	
//			public String getPrice() {
//		return price;
//	}
//
//	public void setPrice(String price) {
//		this.price = price;
//	}
//
}
