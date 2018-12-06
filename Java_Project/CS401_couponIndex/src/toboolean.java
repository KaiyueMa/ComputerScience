import java.util.Scanner;

public class toboolean {
	private String s_flag;
	private boolean b_flag;
	Scanner scan = new Scanner(System.in);
	
		
	public boolean toboolean() {		//transfer input to boolean
		s_flag= scan.nextLine();
		if(s_flag.equals("Y")||s_flag.equals("y")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
}
