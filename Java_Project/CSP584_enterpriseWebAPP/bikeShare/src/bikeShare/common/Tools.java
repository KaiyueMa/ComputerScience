package bikeShare.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Tools {
	//提供一个方法，将乱码转成UTF-8
	public static String  getNewsString(String input){
		String  result="";
		
		try {
			result=new String (input.getBytes("iso-8859_1"),"UTF-8");
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	return result;
	}
	
	public static  int dateDiff(Date startTime, Date endTime){
		//按照传入的格式生成一个simpledateformate对象
	
		long nd = 1000*24*60*60;//一天的毫秒数
		long nh = 1000*60*60;//一小时的毫秒数
		long nm = 1000*60;//一分钟的毫秒数
		long ns = 1000;//一秒钟的毫秒数long diff;try {
		//获得两个时间的毫秒时间差异
	    long diff;
	    long hour = 0;
		
		diff = endTime.getTime() - startTime.getTime();
	    hour = diff/nh;//计算差多少小时
		
		if(hour<1){
			hour=1;
		}
	
        System.out.println("骑车用时"+hour);
        return (int) hour;
	
		
	}
	
	
	
}

