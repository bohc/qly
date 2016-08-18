/**
 *柏红春
 *2014-11-06 13:03:52
 *这个类数据的操作类
 */
package com.pj.dao;

import com.pj.bean.QlyViewtype;

@SuppressWarnings("serial")
public class QlyViewtypeDao extends DaoBase {
	private static QlyViewtypeDao dao;
	static {
		dao = new QlyViewtypeDao();
	}

	public final synchronized static QlyViewtypeDao getInstence() {
		sqlMapClient = sqlMapQly;
		return dao;
	}
}
