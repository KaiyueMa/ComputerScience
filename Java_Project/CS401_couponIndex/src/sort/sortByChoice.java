package sort;

import java.util.*;
import java.io.*;
import IOtable.*;

public class sortByChoice {
	Scanner scan = new Scanner(System.in);
	IOtable.IOFile io_f=new IOtable.IOFile();
	IOtable.GuideInput manual_Input=new IOtable.GuideInput();
	SortName4Search sortName = new SortName4Search();
	cmp cp_sort=new cmp();
	
	LinkedList<Coupon> list1 = new LinkedList<Coupon>(io_f.list);
	public ArrayList<Coupon> list_name=new ArrayList<Coupon>();
	int l=io_f.list.size();
	
	public void sort_io() {
//		System.out.println(io_f.list.get(1).geteveything());
		list1=io_f.list;
//		System.out.println(list1.get(1).geteveything());
		
		toboolean tb=new toboolean();
		System.out.print("Do you want to sort the coupon?(Y/N)   ");
		boolean sortflag= tb.toboolean();
		if(sortflag) {
			int sortBaseOn=what_sort();

			if(sortBaseOn!=99) {
				System.out.print("ascending or descending?(a/d)   ");
				String aORd= scan.nextLine();
				if(sortBaseOn==0){//sort based on initial price
					System.out.println("Sort based on initial price");
					sortIni(aORd);
				}
				else if(sortBaseOn==1){//sort based on act price
					System.out.println("Sort based on rate price");

					sortAct(aORd);
				}
				else if(sortBaseOn==2){//sort based on rate
					System.out.println("Sort based on discount rate");
					sortRate(aORd);
				}
			}
			else {//sort based on name
				ArrayList<Coupon> alist=new ArrayList<Coupon>();
				alist=sortName.sort_itemname();
				for(int k=0;k<alist.size(); k++) {
					System.out.println(alist.get(k).geteveything());}
					System.out.println();
			}
		}
		else{
			System.out.println("Not Sort");
		}
	}
	
	public void sortIni(String aORd) {
		if(aORd.equals("a")) {
			sort_ini(1);
		}
		else {
			sort_ini(0);
		}
	}
	public void sort_ini(int j) {
		IOtable.GuideInput manual_Input=new IOtable.GuideInput();
		
		cmp cp_sort=new cmp();
		IOFile io=new IOFile();
		int l=io.list.size();
		
//		int flag=cp_sort.compare(IOFile.list.get(0), IOFile.list.get(1));
		if(j==1) {//升序
			Collections.sort(list1, new Comparator<Coupon>(){
				public int compare(Coupon c0, Coupon c1) {  
					if(c0.ini_price!=c1.ini_price) {
						return (int) (c0.ini_price-c1.ini_price);
						}
					else {
						return c0.item_name.compareTo(c1.item_name);
						} 
	            		}
				});
			printSort();
//			System.out.println("Sort result is:(item_name---initial_price"
//					+ "---actual_price---rate)");
//			for(int i=0;i<io.list.size();i++) {
//				System.out.println(IOFile.list.get(i).getItem_name()+
//						"    "+IOFile.list.get(i).getIni_price()+"    "
//						+IOFile.list.get(i).getAct_price()+"    "
//						+IOFile.list.get(i).getRate());
//			}
		}
	
		else {
			Collections.sort(list1, new Comparator<Coupon>(){
				public int compare(Coupon c0, Coupon c1) {  
					if(c0.ini_price!=c1.ini_price) {
						return (int) (c1.ini_price-c0.ini_price);
						}
					else {
						return c1.item_name.compareTo(c0.item_name);
						} 
	            		}
			});
			printSort();
//			System.out.println("Sort result is:(item_name---initial_price"
//					+ "---actual_price---rate)");
//			for(int i=0;i<io.list.size();i++) {
//				System.out.println(IOFile.list.get(i).getItem_name()+
//						"    "+IOFile.list.get(i).getIni_price()+"    "
//						+IOFile.list.get(i).getAct_price()+"    "
//						+IOFile.list.get(i).getRate());
//			}
		}
}
	
