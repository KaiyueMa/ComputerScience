package bikeShare.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;


	public class RespondTool {
		//提供一个方法，将乱码转成UTF-8
		public static void  getNewsString(HttpServletRequest request, HttpServletResponse response,String message,int code){
			
		 PrintWriter pw=null;
			try {
				response.setCharacterEncoding("UTF-8");
				pw = response.getWriter();
				if(code==100){
					 pw.write(JSONObject.fromObject(bikeShare.model.Msg.success(message)).toString());
					
				}else{
					 pw.write(JSONObject.fromObject(bikeShare.model.Msg.fail(message)).toString());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {
				if(pw!=null){
					pw.close();
				}
			}
		
		}
		
		public static void putList(HttpServletRequest request, HttpServletResponse response, List<?> list){
			PrintWriter pw=null;
			try {
				response.setCharacterEncoding("UTF-8");
				pw = response.getWriter();
				JSONObject jsonObject=new JSONObject();
				if(null!=list){
					
					jsonObject.put("data",list);			
				}
				String returnString=JSON.toJSONString(jsonObject);
				
				try {
					response.setCharacterEncoding("UTF-8");
					pw = response.getWriter();
					pw.write(returnString);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if(pw!=null){
						pw.close();
					}
				}
				   
				
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}finally {
				if(pw!=null){
					pw.close();
				}
			}
			
		}
	}

