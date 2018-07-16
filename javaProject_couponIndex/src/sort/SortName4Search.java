package sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import IOtable.Coupon;
import IOtable.IOFile;
import IOtable.cmp;

public class SortName4Search {
	cmp cp_sort=new cmp();
	IOFile io_f=new IOFile();
	LinkedList<Coupon> list1 = io_f.list;
	public ArrayList<Coupon> list_name=new ArrayList<Coupon>();
	int l=list1.size();
	
	
	//sort based on name
	public ArrayList<Coupon> sort_itemname() {		
		IOtable.GuideInput manual_Input=new IOtable.GuideInput();
		
		cmp cp_sort=new cmp();
		IOFile io=new IOFile();
		int l=io.list.size();

		Collections.sort(list1, new Comparator<Coupon>(){
			public int compare(Coupon c0, Coupon c1) {  
				if(c0.item_name!=c1.item_name) {			
					return c0.item_name.compareTo(c1.item_name);
					}
				else {
					return c0.prov_name.compareTo(c1.prov_name);
					} 
	         	}
			});
			for(int i=0;i<l;i++) {
				String iniprice=Double.toString(list1.get(i).ini_price);
				String rate=Integer.toString(list1.get(i).rate);
				String actprice=Double.toString(list1.get(i).act_price);
				String status=Boolean.toString(list1.get(i).status);
				String addon=Boolean.toString(list1.get(i).addOn);
				
				list_name.add(new Coupon(list1.get(i).prov_name, 
						list1.get(i).item_name, iniprice, rate, 
						actprice, list1.get(i).s_time, status, addon));

			}
		return list_name;
	}
}
