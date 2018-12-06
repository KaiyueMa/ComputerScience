package Search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import IOtable.Coupon;
import IOtable.IOFile;

public class linerS {
	private static Scanner scan;
	IOFile io_f=new IOFile();
	
	LinkedList<Coupon> list1 = io_f.list;
	ArrayList<Coupon> found_list=new ArrayList<Coupon>();

	//linear search IO
	public void liner_search(String toSearch) {
		int length=io_f.list.size();
//		String toSearch=getInput();
		if(isfound(toSearch)) {
			if(found_list.size()>1) {
				
			}
		}
		else {
			length++;
			System.out.println("Linear search does NOT find the item in "+length+" time");
		}
		
	}
	
	// search function
	public boolean isfound(String toSearch) {
		int i=0,j;
		int length=io_f.list.size();
		boolean foundFlag=false; 
		boolean return_flag=false;
		while(i<length) {
			foundFlag=foundMoreThan1(i, toSearch);	//maybe there are more than one coupons
				i++;
			if(foundFlag) {
				return_flag=true;
			}
			else {}
			}
		if(found_list.size()>1) {
			test_addon();
		}
		return return_flag;
	}
	
	public boolean foundMoreThan1(int i, String toSearch) {
		int search_time=0;
		if(toSearch.equals(list1.get(i).getItem_name())) {
			search_time=i+1;
			System.out.print("Found in "+search_time+"th in LinearSearch ");
			System.out.println(list1.get(i).geteveything());
			
			String iniprice=Double.toString(list1.get(i).ini_price);
			String rate=Integer.toString(list1.get(i).rate);
			String actprice=Double.toString(list1.get(i).act_price);
			String status=Boolean.toString(list1.get(i).status);
			String addon=Boolean.toString(list1.get(i).addOn);
			
			found_list.add(new Coupon(list1.get(i).prov_name, 
					list1.get(i).item_name, iniprice, rate, 
					actprice, list1.get(i).s_time, status, addon));
			return true;
		}
		else return false;
	}
	
	public void test_addon() {		//find coupon(s) and save
		int size=found_list.size();
		
		if(found_list.get(0).getAddOn()) {
			double final_price=found_list.get(0).getIni_price();
			for(int i=0;i<size;i++) {
				final_price*=((found_list.get(i).getRate())/(double)100);
			}
			
			System.out.println("After using"+found_list.size()+" coupons which could "
					+ "add on others, the final price of the item "+
					found_list.get(0).getItem_name()+"  is"+final_price);
		}
	}
	
//	public String getInput() {
//		System.out.print("Please input coupon information: ");
//		scan = new Scanner(System.in);
//		String coupon_info=scan.nextLine();
//		return coupon_info;
//	}
}
