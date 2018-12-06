package IOtable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import sort.SortName4Search; 

//work as a IO between user and list
public class GuideInput {	

		Scanner scan = new Scanner(System.in);
		IOFile io_f=new IOFile();
		SortName4Search s4s=new SortName4Search();
		ArrayList<Coupon> alist=new ArrayList<Coupon>();
		static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		
		String prov_name;	//provider name
		String item_name;	//item name
		String s_time;		//expiration date
		String ini_price;	//price of item
		String act_price;	//price of using coupon
		String rate;			//discount rate
		String status;		//status of the coupon (used or not)
		String addOn;		//is this coupon add on other
		
		private int n=0;
		private boolean b_flag;
		private String s_flag;
		
	//print info to guide user to input Coupon
	public boolean start_input() throws ParseException {
			
		boolean success=true;
		
		while(true) {
			
			System.out.println("please input the information of the coupon");
			
			System.out.print("the Name of provider: ");
			prov_name = scan.nextLine();
			b_flag=str_length(prov_name, 20);
			if(b_flag) {}
			else {												//check input valid
				System.out.print("provider name out of boundry, "
						+ "do you want to input again?(Y/N)  ");
				s_flag= scan.nextLine();
				boolean flag=toboolean(s_flag);
				if(flag) {
					continue;
					}
				else {
					success=false;
					break;
					}
				}
			

			System.out.print("the Name of product: ");
			item_name=scan.nextLine();
			boolean n_flag=str_length(item_name, 20);
			if(n_flag) {}
			else {												//check input valid
				System.out.print("product name out of boundry, "
						+ "do you want to input again?(Y/N)  ");
				s_flag= scan.nextLine();
				boolean flag1=toboolean(s_flag);
				if(flag1) {
					continue;
					}
				else {
					success=false;
					break;
					}
				}

			
			System.out.print("the original price of the product: ");
			ini_price=scan.nextLine();
			if(!(ini_price.matches("^[0-9]+$"))) {				//check input valid
				System.out.print("a wrong price, "
						+ "do you want to input again?(Y/N)  ");
				s_flag= scan.nextLine();
				boolean flag=toboolean(s_flag);
				if(flag) {
					continue;
					}
				else {
					success=false;
					break;
					}
			}

			System.out.print("the rate of the coupon: ");
			rate=scan.nextLine();
			int str_rate=Integer.parseInt(rate);
			if((str_rate<0)||(str_rate>100)) {
					System.out.print("a wrong discount rate, "
							+ "do you want to input again?(Y/N) "); 
					s_flag= scan.nextLine();
					boolean flag=toboolean(s_flag);
					if(flag) {
						continue;
						}
					else {
						success=false;
						break;
						}
			}
		
		
			System.out.print("the expiration period:(yyyy-MM-dd) ");
			s_time=scan.nextLine();
			try {        
				format.setLenient(false);  
				Date date = format.parse(s_time);  
		      } 
			catch (Exception ex){  
															//check input valid 
				System.out.print("a wrong time, "
						+ "do you want to input again?(Y/N) ");  
				s_flag= scan.nextLine();
				boolean flag=toboolean(s_flag);
				if(flag) {
					continue;
				}
				else {
					success=false;
					break;
				}
			}
			Date coupon_date= new Date();
			coupon_date=StrToDate(s_time);		//check date if early than today, set false
			b_flag=cmp_date(coupon_date);
			if(b_flag) {
				System.out.println("the status of this coupon is false ");
				status="F";
			}
			else {
				System.out.print("the status of this coupon:(Unused:Y) ");
				status=scan.nextLine();
			}
		
			
			/*addon function, multiple coupons of the same provider and has same name, 
				then the final price will be discount based on total discount.*/
			System.out.print("is the coupon add on other coupons:(Y/N) ");
			addOn=scan.nextLine();
					
			String[] coupon = new String[] {			//link the input into String
					prov_name, item_name, ini_price, rate, act_price, s_time, status, addOn
			};

			//check the input, find if there is same item with same provider has different price.
			boolean input_correct=inputCorrect(prov_name, item_name, ini_price);
			if((input_correct==false)) {
				System.out.print("Same item has two prices, WRONG, "
						+ "do you want to input again?(Y/N) "); 
				s_flag= scan.nextLine();
				boolean flag=toboolean(s_flag);
				if(flag) {
					continue;
					}
				else {
					success=false;
					break;
					}
			}
			toCop(coupon);
					
		break;
		}
		
		
		return success;
	}
	
		//check the input, find if there is same item with same provider has different price.
		public boolean inputCorrect(String p_name, String i_name, String price) {
			int i=0;
			int i_price=Integer.parseInt(price);
			alist=s4s.sort_itemname();
			while(true) {
				while(i<alist.size()) {
					if((alist.get(i).getProv_name().compareTo(p_name)==0)&&
						(alist.get(i).getItem_name().compareTo(i_name)==0)) {
						if(alist.get(i).getIni_price()==i_price) {
							return true;
						}
						else return false;
					}	
					else i++;
				}return true;
			}
		}
	
		//add coupon to master linked list
		public void toCop(String[] coupon) {
			Coupon cop;
			cop = new Coupon(coupon[0],coupon[1],coupon[2],coupon[3],
					coupon[4],coupon[5],coupon[6],coupon[7]);
			io_f.list.add(cop);

		}
		
		//in order to print coupon, using this function get input
		public String Link2List()
		{
			
			int input_line=io_f.list.size()-1;
			
			String coupon_full=io_f.list.get(input_line).prov_name+"----"+
					io_f.list.get(input_line).item_name+"----"+
					io_f.list.get(input_line).ini_price+"----"+
					io_f.list.get(input_line).rate+"----"+
					io_f.list.get(input_line).act_price+"----"+
					io_f.list.get(input_line).s_time+"----"+
					io_f.list.get(input_line).status+"----"+
					io_f.list.get(input_line).addOn;
			System.out.println("new coupon is: "+coupon_full);
			System.out.println();
			return coupon_full;
		}
	
		// test Yes or No, transfer it to boolean flag
		public boolean toboolean(String str_boolean) {
			if(str_boolean.equals("Y")||str_boolean.equals("y")) {
				return true;
			}
			else {
				return false;
			}
		}
		
		// set the max length of the input String
		public boolean str_length(String str, int fixlength) {
			int thislength=str.length();
			if(thislength>fixlength) {return false;}
			else {return true;}
			}
		
		// transfer str to date
		public static Date StrToDate(String str) {
			  
			  Date date = null;
			  try {
				  date = format.parse(str);
			  	}
			  catch (ParseException e) {
				  e.printStackTrace();
			  	}
			  	return date;
			}
		
		// compare date to today
		public boolean cmp_date(Date coupon_date) throws ParseException {
		
			Calendar cal = Calendar.getInstance();// get today
			String strnow= format.format(cal.getTime());
			Date now=format.parse(strnow);
//			System.out.println("Today is:" +now );
			if (coupon_date.before(now)){return true;}//before today
			else{return false;}
		}
		// update exist statue by compare date
		public boolean update_statue_4exist(String exist_date) throws ParseException {
			Date exist_coupon_date= new Date();
			exist_coupon_date=StrToDate(exist_date);
			b_flag=cmp_date(exist_coupon_date);
			if(b_flag) {
				return false;
			}
			else {
				return true;
			}
		}
}
