package IOtable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Coupon {
	public String prov_name;	//provider name
	public String item_name;	//item name
	public double ini_price;	//price of item
	public int rate;			//discount rate
	public double act_price;	//price of using coupon
	public String s_time;		//expiration date
	public boolean status;		//status of the coupon (used or not)
	public boolean addOn;		//is this coupon add on other
	GuideInput guide = new GuideInput();
	

	//getter setter function
	//provider name
	public String getProv_name() {return prov_name;}

	//item name
	public String getItem_name() {return item_name;}
	public void setItem_name(String item_name) {this.item_name = item_name;}
		
	//initial price of the item
	public double getIni_price() {return ini_price;}
	public void setIni_price(double ini_price) {this.ini_price = ini_price;}
	
	//discount rate
	public int getRate() {return rate;}

	//price of using coupon
	public double getAct_price() {return act_price;}

	//expiration time
	public String getS_time() {return s_time;}

	//status of the item
	public boolean getStatus() {return status;}
	public void setStatus(boolean status) {this.status = status;}

	//is this item add on other
	public boolean getAddOn() {return addOn;}
	public void setAddOn(boolean addOn) {this.addOn = addOn;}
	public String geteveything() {
		String whole_list=getProv_name()+"---"+getItem_name()+
				"---"+getIni_price()+"---"+getRate()+"---"+
				getAct_price()+"---"+getS_time()+"---"+
				getStatus()+"---"+getAddOn();
		return whole_list;
	}
	
	//encapsulation coupon
	public Coupon(String prov_name, String item_name, String Ini_price, String rate, String Act_price, String s_time, String status, String addOn) {
		this.prov_name = prov_name;
		this.item_name = item_name;
		this.ini_price = Double.valueOf(Ini_price);
		int str_rate=Integer.parseInt(rate);
		
		this.rate = str_rate;

		this.act_price =  ini_price*(100-str_rate)/100;
		this.s_time = s_time;
		this.status = toboolean(status);
		this.addOn = toboolean(addOn);
	}
	public boolean toboolean(String str_boolean) {
		if(str_boolean.equals("true")||str_boolean.equals("True")
				||str_boolean.equals("Y")||str_boolean.equals("y")) {
			return true;
		}
		else {
			return false;
		}
	} 
}