	public void sortAct(String aORd) {
		if(aORd.equals("a")) {
			sort_act(1);
		}
		else {
			sort_act(0);
		}
	}
	public void sort_act(int j) {

		if(j==1) {//升序
			Collections.sort(list1, new Comparator<Coupon>(){
				public int compare(Coupon c0, Coupon c1) {  
					if(c0.act_price!=c1.act_price) {
						return new Double(c0.act_price).compareTo(new Double(c1.act_price));
						}
					else {
						return c0.item_name.compareTo(c1.item_name);
						} 
	            		}
				});
			printSort();
//			System.out.println("Sort result is:(item_name---initial_price"
//					+ "---actual_price---rate)");
//			for(int i=0;i<io.list.size();i++) {
//				System.out.println(IOFile.list.get(i).getItem_name()+
//						"    "+IOFile.list.get(i).getIni_price()+"    "
//						+IOFile.list.get(i).getAct_price()+"    "
//						+IOFile.list.get(i).getRate());
//			}
		}
	
		else {
			Collections.sort(list1, new Comparator<Coupon>(){
				public int compare(Coupon c0, Coupon c1) {  
					if(c0.act_price!=c1.act_price) {
						return new Double(c1.act_price).compareTo(new Double(c0.act_price));
						}
					else {
						return c1.item_name.compareTo(c0.item_name);
						} 
	            		}
			});
			printSort();
//			System.out.println("Sort result is:(item_name---initial_price"
//					+ "---actual_price---rate)");
//			for(int i=0;i<io.list.size();i++) {
//				System.out.println(IOFile.list.get(i).getItem_name()+
//						"    "+IOFile.list.get(i).getIni_price()+"    "
//						+IOFile.list.get(i).getAct_price()+"    "
//						+IOFile.list.get(i).getRate());
//			}
		}
}
		
	public void sortRate(String aORd) {
		if(aORd.equals("a")) {
			sort_rate(1);
		}
		else {
			sort_rate(0);
		}
	}
	public void sort_rate(int j) {
		IOtable.GuideInput manual_Input=new IOtable.GuideInput();
		
		cmp cp_sort=new cmp();
		IOFile io=new IOFile();
		int l=io.list.size();
		
//		int flag=cp_sort.compare(IOFile.list.get(0), IOFile.list.get(1));
		if(j==1) {//ascending
			Collections.sort(list1, new Comparator<Coupon>(){
				public int compare(Coupon c0, Coupon c1) {  
					if(c0.rate!=c1.rate) {
						return (int) (c0.rate-c1.rate);
						}
					else {
						return c0.item_name.compareTo(c1.item_name);
						} 
	            		}
				});
			printSort();

		}
	
		else {//descending
			Collections.sort(list1, new Comparator<Coupon>(){
				public int compare(Coupon c0, Coupon c1) {  
					if(c0.rate!=c1.rate) {
						return (int) (c1.rate-c0.rate);
						}
					else {
						return c1.item_name.compareTo(c0.item_name);
						} 
	            		}
			});
			printSort();

		}
}


	//select search 
	public int what_sort() {
		System.out.println("Sort by what properity?");
		System.out.print("Following this order"
				+ "(initial_price:i   real_price:r   discount_rate:d)    ");
		String w_s=scan.nextLine();
		switch(w_s) {
			case "i":
				return 0;
			case "r":
				return 1;
			case "d":
				return 2;
			}
		System.out.println("DEFAULT: sort by item name (ascendent)");
		return 99;
	}
	
	//print sort results
	public void printSort() {
		System.out.println("Sort result is:");
		System.out.println("(item_name---initial_price---actual_price---"
				+ "rate---expiration_date)");
		for(int i=0;i<list1.size();i++) {
			System.out.println("     "+list1.get(i).getItem_name()+
					"           "+list1.get(i).getIni_price()+"             "
					+list1.get(i).getAct_price()+"         "
					+list1.get(i).getRate()+"      "
					+list1.get(i).getS_time());
		}
	}
	
	class toboolean {
		private String s_flag;
		private boolean b_flag;
		Scanner scan = new Scanner(System.in);
		
			
		public boolean toboolean() {
			s_flag= scan.nextLine();
			if(s_flag.equals("Y")||s_flag.equals("y")) {
				return true;
			}
			else {
				return false;
			}
		}
		
		
	}
}
