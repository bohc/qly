package com.base;

/**
 * 所有持久类的基类
 * 
 * @author Administrator sortCol 用哪个字段排序 sort 是升序还是降序 curPage 当前页 pageSize
 *         每页显示的行数 isPage 是否分页,0为不分布,1为分页,默认为不分页; maxCount 有多少数据
 */
public class ModelBase {
	private String sortCol = "nID";// 用哪个字段排序
	private String sort = "DESC";// 是升序还是降序
	private int curPage = 0;// 当前页
	private int pageSize = 15;// 每页显示的行数 默认为15
	private String isPage;// 是否分页,空为不分布,有值为分页
	private int maxcount;// 有多少数据

	public String getSortCol() {
		return sortCol;
	}

	public void setSortCol(String sortCol) {
		this.sortCol = sortCol;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public int getMaxcount() {
		return maxcount;
	}

	public void setMaxcount(int maxcount) {
		this.maxcount = maxcount;
	}

}
