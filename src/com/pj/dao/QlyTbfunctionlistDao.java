/**
 *柏红春
 *2014-11-03 10:37:15
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyTbfunctionlist;

@SuppressWarnings("serial")
public class QlyTbfunctionlistDao extends DaoBase {
	private static QlyTbfunctionlistDao dao;
	static {
		dao = new QlyTbfunctionlistDao();
	}

	public final synchronized static QlyTbfunctionlistDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
