package com.util.page;

import java.util.List;

/**
 * 分页控制,主要传递总记录数和当前页 第一次调用时没参数传递,则记算记录数
 * <p>
 * Title: Struts+Hibernate开发模板
 * </p>
 * <p>
 * Description: 通用开发模板
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: 优势科技
 * </p>
 * 
 * @version 1.0
 */
public class PageControlImpl_link implements PageControl {
	private String address; // 联结地址

	private String para; // 联结地址参数

	private int curPage; // 当前是第几页

	private int maxPage; // 一共有多少页

	private int maxRowCount; // 一共有多少行

	private int rowsPerPage; // 每页有多少行

	private int position; // 开始取数位置
	private List dataList;//数据列表

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}

	/**
	 * 构函数
	 * 
	 * @param maxRowCount总记录数
	 * @param rowsPerPage每页记录数
	 * @param curPage当前页号
	 */
	public PageControlImpl_link(int curPage, int rowsPerPage) {
		// 设置联结地址
		this.curPage = curPage;
		// 设置总记录数
		this.rowsPerPage = rowsPerPage;
		// 根据总行数计算总页数
		countMaxPage();
		if (this.curPage < 1)
			this.curPage = 1;
		if (this.curPage > this.maxPage)
			this.curPage = this.maxPage;
		// 计算开始取得数据位置
		countPosition();
	}

	public PageControlImpl_link(int maxRowCount, int curPage, int rowsPerPage) {
		// 设置联结地址
		this.curPage = curPage;
		// 设置总记录数
		this.maxRowCount = maxRowCount;
		// 设置每页记录数
		this.rowsPerPage = rowsPerPage;
		// 根据总行数计算总页数
		countMaxPage();
		if (this.curPage < 1)
			this.curPage = 1;
		if (this.curPage > this.maxPage)
			this.curPage = this.maxPage;
		// 计算开始取得数据位置
		countPosition();
	}

	public PageControlImpl_link(String address, String para, int maxRowCount,
			int curPage, int rowsPerPage) {
		// 设置联结地址
		this.address = address;
		// 设置联结地址参数
		this.para = para;
		// 设置当前页
		this.curPage = curPage;
		// 设置总记录数
		this.maxRowCount = maxRowCount;
		// 设置每页记录数
		this.rowsPerPage = rowsPerPage;
		// 根据总行数计算总页数
		countMaxPage();
		if (this.curPage < 1)
			this.curPage = 1;
		if (this.curPage > this.maxPage)
			this.curPage = this.maxPage;
		// 计算开始取得数据位置
		countPosition();
	}

	// 根据总行数计算总页数
	private void countMaxPage() {
		if (this.maxRowCount % this.rowsPerPage == 0) {
			this.maxPage = this.maxRowCount / this.rowsPerPage;
		} else {
			this.maxPage = this.maxRowCount / this.rowsPerPage + 1;
		}
	}

	// 计算开始取得数据位置
	private void countPosition() {
		this.position = (this.curPage - 1) * this.rowsPerPage;
	}

	/** 取得总记录数 */
	public int getMaxRowCount() {
		return this.maxRowCount;
	}

	/** 取得总记录数 */
	public void setMaxRowCount(int maxRowCount) {
		this.maxRowCount = maxRowCount;
	}

	/** 取得总页数 */
	public int getMaxPage() {
		return this.maxPage;
	}

	/** 取得当前页 */
	public int getCurPage() {
		return this.curPage;
	}

	/** 取每页行数 */
	public int getRowsPerPage() {
		return this.rowsPerPage;
	}

	/** 取得开始查询数据位置 */
	public int getPosition() {
		return this.position;
	}

	/** 取下一页联结 */
	public String getNextPage() {
		String link = this.address;
		int nextValue = this.getCurPage() + 1;
		if (this.curPage < this.maxPage)
			link = "<A HREF=\"" + link + "?" + para + "&count="
					+ this.getMaxRowCount() + "&currentpage=" + nextValue
					+ "\">下一页</A>";
		else
			link = "　　　";
		return link;
	}

	public String getNextPage(String formName) {
		String link = this.address;
		int nextValue = this.getCurPage() + 1;
		if (this.curPage < this.maxPage)
			link = "<A HREF='javascript:document." + formName + ".count.value="
					+ this.getMaxRowCount() + ";document." + formName
					+ ".currentpage.value=" + nextValue + ";document."
					+ formName + ".submit();'>下一页</A>";
		else
			link = "下一页";
		return link;
	}

	/** 取上一页联结 */
	public String getBackPage() {
		String link = this.address;
		int backValue = this.getCurPage() - 1;
		if (backValue > 0)
			link = "<A HREF=\"" + link + "?" + para + "&count="
					+ this.getMaxRowCount() + "&currentpage=" + backValue
					+ "\">上一页</A>";
		else
			link = "　　　";
		return link;
	}

	public String getFirstPage(String formName) {
		String link = this.address;
		int firstValue = 1;
		if (this.getCurPage() > 1)
			link = "<A HREF='javascript:document." + formName + ".count.value="
					+ this.getMaxRowCount() + ";document." + formName
					+ ".currentpage.value=" + firstValue + ";document."
					+ formName + ".submit();'>首页</A>";
		else
			link = "首页";
		return link;

	}

	public String getBackPage(String formName) {
		String link = this.address;
		int backValue = this.getCurPage() - 1;
		if (backValue > 0)
			link = "<A HREF='javascript:document." + formName + ".count.value="
					+ this.getMaxRowCount() + ";document." + formName
					+ ".currentpage.value=" + backValue + ";document."
					+ formName + ".submit();'>上一页</A>";
		else
			link = "上一页";
		return link;
	}

	/** 转到第一页 */
	public String getFirstPage() {
		String link = this.address;
		int firstValue = 1;
		if (this.getCurPage() > 1)
			link = "<A HREF=\"" + link + "?" + para + "&count="
					+ this.getMaxRowCount() + "&currentpage=" + firstValue
					+ "\">首页</A>";
		else
			link = "　　　";
		return link;

	}

	/** 转到最后一页 */
	public String getEndPage() {
		String link = this.address;
		int endValue = this.maxPage;
		if (this.curPage < this.maxPage)
			link = "<A HREF=\"" + link + "?" + para + "&count="
					+ this.getMaxRowCount() + "&currentpage=" + endValue
					+ "\">尾页</A>";
		else
			link = "　　　";
		return link;

	}

	public String getEndPage(String formName) {
		String link = this.address;
		int endValue = this.maxPage;
		if (this.curPage < this.maxPage)
			link = "<A HREF='javascript:document." + formName + ".count.value="
					+ this.getMaxRowCount() + ";document." + formName
					+ ".currentpage.value=" + endValue + ";document."
					+ formName + ".submit();'>尾页</A>";
		else
			link = "尾页";
		return link;

	}

	/**
	 * 显示分页联结
	 * 
	 * @param number显示联结数
	 * @return String
	 */
	public String getLinkPage(int number) {
		String str = "", link = this.address;
		int num;
		int pageNumber;
		if (number < 3)
			number = 3;
		num = number / 2;
		if (number > maxRowCount)
			number = maxRowCount;
		for (int i = 0; i < number; i++) {
			pageNumber = getCurPage() - num + i;
			if (getCurPage() <= num)
				pageNumber = 1 + i;
			if (getCurPage() > getMaxPage() - num)
				pageNumber = getMaxPage() - number + i + 1;
			if (pageNumber > getMaxPage() || pageNumber < 1)
				continue;
			str += "&nbsp;<A HREF=\"" + link + "?" + para + "&count="
					+ this.getMaxRowCount() + "&currentpage=" + pageNumber
					+ "\">" + pageNumber + "</A>&nbsp;";

		}
		return str;
	}

	public String getLinkPage(int number, String formName) {
		String str = "", link = this.address;
		int num;
		int pageNumber;
		if (number < 3)
			number = 3;
		num = number / 2;
		if (number > maxRowCount)
			number = maxRowCount;
		for (int i = 0; i < number; i++) {
			pageNumber = getCurPage() - num + i;
			if (getCurPage() <= num)
				pageNumber = 1 + i;
			if (getCurPage() > getMaxPage() - num)
				pageNumber = getMaxPage() - number + i + 1;
			if (pageNumber > getMaxPage() || pageNumber < 1)
				continue;
			// str += "&nbsp;<A HREF=\"" + link + "?" + para + "&count="
			// + this.getMaxRowCount() + "&currentpage=" + pageNumber
			// + "\">" + pageNumber + "</A>&nbsp;";
			// str += "&nbsp;<A HREF='" + link + "?" + para + "&count="
			// + this.getMaxRowCount() + "&currentpage=" + pageNumber
			// + "\">" + pageNumber + "</A>&nbsp;";
			str += "&nbsp;<A HREF='javascript:goPage(\"" + pageNumber + "\")'>"
					+ pageNumber + "</A>";
		}
		String Funciton = "<script>";
		Funciton = Funciton + "function goPage(pageNumber){";
		Funciton = Funciton + "document." + formName + ".count.value='"
				+ this.getMaxRowCount() + "',";
		Funciton = Funciton + "document." + formName
				+ ".currentpage.value=pageNumber;";
		Funciton = Funciton + "document." + formName + ".submit();";
		Funciton = Funciton + "}";
		Funciton = Funciton + "</script>";
		return str + Funciton;
	}

	/***************************************************************************
	 * 只显示地址
	 **************************************************************************/
	public String getLinkUrl() {
		String str = "";
		str = this.address + "?" + para;
		return str;
	}
	
	public String getBackImgPage(String formName) {
		String link = this.address;
		int backValue = this.getCurPage() - 1;
		if (backValue > 0)
			link = "<A HREF='javascript:document." + formName + ".count.value="
					+ this.getMaxRowCount() + ";document." + formName
					+ ".currentpage.value=" + backValue + ";document."
					+ formName + ".submit();'><img id=\"upd\" name=\"upd\" src=\"Contents/images/btn/anniu_r1_c31.jpg\" border=\"0\" style=\"cursor: hand\" onMouseOver=\"showbtnimg(this,'1');\" onMouseOut=\"showbtnimg(this,'2');\" /></A>";
		else
			link = "<img id=\"upd\" name=\"upd\" src=\"Contents/images/btn/anniu_r1_c32.jpg\" border=\"0\" />";
		return link;
	}
	
	public String getNextImgPage(String formName) {
		String link = this.address;
		int nextValue = this.getCurPage() + 1;
		if (this.curPage < this.maxPage)
			link = "<A HREF='javascript:document." + formName + ".count.value="
					+ this.getMaxRowCount() + ";document." + formName
					+ ".currentpage.value=" + nextValue + ";document."
					+ formName + ".submit();'><img id=\"downd\" name=\"downd\" src=\"Contents/images/btn/anniu_r1_c51.jpg\" border=\"0\" style=\"cursor: hand\" onMouseOver=\"showbtnimg(this,'1');\" onMouseOut=\"showbtnimg(this,'2');\"/></A>";
		else
			link = "<img id=\"upd\" name=\"upd\" src=\"Contents/images/btn/anniu_r1_c52.jpg\" border=\"0\" />";
		return link;
	}

}
