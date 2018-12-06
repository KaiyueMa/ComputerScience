import java.util.*;
import java.io.*;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import IOtable.*;
import Search.bstS;
import sort.sortByChoice;

public class mainf {
	private static Scanner scan;
	
	static IOtable.IOFile 	io_f = new IOtable.IOFile();
	static IOtable.GuideInput manual_Input = new IOtable.GuideInput();
	static sort.sortByChoice sort = new sort.sortByChoice();
	static Search.binaryS bSearch = new Search.binaryS();
	static Search.linerS lSearch = new Search.linerS();
	static Search.bstS bstSearch = new bstS();
	static toboolean toBool = new toboolean();
	static boolean flag;
	
	
	static LinkedList<Coupon> list1= new LinkedList<Coupon>();
	public static void main(String[] args) throws Exception {
		
		//read from the file, if the file is not exist, build it
		System.out.print("Please input the file name: ");
		scan = new Scanner(System.in);
		String s_file_name=scan.nextLine();
		File file_name=new File(s_file_name);
		io_f.readFrom_OR_buildFile(file_name);
		
		list1=io_f.list;	
		
		//Manual input coupon information
		System.out.println("------Manual add Coupon------");
		addOrNot(file_name);
		
		if(list1.size()!=0) {	//if there's no coupon, no function to manipulate the coupon
		//Sort coupon information
		System.out.println("--------Sort Coupon--------");
		sort.sort_io();
		
		//Search coupon
		System.out.println("--------Searching Coupon--------");
		searchFunction();
		}
		else {
			System.out.println("There's no coupon can't Search or Sort.");
		}
		
	}
	
	public static void addOrNot(File file_name) throws Exception {	//manually input 
		System.out.print("Do you want to add coupon? (Y/N)    ");
		flag=toBool.toboolean();
		if(flag)
		{
			while(flag) {
				boolean input_success=manual_Input.start_input();	//read from user
				if(input_success) {
					String buildlist=manual_Input.Link2List();
					IOFile.writeTo_OR_bulidFile(file_name, buildlist);
					System.out.print("add more?(Y/N)    ");			//add one more
					flag=toBool.toboolean();
				}
				else {break;}
			}
			System.out.println("End manual add");
		}
		else if(!flag){
			System.out.println("Not manual add coupon");
			System.out.println();
		}
	}

	public static void searchFunction() {		//choice search function form binary/linear/BST
		String toSearch=getInput();
		System.out.println("Using which search funtion?");
		System.out.print("Following this order"
				+ "(LinerSearch:l   BinarySearch:b   BSTSearch:bst)  ");
		scan = new Scanner(System.in);
		String w_s=scan.nextLine();
		if(w_s.equals("l")||w_s.equals("b")||w_s.equals("bst")) {	//choice search 
			switch(w_s) {
			case "l":
				lSearch.liner_search(toSearch);
				break;
			case "b":
				bSearch.binary_search(toSearch);
				break;
			case "bst":
				bstSearch.BST_search(toSearch);
				break;
			}
		}
		else {		//if none of those are selected, use default search function
			System.out.println("Using default search funcion (liner Search)");
			lSearch.liner_search(toSearch);	
		}		
	}
	public static String getInput() {	//get info from user
		System.out.print("Please input coupon information: ");
		scan = new Scanner(System.in);
		String coupon_info=scan.nextLine();
		return coupon_info;
	}
}


