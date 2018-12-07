package Search;

import java.util.ArrayList;
import IOtable.Coupon;
import sort.SortName4Search;

public class bstS {
	SortName4Search s4s=new SortName4Search();
	
	ArrayList<Coupon> alist=new ArrayList<Coupon>();
	int i=0;
	int search_time=1;
	
	private bstNode root;
	
	public bstS() {  
        root = null;  
    }
	public bstNode insert (Coupon coupon) {  
        // new node
		bstNode newNode = new bstNode(coupon);  
        // this node  
		bstNode current = root;  
        // previous node 
		bstNode parent  = null;  
        // if root is null  
        if (current == null) {  
            root = newNode;  
            return newNode;  
        }  
        while (true) {  
            parent = current;  
            if ((coupon.item_name.compareTo(current.value.item_name)<=0)) {  
                current = current.left;  
                if (current == null) {  
                    parent.left = newNode;  
                    return newNode;  
                }  
            } else {  
                current = current.right;  
                if (current == null) {  
                    parent.right = newNode;  
                    return newNode;  
                }  
            }  
        }  
    }  
	
	//bulid BST
	public void buildTree() {
		alist= s4s.sort_itemname();
		for(i=0;i<(alist.size());i++) {
			insert(alist.get(i));
		}
//		System.out.println(root.right.left.value.geteveything());
	}

	//BST IO
	public void BST_search (String toSearch) {  
		buildTree();
		bstNode current = root; 
		boolean found_flag=false;
		while (current != null) {  
			if(toSearch.compareTo(current.value.getItem_name())==0) {
				found_flag=true;
	        		System.out.print("Found in "+search_time+"th in BSTSearch ");
	        		System.out.println(current.value.geteveything());
	        		search_time++;
	        		current =current.left;//if there's same element 
	        	}
	        	else {
	        		if (toSearch.compareTo(current.value.getItem_name())<0)  {
		                current = current.left;  
		                search_time++;
		            }
		            else {
		            		current = current.right; 
	        				search_time++;
		            }
	        }  
	        if(current==null&&found_flag==false)  {
	        	System.out.println("BST search does NOT find the item in "+search_time+" time");
	        }
	    }  
	}
}

//Node
class bstNode {  
	
    Coupon value;    
    bstNode left;  
    bstNode right;  
  
    public bstNode (Coupon value) {  
        this.value = value;  
        left  = null;  
        right = null;  
    }  
} 
