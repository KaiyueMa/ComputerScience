package bikeShare.model;

public class Pagination {

	private  int pageNow=1;//当前页
	private int countPage=0;//总页数
	private int  pageSize=8;//每页显示页数
	private  int countSize=0;//总记录数
	
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getCountPage() {
		if(countSize!=0){
			
			if(countSize%pageSize!=0){
				countPage=countSize/pageSize+1;
				
				
			}else{
			
			countPage=countSize/pageSize;
			}
			
		}
		
		return countPage;
	}
	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}
	public int getCountSize() {
		return countSize;
	}
	public void setCountSize(int countSize) {
		this.countSize = countSize;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
