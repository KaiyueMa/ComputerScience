package bikeShare.common;

import java.util.List;

public class PageUtil {

	private int pageNo;

	private int pageSize;

	private int totalCount;

	private List<?> data;

	private int totalPage;

	private int currPage = 1;

	public PageUtil(int pageSize, int totalCount) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		if (this.totalCount % this.pageSize == 0) {

			this.totalPage = this.totalCount / this.pageSize;
		} else {
			this.totalPage = this.totalCount / this.pageSize + 1;
		}
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

}
