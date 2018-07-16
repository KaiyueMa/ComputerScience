package IOtable;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class IOFile {
	public static LinkedList<Coupon> list = new LinkedList<Coupon>();
	public ArrayList<Coupon> alist=new ArrayList<Coupon>();
	public int line = 1;
	
	public void readFrom_OR_buildFile(File filename) throws Exception {
		
		String tempString = null;
		BufferedReader read= null;
	    
	    
	    //if file doesn't exist, build it
	    if(!(filename.exists())) {
	    		try {
	    			filename.createNewFile(); 
	    			}
	    		catch(Exception e){  
	    			e.printStackTrace();  
	    	  		}  
	    		filename.createNewFile();
	    		System.out.println("Index <"+filename+".txt> doesn't exist, "
	    				+ "build a new <"+filename+".txt>");
	    		FileWriter writter=new FileWriter(filename,true);
            BufferedWriter bufferWritter = new BufferedWriter(writter);
            
          //set guide in the file
            bufferWritter.write("(provider name, item name, initial price, discount rate, "
            		+ "actual price, expire time, state, add on state)");
            bufferWritter.close();
	    }
	    
	    //read from the file
	    else {
	    try {
	        System.out.println("Read file "+filename);
	        read = new BufferedReader(new FileReader("/Users"
	        		+ "/michael/Codes/Java-eclipse/Project1.2/"+filename));
	        tempString=read.readLine();
	        while ((tempString = read.readLine()) != null) {
//	        		System.out.println(tempString);
	        		readFromFile2llist(tempString);
	            line ++ ;	
	        }
	        read.close();
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    	
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally{
	        if(read != null){
	            try {
	                read.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	  }
	    System.out.println(list.size()+" Coupon(s) in the index");
	    System.out.println();
	}
	
	//wirte manual input into file
	public static void writeTo_OR_bulidFile(File filename, String content) {  
        try {    
            if (filename.exists()) { } 
            else {  
                System.out.print("Index <"+filename+">.txt doesn't exist, "
                		+ "build a new file: <"+filename+".txt>");  
                filename.createNewFile();  
                FileWriter writter=new FileWriter(filename,true);
                BufferedWriter bufferWritter = new BufferedWriter(writter);
                bufferWritter.write("(provider name, item name, initial price, "
                		+ "discount rate, actual price, expire time, state, add on state)");
                bufferWritter.close();
            }  
          FileWriter writter=new FileWriter(filename,true);
          BufferedWriter bufferWritter = new BufferedWriter(writter);
          bufferWritter.newLine();
          bufferWritter.write(content);
          bufferWritter.close();
        } catch (Exception e) {  
            e.printStackTrace();  
  
        }  
    }  

	//read from file and save to master list
	public void readFromFile2llist(String org_string) throws ParseException {
		GuideInput guide = new GuideInput();
		if(!(org_string.equals(""))) {
		String[] sp_str = org_string.split("----");

			if(sp_str.length<8) {
				System.out.println(org_string+"Lost element, delete this line");
			}
			else {
				boolean existdate_flag=guide.update_statue_4exist(sp_str[5]);
				if(existdate_flag) {}//before today
				else {sp_str[6]="false";}

				list.add(new Coupon(sp_str[0], sp_str[1], sp_str[2], 
						sp_str[3], sp_str[4], sp_str[5], sp_str[6], sp_str[7]));
				System.out.println("Line"+ line + ": " +org_string);

			}
		}
		else {
			line--;
		}
		
	
}


	public LinkedList<Coupon> getList() {
		return list;
	}
	
}


	
