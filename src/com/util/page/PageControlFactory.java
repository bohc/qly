package com.util.page;

import com.base.ModelBase;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class PageControlFactory {
	private PageControlFactory() {
	}

	/**
	 * @param address
	 *            String
	 * @param para
	 *            String
	 * @param maxRowCount
	 *            int
	 * @param curPage
	 *            int
	 * @param rowsPerPage
	 *            int
	 * @return PageControl
	 */
	public final static PageControl getPageControlLink(String address, String para, int maxRowCount, int curPage, int rowsPerPage) {
		return new PageControlImpl_link(address, para, maxRowCount, curPage, rowsPerPage);
	}

	public final static PageControl getPgList(String rowsSql, String countSql, SqlMapClient sqlMap, String curPage, ModelBase o) {
		int parCurPage = 1;
		int parRowsPerPage = 0;
		if (curPage == null || curPage.equals("")) {
			parCurPage = 1;
		} else {
			parCurPage = new Integer(curPage).intValue();
		}

		try {
			parRowsPerPage = new Integer((String) sqlMap.queryForObject(countSql, o)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}

		PageControlImpl_link pm = new PageControlImpl_link(parRowsPerPage, parCurPage, o.getPageSize());

		o.setPageSize(pm.getRowsPerPage());
		if (parRowsPerPage > 0)
			o.setCurPage((pm.getCurPage() - 1) * o.getPageSize());

		o.setMaxcount(parRowsPerPage);

		try {
			pm.setDataList(sqlMap.queryForList(rowsSql, o));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;

	}

	public final static PageControl getPgListForStore(String rowsSql, String countSql, SqlMapClient sqlMap, String curPage, ModelBase o) {
		int parCurPage = 1;
		int parRowsPerPage = 0;
		if (curPage == null || curPage.equals("")) {
			parCurPage = 1;
		} else {
			parCurPage = new Integer(curPage).intValue();
		}

		try {
			parRowsPerPage = sqlMap.queryForList(countSql, o).size();
		} catch (Exception e) {
			e.printStackTrace();
		}

		PageControlImpl_link pm = new PageControlImpl_link(parRowsPerPage, parCurPage, o.getPageSize());

		o.setPageSize(pm.getRowsPerPage());
		if (parRowsPerPage > 0)
			o.setCurPage((pm.getCurPage() - 1) * o.getPageSize());

		o.setMaxcount(parRowsPerPage);

		try {
			pm.setDataList(sqlMap.queryForList(rowsSql, o));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pm;

	}
}
