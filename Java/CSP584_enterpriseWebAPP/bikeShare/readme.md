# Bike Share Web Application

CSP584's final project.

This is a ***shared bike*** web application. User could rent bike from our website. 

|Team Leader|Kaiyue Ma| kma5@hawk.iit.edu|
|---|:---|:---
|Author|Hangqi Dai| hdai8@hawk.iit.edu|
|Author|Sanchuang Jiang| sjiang24@hawk.iit.edu|

* The website has a page shows all bikes.
* For user:
	* Users can log in and need to enter password and verification code when logging in.
	* After logging in, the user can view the user's personal information, including all the information registered, and the user can update the user information and login password.
	* On the order interface the user can view existing orders.
	* The homepage also has a button for the user to click into the sing to see the order.
	* On the wallet interface, users can view the account balance and recharge the account. Here is another deposit of 300 bucks.
	* User can log out.
	* The user clicks on the rental button and enters the rental bicycle interface.
	* The rental bike interface line displays bicycle information, including the name of the bicycle, the bicycle category, the pick-up location, the number of remaining vehicles available, and discount information. Users can also see the description of the bicycle and the comments of the bicycle on this interface.
	* Clicking on the rental bike requires the user to confirm the order again.
	* After confirming, it shows that the rental bike is successful.
	* After using the bicycle, the user clicks return to return the bicycle.
	* After returning, the user can choose to use wallet payment or cash payment.
* For stuff:
	* After the staff confirms (after getting the bike at the store), the calculation time starts.
	* The staff manages user orders, including dispatching bicycles to users, reclaiming bicycles.
	* The staff manages bicycles, including increasing bicycle inventory, reducing bicycle inventory, and adding a bicycle category.
	* Staff can log out.
* For administrator
	* Administrators can view all users and staff and be able to delete corresponding user or staff information.
	* Administrators can add, delete, and change discount information that will be added by staff to the corresponding item.
	* Administrator can log out.



## Environment
Java 7, Tomcat 7.0.34 and MySQL are requried