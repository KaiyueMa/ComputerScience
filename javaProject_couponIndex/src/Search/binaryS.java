package Search;

import java.util.*;
import javax.swing.text.AbstractDocument.LeafElement;
import java.lang.*;
import IOtable.*;
import sort.SortName4Search;
import sort.sortByChoice;

public class binaryS {
	int search_time=1;
	
	IOFile io_f=new IOFile();
	SortName4Search s4s=new SortName4Search();
	LinkedList<Coupon> trial = io_f.list;
	ArrayList<Coupon> alist=new ArrayList<Coupon>();

	//binary search IO
	public boolean binary_search(String toSearch) {
		int length=io_f.list.size();
		int mid=length/2;
		boolean bflag=false;
		
		//sort by name
		alist=s4s.sort_itemname();

		
		boolean search_flag;
		
		if(length==0) {	//nothing in the list
			System.out.println("There is no Coupon to search, search 0 time");
			return false;
		}
		else 
			if(searchMid(toSearch, mid)) {	//search mid one
				return true;					//if find return ture
				}
			else
				if(toSearch.compareTo(alist.get(mid).getItem_name())<0) {//search left part
//					searchMid(toSearch, (mid+length)/2);
					bflag=searchRecu_left(toSearch, mid);
				}
				else {//search right part
//					searchMid(toSearch, (mid)/2);
					bflag=searchRecu_right(toSearch, mid);
				}
		return bflag;
	}
	
	//recursively search left part
	public boolean searchRecu_left(String toSearch, int mid ) {
		boolean search_recu=false;
		int new_left_mid=mid/2;
		search_recu=searchMid(toSearch, new_left_mid);
		if(new_left_mid==0||new_left_mid==(alist.size()/2)) {
			System.out.println("Binary search does NOT find the item in "+search_time+" time");
			return false;
			}
		else {
			if(search_recu) {return search_recu;}
			else if(toSearch.compareTo(alist.get(new_left_mid).getItem_name())<0) {
				return searchRecu_left(toSearch, (mid+alist.size())/2);
			}
			else return searchRecu_right(toSearch, new_left_mid);
		}
	}
	
	//recursively search right part
	public boolean searchRecu_right(String toSearch, int mid ) {
		boolean search_recu=false;
		int new_right_mid=(mid+alist.size())/2;
		search_recu=searchMid(toSearch, new_right_mid);
		if(new_right_mid==(alist.size()-1)||new_right_mid==(alist.size()/2)) {
			System.out.println("Binary search does NOT find the item in "+search_time+" time");
			return false;
			}
		else {
			if(search_recu) {return search_recu;}
			else if(toSearch.compareTo(alist.get(new_right_mid).getItem_name())<0) {
				return searchRecu_left(toSearch, (new_right_mid+mid)/2);
			}
			else return searchRecu_right(toSearch, new_right_mid);
		}
	}
	
	//search mid one in every sub-part
	public boolean searchMid(String toSearch, int index) {
		boolean mid_flag=false;
		boolean temp_mid_flag=true;
		int subMid=index;
		if(toSearch.equals(alist.get(index).item_name)) {
			mid_flag=true;
			System.out.print("Found in "+search_time+"th in BinarySearch ");
			System.out.println(alist.get(index).geteveything());
			search_time++;
			while(true) {
				if((index--)>=0) {
					temp_mid_flag=search_item(toSearch,index);//search left
					if(temp_mid_flag) {
						System.out.print("Found in "+search_time+"th in BinarySearch ");
						System.out.println(alist.get(index).geteveything());
						index--;
						search_time++;
					}
					else break;
				}else break;
			}
			while(true) {
				if((subMid++)<(alist.size()-1))
				temp_mid_flag=search_item(toSearch,subMid);//search right
				if(temp_mid_flag) {
					System.out.print("Found in "+search_time+"th in BinarySearch ");
					System.out.println(alist.get(subMid).geteveything());
					subMid++;
					search_time++;
				}
				else break;
			}
			return true;
		}
		else{
				search_time++;
				return false;
			}
	}
	
	public boolean search_item(String toSearch, int index) {
		if(toSearch.equals(alist.get(index).item_name)) {
			return true;
		}else return false;
	}
}

